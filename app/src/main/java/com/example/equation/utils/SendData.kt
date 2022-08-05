package com.example.equation.utils

import androidx.lifecycle.MutableLiveData
import com.example.equation.data.model.SystemEquation

class SendData private constructor(){
    private var arr : MutableLiveData<SystemEquation> = MutableLiveData()

    fun getLiveArr():MutableLiveData<SystemEquation>{
            return arr
    }

    companion object{
        private val mInstance = SendData()
        @Synchronized
        fun getInstance(): SendData {
            return mInstance
        }
    }
}