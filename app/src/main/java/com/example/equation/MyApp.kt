package com.example.equation

import android.annotation.SuppressLint
import com.example.equation.ui.base.BaseApplication
import com.example.equation.ui.base.resource.BaseResource

class MyApp : BaseApplication() {
    private val TAG = "MyApp"

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var resource: BaseResource
        fun resource() = resource
    }

    override fun onCreate() {
        super.onCreate()
        resource = BaseResource(this)
    }
}