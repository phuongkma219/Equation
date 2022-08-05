package com.example.equation.data.model

import com.example.equation.utils.checkSquareRoot

class Quadratic {
     var numberA:Double = 0.0
     var numberB:Double = 0.0
     var numberC:Double = 0.0
     var numberD:Double = 0.0

     constructor(numberA: Double, numberB: Double, numberC: Double, numberD: Double) {
         this.numberA = numberA
         this.numberB = numberB
         this.numberC = numberC
         this.numberD = numberD
     }

     fun delta():Double{
         return  Math.pow(numberB, 2.0) - (3 * numberA * numberC)
     }
     fun numK():Double{
         return ((9 * numberA * numberB * numberC) - (2 * checkSquareRoot(numberB, 3.0)) - (27 * checkSquareRoot(
             numberA,
             2.0))* numberD) /
                 (2 * Math.sqrt(Math.abs(checkSquareRoot(delta(), 3.0))))
     }
}