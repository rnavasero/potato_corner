package com.example.codemagnus.newproject.Adapters

import android.app.DialogFragment
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.example.codemagnus.newproject.Fragments.SizeSelectionFragment
import com.squareup.picasso.Picasso
import com.sunmi.Activities.MainActivity
import com.sunmi.Models.Product
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.category_content.view.*


/**
 * Created by codemagnus on 3/20/18.
 */
class ProductAdapter(val mContext:Context?, itemList:MutableList<Product>?):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val TAG2 = "###"
    val items = itemList

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindHolderbyPosition(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.category_content, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    open inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val mActivity = mContext as MainActivity?
        fun bindHolderbyPosition(position: Int){
            val item = items!![position]
            itemView.tv_item_name.text = item.name
            Picasso.with(mContext).load(item.imgUrl).resize(260,260).centerCrop().into(itemView.iv_item_content)

            itemView.fl_product_select.setOnClickListener {

                if (item.category != "FLAVORED FRIES") {
                    if (item.category == "FANCY FRIES"){
                        SizeSelectionFragment.category = item.category
                        SizeSelectionFragment.i_id = item.id
                        SizeSelectionFragment.n_name = item.name
                        SizeSelectionFragment.i_image = item.imgUrl
                        SizeSelectionFragment.newInstance()
                        mActivity!!.addnewFragment(SizeSelectionFragment(),SizeSelectionFragment.TAG)

                    } else if (item.category == "DRINKS"){
                        SizeSelectionFragment.category = item.category
                        SizeSelectionFragment.i_id = item.id
                        SizeSelectionFragment.n_name = item.name
                        SizeSelectionFragment.i_image = item.imgUrl
                        mActivity!!.addnewFragment(SizeSelectionFragment(),SizeSelectionFragment.TAG)
//                        val alert:android.support.v4.app.DialogFragment = SizeSelectionFragment()
//                        alert.show(mActivity!!.fm, SizeSelectionFragment.TAG)

                    }
                } else {
                    SizeSelectionFragment.category = item.category
                    SizeSelectionFragment.i_id = item.id
                    SizeSelectionFragment.flavor = item.name
                    mActivity!!.addnewFragment(SizeSelectionFragment(),SizeSelectionFragment.TAG)
//                    val alert:android.support.v4.app.DialogFragment = SizeSelectionFragment()
//                    alert.show(mActivity!!.fm, SizeSelectionFragment.TAG)


                }
            }

        }

    }

}