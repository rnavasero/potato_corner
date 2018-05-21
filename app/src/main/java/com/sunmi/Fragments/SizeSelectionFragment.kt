package com.example.codemagnus.newproject.Fragments

import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Adapters.SizeAdapter
import com.example.codemagnus.newproject.Adapters.SizeSelectAdapter
import com.sunmi.Activities.MainActivity
import com.sunmi.Models.Product
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.fragment_size_selection.*

/**
 * Created by codemagnus on 3/21/18.
 */
class SizeSelectionFragment: DialogFragment() {

    private val TAG2 = "#####################"
    private var mActivity: MainActivity? = null
    private var adapter: SizeAdapter? = null
    private var mAdapter: SizeSelectAdapter? = null
    private var items: Product = Product()

    companion object {
        var i_id:String? = null
        var n_name:String? = null
        var i_image:Int? = null
        var category:String? = null
        var flavor:String? = null
        val TAG: String = SizeSelectionFragment::class.java.simpleName
        var instance: SizeSelectionFragment? = SizeSelectionFragment()

    }

    fun newInstance(product: Product):Fragment{
        var fragment = SizeSelectionFragment()
        items = product
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        instance = this
        return inflater?.inflate(R.layout.fragment_size_selection, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = context as MainActivity?
        mActivity?.setToolbar(false)
        adapter = SizeAdapter(context, category,flavor, i_id)
        mAdapter = SizeSelectAdapter(context, category, i_image, n_name, i_id)


        Log.i(TAG2, items.toString())


        if(category == "Flavored Fries"){
            rv_sizeselect.layoutManager = GridLayoutManager(context,2)
            rv_sizeselect.adapter = adapter
        }
        else if(category == "Fancy Fries")
        {
            rv_sizeselect.layoutManager = GridLayoutManager(context,1)
            rv_sizeselect.adapter = mAdapter
        }
        else if(category == "Beverages")
        {
            rv_sizeselect.layoutManager = GridLayoutManager(context,1)
            rv_sizeselect.adapter = mAdapter
        }
        Log.i(TAG2,"SIZE FRAGMENT" + items.toString())


    }

    override fun onDetach() {
        super.onDetach()

        if (this.isRemoving) {
            val fm = fragmentManager
            fm.beginTransaction().remove(SizeSelectionFragment()).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null

    }
}