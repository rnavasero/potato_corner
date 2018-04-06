package com.example.codemagnus.newproject.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunmi.printerhelper.R


/**
 * Created by codemagnus on 4/2/18.
 */

class SuccessFragment: Fragment() {
    var response = ""

    companion object {
        val TAG: String = SuccessFragment::class.java.simpleName
    }

    fun newInstance(result: String): Fragment {
        val fragment = SuccessFragment()
        fragment.response = result
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "result: $response")
    }
}