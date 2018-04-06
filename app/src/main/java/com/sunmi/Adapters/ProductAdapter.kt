package com.example.codemagnus.newproject.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Fragments.SizeSelectionFragment
import com.example.codemagnus.newproject.Models.Product
import com.squareup.picasso.Picasso
import com.sunmi.Activities.MainActivity
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.category_content.view.*


/**
 * Created by codemagnus on 3/20/18.
 */
class ProductAdapter(val mContext:Context?, itemList:MutableList<Product>?):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val TAG2 = "#####################"
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

            itemView.iv_item_content.setOnClickListener {

                if (item.category != "Flavored Fries") {
                    if (item.category == "Fancy Fries"){
                        SizeSelectionFragment.category = item.category
                        SizeSelectionFragment.i_id = item.id
                        SizeSelectionFragment.n_name = item.name
                        SizeSelectionFragment.i_image = item.imgUrl

                        Log.i(TAG2,item.toString())
                        mActivity?.newFragment(SizeSelectionFragment(), SizeSelectionFragment.TAG)

                    } else if (item.category == "Beverages"){
                        SizeSelectionFragment.category = item.category
                        SizeSelectionFragment.i_id = item.id
                        SizeSelectionFragment.n_name = item.name
                        SizeSelectionFragment.i_image = item.imgUrl
                        mActivity?.newFragment(SizeSelectionFragment(), SizeSelectionFragment.TAG)

                    }
                } else {
                    SizeSelectionFragment.category = item.category
                    SizeSelectionFragment.i_id = item.id
                    SizeSelectionFragment.flavor = item.name
                    mActivity?.newFragment(SizeSelectionFragment(), SizeSelectionFragment.TAG)


                }
            }

        }

    }
}