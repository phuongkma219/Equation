package com.example.equation.ui.quadratic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.equation.data.model.Quadratic
import com.example.equation.data.response.DataResponse
import com.example.equation.utils.checkSquareRoot

class QuadraticViewModel:ViewModel() {
    var result= MutableLiveData<DataResponse<MutableList<Double>>>()


    fun calculatePT(pt : Quadratic){
        val numA:Double = pt.numberA
        val numB:Double= pt.numberB
        var numC:Double= pt.numberC
        var numD:Double= pt.numberD
        var x1: Double = 0.0
        var x2: Double = 0.0
        var x3: Double = 0.0
        var x : Double = 0.0
        val array : MutableList<Double> = mutableListOf<Double> ()

        val delta = pt.delta()
        val k = pt.numK()
        if (delta > 0) {
            if (Math.abs(k) <= 1) {
                x1 = ((2 * Math.sqrt(delta) * Math.cos((Math.acos(k)) / 3) - numB)) / (3 * numA)
                x2 =
                    ( (2 * Math.sqrt(delta) * Math.cos((Math.acos(k) / 3) - ((2 * Math.PI) / 3)))  - numB) / (3 * numA)

                x3 =
                    ((2 * Math.sqrt(delta) * Math.cos((Math.acos(k) / 3) + ((2 * Math.PI) / 3))) - numB) / (3 * numA)

                array.add(x1)
                array.add(x2)
                array.add(x3)
                result.value = DataResponse.DataThreeSolutions(array)

            } else if (Math.abs(k) > 1) {
                val a1 =(Math.sqrt(delta) *Math.abs(k))/(3*numA*k)
                val k1 = Math.sqrt(Math.pow(k,2.0) -1)
                val a3 =  Math.pow(Math.abs(k)- Math.sqrt(Math.pow(k,2.0)-1),1.0/3)
                x = (a1*a3) - (numB/ (3*numA))
                array.add(x)
                result.value = DataResponse.DataOneSolutions(array)

            }
        }
        if ( delta.toInt()==0){
            x = (-numB + Math.pow(checkSquareRoot(numB,1.0/3) - 27* checkSquareRoot(numA,2.0) *numD,1.0/3)) / (3*numA)
            array.add(x)
            result.value = DataResponse.DataTwoSolutions(array)
        }
        if (delta<0){
            val a1 = (Math.sqrt(Math.abs(delta)) / (3*numA))
            val k1 = Math.sqrt(checkSquareRoot(k,2.0) +1)
            val a2 = checkSquareRoot(k+  k1 ,1.0/3) + checkSquareRoot(k-k1,1.0/3)
            x =a1*a2 - (numB/(3*numA))

            array.add(x)
            result.value = DataResponse.DataOneSolutions(array)

        }
    }
    class Factory() : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuadraticViewModel::class.java)) {
                return QuadraticViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}