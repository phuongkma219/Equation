package com.example.equation.ui.system_equation

import com.example.equation.R
import com.example.equation.data.model.SystemDataType
import com.example.equation.data.model.SystemEquation
import com.example.equation.databinding.LayoutInputDialogBinding
import com.example.equation.ui.base.BaseDialogFragment
import com.example.equation.utils.Constants
import com.example.equation.utils.SendData

class InputDialog : BaseDialogFragment<LayoutInputDialogBinding>() {
    override fun initViewBinding() {
        mBinding.inputToolBar.title = getString(R.string.input_dialog)
        val array: ArrayList<Double> = arrayListOf()
        mBinding.btnResult.setOnClickListener {
            if (arguments != null) {
                val e1 = requireArguments().getString(resources.getString(R.string.input_dialog))
                val numberA = if ( mBinding.coefficientA.text.toString().isEmpty()) 0.0 else mBinding.coefficientA.text.toString().toDouble()
                val numberB = if ( mBinding.coefficientB.text.toString().isEmpty()) 0.0 else mBinding.coefficientB.text.toString().toDouble()
                val numberC = if ( mBinding.coefficientC.text.toString().isEmpty()) 0.0 else mBinding.coefficientC.text.toString().toDouble()
                val numberD = if ( mBinding.coefficientD.text.toString().isEmpty()) 0.0 else mBinding.coefficientD.text.toString().toDouble()
                array.add(numberA)
                array.add(numberB)
                array.add(numberC)
                array.add(numberD)
                if (e1 == Constants.PT_ONE) {
                    SendData.getInstance().getLiveArr().value = SystemEquation(SystemDataType.PT_ONE,array)
                }
                if (e1 == Constants.PT_TWO) {
                    SendData.getInstance().getLiveArr().value = SystemEquation(SystemDataType.PT_TWO,array)
                }
                if (e1 == Constants.PT_THREE) {
                    SendData.getInstance().getLiveArr().value = SystemEquation(SystemDataType.PT_THREE,array)
                }
            }
            dismiss()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_input_dialog
    }
}