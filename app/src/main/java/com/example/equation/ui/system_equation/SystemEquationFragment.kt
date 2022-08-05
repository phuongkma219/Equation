package com.example.equation.ui.system_equation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.baseproject.ui.base.BaseWithVMFragment
import com.example.equation.R
import com.example.equation.data.model.SystemDataType
import com.example.equation.data.model.SystemEquation
import com.example.equation.data.response.DataResponse
import com.example.equation.data.response.ResultsModelDataType
import com.example.equation.databinding.FragmentSystemBinding
import com.example.equation.utils.AlertDialogUtils
import com.example.equation.utils.Constants
import com.example.equation.utils.SendData

class SystemEquationFragment:BaseWithVMFragment<FragmentSystemBinding,SystemEquationViewModel>() {
    override fun getLayout(): Int {
        return R.layout.fragment_system
    }
    companion object{
        private const val TAG = "SystemEquationFragment"
    }

    override fun initView() {
        (activity as AppCompatActivity?)!!.findViewById<Toolbar>(R.id.toolbar).title =
            resources.getString(R.string.sysem_equation)
        initViewModel()
        val arr = Array<DoubleArray>(3, { DoubleArray(4) })
        SendData.getInstance().getLiveArr().observe(viewLifecycleOwner) {

            if (it.systemDataType == SystemDataType.PT_ONE){
                for (i in 0..it.data!!.size - 1) {
                    arr[0][i] = it.data[i].toDouble()
                }
                binding.tvResultE1.text = "${it.data[0]}x + (${it.data[1]})y + (${it.data[2]})z = ${it.data[3]}"
            }
            else if (it.systemDataType == SystemDataType.PT_TWO){
                for (i in 0..it.data!!.size - 1) {
                    arr[1][i] = it.data[i].toDouble()
                }
                binding.tvResultE2.text = "${it.data[0]}x + (${it.data[1]})y + (${it.data[2]})z = ${it.data[3]}"
            }
            else if (it.systemDataType == SystemDataType.PT_THREE){
                for (i in 0..it.data!!.size - 1) {
                    arr[2][i] = it.data[i].toDouble()
                }
                binding.tvResultE3.text = "${it.data!![0]}x + (${it.data[1]})y + (${it.data[2]})z = ${it.data[3]}"
                SendData.getInstance().getLiveArr().value = SystemEquation(SystemDataType.NULL,null)

            }

        }

        binding.ivEditE1.setOnClickListener {
            val inputDialog = InputDialog()
            val bundle = Bundle()
            bundle.putString(resources.getString(R.string.input_dialog), Constants.PT_ONE)
            inputDialog.arguments = bundle
            inputDialog.show(childFragmentManager, "InputDialog")
        }
        binding.ivEditE2.setOnClickListener {
            val inputDialog = InputDialog()
            val bundle = Bundle()
            bundle.putString(resources.getString(R.string.input_dialog), Constants.PT_TWO)
            inputDialog.arguments = bundle
            inputDialog.show(childFragmentManager, "InputDialog")
        }
        binding.ivEditE3.setOnClickListener {
            val inputDialog = InputDialog()
            val bundle = Bundle()
            bundle.putString(resources.getString(R.string.input_dialog), Constants.PT_THREE)
            inputDialog.arguments = bundle
            inputDialog.show(childFragmentManager, "InputDialog")
        }
        binding.btnResult.setOnClickListener {
            mViewModel.findSolutionHPT(arr)
        }
    }
    fun initViewModel(){
        val factory = SystemEquationViewModel.Factory()
        mViewModel =
            ViewModelProvider(this, factory)[SystemEquationViewModel::class.java]
        mViewModel.result.observe(viewLifecycleOwner){
            when(it.resultsModelDataType){
                ResultsModelDataType.THREE_SOLUTIONS ->{
                    val data = (it as DataResponse.DataThreeSolutions).data
                    val result = "\n x = ${data[0]} \n y = ${data[1]} \n z = ${data[2]} \n "
                    AlertDialogUtils(requireContext()).show(
                        R.string.result,
                        result
                    )
                }
                ResultsModelDataType.INFINITE_SOlUTIONS ->{
                    AlertDialogUtils(requireContext()).show(
                        R.string.result,
                        getString(R.string.infinite_solution)
                    )
                }
                ResultsModelDataType.NO_SOLUTIONS ->{
                    AlertDialogUtils(requireContext()).show(
                        R.string.result,
                        getString(R.string.no_solution)
                    )
                }
                else -> {}
            }
        }
    }

}