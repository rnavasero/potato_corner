package com.example.codemagnus.newproject.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.example.codemagnus.newproject.Fragments.SizeSelectionFragment
import com.example.codemagnus.newproject.Models.Product
import com.squareup.picasso.Picasso
import com.sunmi.Activities.MainActivity
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.dialog_confirm_checkout.*
import kotlinx.android.synthetic.main.size_content.view.*

/**
 * Created by codemagnus on 3/20/18.
 */
class SizeAdapter(val mContext:Context,var category:String?, var flavor:String?, var itemID:String?):RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    private val TAG2 = "#####################"
    var mActivity: MainActivity? = null
    var itemList:MutableList<Product> = mutableListOf()

    init {
        mActivity = mContext as MainActivity?
        itemList = mActivity!!.staticData
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.size_content,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.onBindItem(position,mContext)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    open inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private var itemExist = false

        private val c = category
        private val f = flavor
        private val id = itemID



        fun onBindItem(position: Int, mContext: Context){

            val item = itemList[position]
            val size = item.size
            val image = item.imgUrl


                    Picasso.with(mContext).load(item.imgUrl).resize(150,150   ).centerCrop().into(itemView.iv_item_content2)

            itemView.cv_item2.setOnClickListener{

                when (item.size){
                    "Regular"->{
                        item.id = "${itemID.toString()}1"
                        item.category = "Flavored Fries"
                        item.flavor = f!!
                        item.name = c!!
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)

                    }

                    "Large"->{
                        item.id = "${itemID.toString()}2"
                        item.category = c!!
                        item.flavor = f!!
                        item.name = c
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)
                    }

                    "Jumbo"->{
                        item.id = "${itemID.toString()}3"
                        item.category = c!!
                        item.flavor = f!!
                        item.name = c
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)
                    }

                    "Mega"->{
                        item.id = "${itemID.toString()}4"
                        item.category = c!!
                        item.flavor = f!!
                        item.name = c
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)
                    }

                    "Giga"->{
                        item.id = "${itemID.toString()}5"
                        item.category = c!!
                        item.flavor = f!!
                        item.name = c
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)
                    }

                    "Terra"->{
                        item.id = "${itemID.toString()}6"
                        item.category = c!!
                        item.flavor = f!!
                        item.name = c
                        item.qty = item.qty
                        item.size = size
                        item.imgUrl = image
                        SizeSelectionFragment().newInstance(item)
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