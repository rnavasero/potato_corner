package com.example.codemagnus.newproject.Adapters

import android.content.Context
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.sunmi.Activities.MainActivity
import com.sunmi.Models.Product
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.layout_size.view.*
import kotlinx.android.synthetic.main.size_content.view.*

/**
 * Created by codemagnus on 3/22/18.
 */
class SizeSelectAdapter(private val mContext:Context, var _categoryId:Int?, var _pName:String?, var _imgUrl:Int?):RecyclerView.Adapter<SizeSelectAdapter.ViewHolder>() {

    private val TAG2 = "###"

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

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener  {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(view: View) {
            Toast.makeText(mContext, "Short Click", Toast.LENGTH_SHORT).show()
            // Context context = view.getContext();
            // article.getName()
        }

        override fun onLongClick(view: View): Boolean {
            Toast.makeText(mContext,"Long Clicked", Toast.LENGTH_LONG).show()
            // Handle long click
            // Return true to indicate the click was handled
            return true
        }

        private val _c = _categoryId
        private val _i = _imgUrl
        private val _n = _pName


        fun onBindItemHolder(position: Int){

            val item = itemList[position]
            itemView.size_label.text = item.size

            if(item.qty >0){
                itemView.cv_item1.item_count1.visibility = View.VISIBLE
                itemView.cv_item1.item_count1.text = item.qty.toString()
            }else
                itemView.cv_item1.item_count1.visibility = View.GONE

            itemView.cv_item1.setOnClickListener {
                itemView.cv_item1.item_count1.visibility = View.VISIBLE
                Toast.makeText(mContext, "Short Clicked!", Toast.LENGTH_SHORT).show()
                item.qty += 1
                mActivity!!.setCartCount(mActivity!!.productCount + 1)
                updated(item)
            }

            itemView.cv_item1.setOnLongClickListener {
                Toast.makeText(mContext, "Long Clicked!", Toast.LENGTH_SHORT).show()
                item.qty -= 1
                if(item.qty < 1){
                    item.qty = 0
                    itemView.cv_item1.item_count1.visibility = View.GONE
                }
                mActivity!!.setCartCount(mActivity!!.productCount - 1)
                updated(item)
                return@setOnLongClickListener true
            }

//            itemView.ll_itemSize.setOnClickListener {
//
//                    when (item.size){
//
//                        "Small"->
//                        {
//                            item.id = "${itemID.toString()}1"
//                            item.category = ""
//                            item.name = _n!!
//                            item.qty = 1
//                            item.flavor = item.size
//                            item.imgUrl = _i!!
//                            item.price = 10.00
//                        }
//
//                        "Medium"->
//                        {
//                            item.id = "${itemID.toString()}2"
//                            item.category = ""
//                            item.name = _n!!
//                            item.qty = 1
//                            item.flavor = item.size
//                            item.imgUrl = _i!!
//                            item.price = 15.00
//                        }
//
//                        "Large"->
//                        {
//                            item.id = "${itemID.toString()}3"
//                            item.category = ""
//                            item.name = _n!!
//                            item.qty = 1
//                            item.flavor = item.size
//                            item.imgUrl = _i!!
//                            item.price = 20.00
//                        }
//
//                    }
//                mActivity!!.cart.add(Product(item.id, item.sku, item.name, item.category, item.size,item.flavor,item.price,item.imgUrl,item.qty))
//                mActivity!!.setCartCount(mActivity!!.productCount + 1)
//                mActivity!!.newFragment(CheckOutFragment(),CheckOutFragment.TAG)
//                mActivity!!.setCartState(true)
//            }

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