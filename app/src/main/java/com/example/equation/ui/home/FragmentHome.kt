package com.example.equation.ui.home

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.baseproject.ui.base.BaseWithVMFragment
import com.example.equation.R
import com.example.equation.databinding.FragmentHomeBinding

class FragmentHome:BaseWithVMFragment<FragmentHomeBinding,HomeViewModel>(), View.OnClickListener {
    override fun getLayout(): Int {
       return R.layout.fragment_home
    }

    override fun initView() {
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.home)
        binding.btnQuadratic.setOnClickListener(this)
        binding.btnSystem.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_quadratic ->{
                findNavController().navigate(FragmentHomeDirections.actionHomeToQuadratic())
            }
            R.id.btn_system ->{
                findNavController().navigate(FragmentHomeDirections.actionHomeToSystemEquation())
            }
        }
    }
}