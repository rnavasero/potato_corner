package com.example.codemagnus.newproject.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.system.Os.remove
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunmi.Activities.MainActivity
import com.sunmi.Fragments.FragmentReceipt
import com.sunmi.printerhelper.R
import com.sunmi.printerhelper.utils.AidlUtil


/**
 * Created by codemagnus on 4/2/18.
 */

class SuccessFragment: Fragment() {
    private var mActivity: MainActivity? = null


    companion object {
        val TAG: String = SuccessFragment::class.java.simpleName
        var instance: SuccessFragment? = SuccessFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        instance = this
        return inflater?.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity?.setToolbar(false, "Success")

    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

    fun onBackPressed(){
        val fragmentManager = fragmentManager
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
