package com.sunmi.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunmi.printerhelper.R

/**
 * A simple [Fragment] subclass.
 */
class BaseFragment : Fragment(){

    companion object {
        fun newInstance():BaseFragment = BaseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_base, container, false)
    }

}// Required empty public constructor
