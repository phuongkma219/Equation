package com.example.equation.utils

import kotlin.math.pow


fun checkSquareRoot(x:Double,y:Double):Double{
        if (x > 0) {
            return x.pow(y)
        }
        return -1 * (-x).pow(y)
    }
fun determinantOfMatrix(mat: Array<DoubleArray>): Double {
    val ans: Double
    ans = (mat[0][0] * (mat[1][1] * mat[2][2] - mat[2][1] * mat[1][2])
            - mat[0][1] * (mat[1][0] * mat[2][2] - mat[1][2] * mat[2][0])
            + mat[0][2] * (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]))
    return ans
}

