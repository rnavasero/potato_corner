package com.example.codemagnus.newproject.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.example.codemagnus.newproject.Fragments.SizeSelectionFragment
import com.example.codemagnus.newproject.Models.Product
import com.sunmi.Activities.MainActivity
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.layout_size.view.*

/**
 * Created by codemagnus on 3/22/18.
 */
class SizeSelectAdapter(private val mContext:Context, var _category:String?,var _image:Int?,var _name:String?, private var itemID:String?):RecyclerView.Adapter<SizeSelectAdapter.ViewHolder>() {

    private val TAG2 = "#####################"

    var mActivity: MainActivity? = null
    var itemList:MutableList<Product> = mutableListOf()

    init {
        mActivity = mContext as MainActivity?
        itemList = mActivity!!.sData
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.onBindItemHolder(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.layout_size,parent,false)
        return ViewHolder(v)
    }

    open inner class ViewHolder(v:View):RecyclerView.ViewHolder(v) {

        private val _c = _category
        private val _i = _image
        private val _n = _name
        private val _i_ID = itemID


        fun onBindItemHolder(position: Int){

            val item = itemList[position]
            itemView.tv_size_sample.text = item.size

            itemView.ll_itemSize.setOnClickListener {

                    when (item.size){

                        "Small"->
                        {
                            item.id = "${itemID.toString()}1"
                            item.category = _c!!
                            item.name = _n!!
                            item.qty = 1
                            item.flavor = item.size
                            item.imgUrl = _i!!
                            item.price = 10.00
                        }

                        "Medium"->
                        {
                            item.id = "${itemID.toString()}2"
                            item.category = _c!!
                            item.name = _n!!
                            item.qty = 1
                            item.flavor = item.size
                            item.imgUrl = _i!!
                            item.price = 15.00
                        }

                        "Large"->
                        {
                            item.id = "${itemID.toString()}3"
                            item.category = _c!!
                            item.name = _n!!
                            item.qty = 1
                            item.flavor = item.size
                            item.imgUrl = _i!!
                            item.price = 20.00
                        }

                    }

                Log.i(TAG2,item.id)
                checkProduct(item)
                if(mActivity!!.itemCheck){
                    updateCart(item)
                }
                else{
                    mActivity!!.cart.add(Product(item.id, item.name, item.description, item.imgUrl,item.category,item.flavor,item.size,item.price,item.qty))
                    mActivity!!.setCartCount(mActivity!!.productCount+1)
                }
                SizeSelectionFragment().newInstance(item)
                mActivity!!.newFragment(CheckOutFragment(),CheckOutFragment.TAG)
                Log.i(TAG2,item.toString())
                mActivity!!.itemCheck = false
            }

        }

        private fun checkProduct(product: Product){
            for (i in 0 until mActivity!!.cart.size){
                if (mActivity!!.cart[i].id == product.id){
                    mActivity!!.itemCheck = true
                }
            }
        }

        private fun updateCart(product: Product){
            for (i in 0 until mActivity!!.cart.size){
                if (mActivity!!.cart[i].id == product.id){
                    mActivity!!.cart[i].qty += 1
                    mActivity!!.setCartCount(mActivity!!.productCount+1)
                    notifyItemChanged(i)
                }
            }
        }


    }
}