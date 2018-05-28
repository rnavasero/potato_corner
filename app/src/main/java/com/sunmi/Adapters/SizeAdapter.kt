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
import com.squareup.picasso.Picasso
import com.sunmi.Activities.MainActivity
import com.sunmi.Models.Product
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.layout_size.view.*
import kotlinx.android.synthetic.main.size_content.view.*

/**
 * Created by codemagnus on 3/20/18.
 */
class SizeAdapter(val mContext:Context,var categoryId:Int?, var pName:String?, var imgUrl:Int?):RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    private val TAG2 = "###"
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

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show()
        }

        override fun onLongClick(v: View?): Boolean {
            Toast.makeText(mContext, "Long Clicked", Toast.LENGTH_SHORT).show()
            return true
        }

        private val c = categoryId
        private val n = pName
        private val i = imgUrl



        fun onBindItem(position: Int, mContext: Context){

            val item = itemList[position]
            val size = item.size
            val image = item.imgUrl

            Picasso.with(mContext).load(item.imgUrl).resize(150,150   ).centerCrop().into(itemView.iv_item_content2)

            if(item.qty >0){
                itemView.cv_item2.item_count2.visibility = View.VISIBLE
                itemView.cv_item2.item_count2.text = item.qty.toString()
            }else
                itemView.cv_item2.item_count2.visibility = View.GONE

            itemView.cv_item2.setOnClickListener {
                itemView.cv_item2.item_count2.visibility = View.VISIBLE
                Toast.makeText(mContext, "Short Clicked!", Toast.LENGTH_SHORT).show()
                item.qty += 1
                itemView.cv_item2.item_count2.text = item.qty.toString()
                mActivity!!.setCartCount(mActivity!!.productCount + 1)
                updated(item)
            }

            itemView.cv_item2.setOnLongClickListener {
                Toast.makeText(mContext, "Long Clicked!", Toast.LENGTH_SHORT).show()
                item.qty -= 1
                itemView.cv_item2.item_count2.text = item.qty.toString()
                if(item.qty < 1){
                    item.qty = 0
                    itemView.cv_item2.item_count2.visibility = View.GONE
                }
                mActivity!!.setCartCount(mActivity!!.productCount - 1)
                updated(item)
                return@setOnLongClickListener true
            }


//
//            itemView.cv_item2.setOnClickListener{
//
//                when (item.size){
//                    "Regular"->{
//                        item.id = "${itemID.toString()}1"
//                        item.category = "Flavored Fries"
//                        item.flavor = f!!
//                        item.name = c!!
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//
//                    }
//
//                    "Large"->{
//                        item.id = "${itemID.toString()}2"
//                        item.category = c!!
//                        item.flavor = f!!
//                        item.name = c
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//                    }
//
//                    "Jumbo"->{
//                        item.id = "${itemID.toString()}3"
//                        item.category = c!!
//                        item.flavor = f!!
//                        item.name = c
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//                    }
//
//                    "Mega"->{
//                        item.id = "${itemID.toString()}4"
//                        item.category = c!!
//                        item.flavor = f!!
//                        item.name = c
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//                    }
//
//                    "Giga"->{
//                        item.id = "${itemID.toString()}5"
//                        item.category = c!!
//                        item.flavor = f!!
//                        item.name = c
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//                    }
//
//                    "Terra"->{
//                        item.id = "${itemID.toString()}6"
//                        item.category = c!!
//                        item.flavor = f!!
//                        item.name = c
//                        item.qty = 1
//                        item.size = size
//                        item.imgUrl = image
//                        SizeSelectionFragment().newInstance(item)
//                    }
//                }
//
//
//                Log.i(TAG2,item.id)
//                checkProduct(item)
//                if(mActivity!!.itemCheck){
//                    updateCart(item)
//                }
//                else
//                {
//                    mActivity!!.cart.add(Product(item.id, item.sku, item.name, item.category, item.size,item.flavor,item.price,R.drawable.cheese,item.qty))
//                    mActivity!!.setCartCount(mActivity!!.productCount+1)
//                }
//                SizeSelectionFragment().newInstance(item)
//                mActivity!!.setCartState(true)
//                mActivity!!.newFragment(CheckOutFragment(),CheckOutFragment.TAG)
//                mActivity!!.itemCheck = false
//
//            }




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

        private fun updated(product: Product){
//            itemView.et_product_qty.text           = product.qty.toString()
//            itemView.img_minus_product.visibility  = if (product.qty > 0) View.VISIBLE else View.GONE
//            itemView.et_product_qty.visibility     = if (product.qty > 0) View.VISIBLE else View.GONE

            Thread(Runnable {
                if (product.qty > 0){
                    if (!mActivity!!.cart.contains(product)){
                        if (product.qty == 1){
                            mActivity!!.cart.add(product)
                        }else{
                            (0 until mActivity!!.cart.size)
                                    .filter { mActivity!!.cart[it].id == product.id }
                                    .forEach {
                                        mActivity!!.cart[it] = product
                                    }
                        }
                    }
                }else{
                    if (mActivity!!.cart.contains(product)){
                        mActivity!!.cart.remove(product)
                    }
                }
            }).start()
        }



    }
}