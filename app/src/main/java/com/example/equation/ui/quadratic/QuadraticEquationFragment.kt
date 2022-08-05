package com.example.equation.ui.quadratic

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.baseproject.ui.base.BaseWithVMFragment
import com.example.equation.R
import com.example.equation.data.model.Quadratic
import com.example.equation.data.response.DataResponse
import com.example.equation.data.response.ResultsModelDataType
import com.example.equation.databinding.FragmentQuadraticBinding
import com.example.equation.utils.AlertDialogUtils


class QuadraticEquationFragment :
    BaseWithVMFragment<FragmentQuadraticBinding, QuadraticViewModel>() {
    override fun getLayout(): Int {
        return R.layout.fragment_quadratic
    }

    override fun initView() {
        (activity as AppCompatActivity?)!!.findViewById<Toolbar>(R.id.toolbar).title =
            resources.getString(R.string.quadratic_equation)
        initViewModel()
        binding.btnResult.setOnClickListener {
            val numberA = if ( binding.coefficientA.text.toString().isEmpty()) 0.0 else binding.coefficientA.text.toString().toDouble()
            val numberB = if ( binding.coefficientB.text.toString().isEmpty()) 0.0 else binding.coefficientB.text.toString().toDouble()
            val numberC = if ( binding.coefficientC.text.toString().isEmpty()) 0.0 else binding.coefficientC.text.toString().toDouble()
            val numberD = if ( binding.coefficientD.text.toString().isEmpty()) 0.0 else binding.coefficientD.text.toString().toDouble()
           if (numberA==0.0 && numberB==0.0 && numberC==0.0 && numberD==0.0 ){
               AlertDialogUtils(requireContext()).show(
                   R.string.result,
                   getString(R.string.error)
               )
           }
            else{
               val quadratic = Quadratic(numberA, numberB, numberC, numberD)
               mViewModel.calculatePT(quadratic)
           }
        }
    }


    fun initViewModel() {
        val factory = QuadraticViewModel.Factory()
        mViewModel =
            ViewModelProvider(this, factory)[QuadraticViewModel::class.java]
        mViewModel.result.observe(viewLifecycleOwner) {
            it.let {
                when (it.resultsModelDataType) {
                    ResultsModelDataType.ONE_SOLUTIONS -> {
                        val data = (it as DataResponse.DataOneSolutions).data
                        AlertDialogUtils(requireContext()).show(
                            R.string.result,
                            "x = ${data.get(0).toString()}"
                        )
                    }
                    ResultsModelDataType.TWO_SOLUTIONS -> {
                        val data = (it as DataResponse.DataTwoSolutions).data
                        AlertDialogUtils(requireContext()).show(
                            R.string.result,
                            "x = ${data.get(0).toString()}"
                        )

                    }
                    ResultsModelDataType.THREE_SOLUTIONS -> {
                        val data = (it as DataResponse.DataThreeSolutions).data
                        val result =
                            "\n x1 = ${data[0]} \n x2 = ${it.data[1]} \n x3 = ${it.data[2]} \n "

                        AlertDialogUtils(requireContext()).show(R.string.result, "x = ${result}")

                    }

                    else -> {}
                }
            }
        }

    }
}