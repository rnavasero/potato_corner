package com.example.codemagnus.newproject.Adapters

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.sunmi.Activities.MainActivity
import com.squareup.picasso.Picasso
import com.sunmi.Models.Product
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.row_check_out.view.*

/**
 * Created by codemagnus on 3/21/18.
 */
class CheckOutRecyclerAdapter(val mContext:Context):RecyclerView.Adapter<CheckOutRecyclerAdapter.ViewHolder>() {

    private val TAG2 = "###"
    var mActivity: MainActivity? = null

    init {
        mActivity = mContext as MainActivity?
    }

    override fun getItemCount(): Int {
        return mActivity!!.cart.size
    }

    fun updateCart(product: Product){
        for (i in 0 until mActivity!!.cart.size){
            if (mActivity!!.cart[i].id == product.id){
                mActivity!!.cart[i].qty = product.qty
                notifyItemChanged(i)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.onBindItemHolder(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.row_check_out,parent, false)
        return ViewHolder(v)
    }

    open inner class ViewHolder(v:View):RecyclerView.ViewHolder(v) {

        fun onBindItemHolder(position: Int){

            val product                         = mActivity!!.cart[position]
            itemView!!.tv_cart_product_name!!.text  = product.name
            itemView.tv_cart_total_price!!.text    = String.format("P %.2f", product.price.toFloat())
            itemView.tv_cart_qty!!.text = product.qty.toString()
            itemView.tv_cart_product_flavor!!.text = product.flavor
            Picasso.with(mContext).load(product.imgUrl).resize(100, 100).centerCrop().into(itemView.img_product_image)

            setProductPrice(product)

            itemView.tv_cart_add.setOnClickListener {
                product.qty += 1
                mActivity?.setCartCount(mActivity!!.productCount + 1)
                setProductPrice(product)
            }

            itemView.tv_cart_minus.setOnClickListener {

                if (product.qty == 1){
                    return@setOnClickListener
                }

                product.qty -= 1

                if (product.qty <= 1){
                    product.qty = 1
                }

                mActivity?.setCartCount(mActivity!!.productCount - 1)
                setProductPrice(product)
            }

            itemView.btn_cart_delete.setOnClickListener {
                AlertDialog.Builder(mContext).apply {
                    setTitle(mContext.getString(R.string.remove_product))
                    setMessage(mContext.getString(R.string.sure_to_remove))
                    setNegativeButton(mContext.getString(R.string.no), null)
                    setPositiveButton(mContext.getString(R.string.yes), { dialogInterface, _ ->
                        dialogInterface.dismiss()
                        mActivity?.setCartCount(mActivity!!.productCount - product.qty)
                        deleteProduct(product)
                        removeProduct()
                        updateCart(product)
                    })
                }.show()
            }

        }

        private fun removeProduct(){
            if (CheckOutFragment.instance != null)
                CheckOutFragment.instance!!.setTotalPrice()

            mActivity?.cart!!.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
        }


        private fun setProductPrice(product: Product) {
            if (CheckOutFragment.instance != null)
                CheckOutFragment.instance!!.setTotalPrice()


            itemView.tv_cart_qty.text           = product.qty.toString()
            itemView.tv_cart_total_price.text   = String.format ("P %.2f", (product.price * product.qty).toFloat())
        }

        private fun deleteProduct(product: Product){
            for (i in 0 until mActivity!!.cart.size){
                if (mActivity!!.cart.get(index = i).id == product.id){
                    mActivity!!.cart.get(index = i).qty = 0
                    notifyItemChanged(i)
                }
            }
        }


    }

}