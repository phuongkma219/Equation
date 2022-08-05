package com.example.equation.ui.system_equation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.equation.data.response.DataResponse
import com.example.equation.ui.quadratic.QuadraticViewModel
import com.example.equation.utils.determinantOfMatrix

class SystemEquationViewModel:ViewModel() {
    var result= MutableLiveData<DataResponse<MutableList<Double>>>()

    fun findSolutionHPT(coeff: Array<DoubleArray>) {
        val array : MutableList<Double> = mutableListOf<Double> ()

        val d = arrayOf(
            doubleArrayOf(coeff[0][0], coeff[0][1], coeff[0][2]), doubleArrayOf(
                coeff[1][0], coeff[1][1], coeff[1][2]
            ), doubleArrayOf(coeff[2][0], coeff[2][1], coeff[2][2])
        )

        val d1 = arrayOf(
            doubleArrayOf(coeff[0][3], coeff[0][1], coeff[0][2]), doubleArrayOf(
                coeff[1][3], coeff[1][1], coeff[1][2]
            ), doubleArrayOf(coeff[2][3], coeff[2][1], coeff[2][2])
        )

        val d2 = arrayOf(
            doubleArrayOf(coeff[0][0], coeff[0][3], coeff[0][2]), doubleArrayOf(
                coeff[1][0], coeff[1][3], coeff[1][2]
            ), doubleArrayOf(coeff[2][0], coeff[2][3], coeff[2][2])
        )


        val d3 = arrayOf(
            doubleArrayOf(coeff[0][0], coeff[0][1], coeff[0][3]), doubleArrayOf(
                coeff[1][0], coeff[1][1], coeff[1][3]
            ), doubleArrayOf(coeff[2][0], coeff[2][1], coeff[2][3])
        )

        val D = determinantOfMatrix(d)
        val D1 = determinantOfMatrix(d1)
        val D2 = determinantOfMatrix(d2)
        val D3 = determinantOfMatrix(d3)

        // Case 1
        if (D != 0.0) {
            val x = D1 / D
            val y = D2 / D
            val z = D3 / D

            array.add(0,x)
            array.add(1,y)
            array.add(2,z)
            result.value = DataResponse.DataThreeSolutions(array)

        } else {
            if (D1 == 0.0 && D2 == 0.0 && D3 == 0.0){
                result.value = DataResponse.DataInfiniteSolutions()
            }
            else if (D1 != 0.0 || D2 != 0.0 || D3 != 0.0) {
                result.value = DataResponse.DataNoSolutions()
            }
        }

    }
    class Factory() : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SystemEquationViewModel::class.java)) {
                return SystemEquationViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}