package com.sunmi.Adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Adapters.CheckOutRecyclerAdapter
import com.sunmi.Activities.LoginActivity
import com.sunmi.Activities.MainActivity
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.row_item_receipt.view.*


/**
 * Created by codemagnus on 4/6/18.
 */
open class ReceiptItemRecyclerAdapter(private val mContext:Context):RecyclerView.Adapter<ReceiptItemRecyclerAdapter.RViewHolder>() {

    private val TAG2 = "#####################"
    private var mActivity: MainActivity? = null

    init {
        mActivity = mContext as MainActivity?
    }

    override fun onBindViewHolder(holder: RViewHolder?, position: Int) {
        holder?.onBindReceiptItemHolder(position)
    }

    override fun getItemCount(): Int {
        return mActivity!!.cart.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.row_item_receipt,parent, false)
        return RViewHolder(v)
    }

    open inner class RViewHolder(v:View):RecyclerView.ViewHolder(v) {

        fun onBindReceiptItemHolder(position: Int) {

            val product = mActivity!!.cart[position]

            itemView.receipt_itemNo.text = (position+1).toString()
            itemView.receipt_itemName.text = product.name
            itemView.receipt_itemCount.text = product.qty.toString()
            itemView.receipt_itemPrice.text = String.format ("%.2f", product.price.toFloat())
            itemView.receipt_itemcPrice.text = String.format ("%.2f", (product.qty*product.price).toFloat())

        }

    }
}