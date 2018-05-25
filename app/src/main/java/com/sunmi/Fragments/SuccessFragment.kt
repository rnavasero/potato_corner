package com.example.codemagnus.newproject.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunmi.Activities.MainActivity
import com.sunmi.Fragments.FragmentReceipt
import com.sunmi.printerhelper.R


/**
 * Created by codemagnus on 4/2/18.
 */

class SuccessFragment: Fragment() {
    var bm:Bitmap? = null
    private var mActivity: MainActivity? = null

    companion object {
        val TAG: String = SuccessFragment::class.java.simpleName
        var instance:SuccessFragment? = SuccessFragment()
        fun newInstance(): SuccessFragment = SuccessFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity!!.setReceiptState(true)
        instance = this
        //mActivity?.setToolbar(false)
        //mActivity!!.setCustomToolbarTitle("RECEIPT")
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}