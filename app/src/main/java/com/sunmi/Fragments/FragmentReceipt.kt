package com.sunmi.Fragments


import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Fragments.SuccessFragment
import com.sunmi.Activities.MainActivity
import com.sunmi.Adapters.ReceiptItemRecyclerAdapter
import com.sunmi.printerhelper.R
import com.sunmi.printerhelper.utils.AidlUtil
import kotlinx.android.synthetic.main.layout_receipt.*
import kotlinx.android.synthetic.main.layout_receipt.view.*

/**
 * Created by codemagnus on 4/6/18.
 */
open class FragmentReceipt: Fragment() {
    private var mActivity: MainActivity? = null
    private var mAdapter:ReceiptItemRecyclerAdapter? = null
    var bm:Bitmap? = null



    companion object {
        val TAG:String = FragmentReceipt::class.java.simpleName
        var instance: FragmentReceipt? = FragmentReceipt()
        fun newInstance(): FragmentReceipt = FragmentReceipt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        instance = this
        return layoutInflater.inflate(R.layout.layout_receipt, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = context as MainActivity?
        mActivity?.setToolbar(false)
        //mFragment.setTotalPrice()
            mAdapter                      = ReceiptItemRecyclerAdapter(context)
            rv_receipt!!.layoutManager    = LinearLayoutManager(context)
            rv_receipt!!.adapter          = mAdapter
            val frameLayout = view.frame_category
            frameLayout.isDrawingCacheEnabled = true
            frameLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            frameLayout.layout(0, 0, frameLayout.measuredWidth, frameLayout.measuredHeight)
            frameLayout.buildDrawingCache(true)
            bm = frameLayout.drawingCache
            AidlUtil.getInstance().printBitmap(bm)
            mActivity!!.fm!!.beginTransaction().apply {
                replace(R.id.main_frame, SuccessFragment(), SuccessFragment.TAG)
                addToBackStack(SuccessFragment.TAG)
            }.commit()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        instance = null
    }


}