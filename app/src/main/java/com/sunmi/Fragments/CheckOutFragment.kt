package com.example.codemagnus.newproject.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.VolleyError
import com.example.codemagnus.newproject.Adapters.CheckOutRecyclerAdapter
import com.example.codemagnus.newproject.Session.Session
import com.mycart.advance.https.API
import com.mycart.advance.https.APIRequest
import com.sunmi.Activities.MainActivity
import com.sunmi.Fragments.FragmentReceipt
import com.sunmi.printerhelper.R
import com.sunmi.printerhelper.utils.AidlUtil
import kotlinx.android.synthetic.main.dialog_confirm_checkout.view.*
import kotlinx.android.synthetic.main.fragment_check_out.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by codemagnus on 3/21/18.
 */
class CheckOutFragment: Fragment() {

    val TAG2 = "#####################"
    internal lateinit var bitmap: Bitmap
    private var mActivity: MainActivity? = null
    private var isChecked = false
    private var mTotal = ""
    private var adapter: CheckOutRecyclerAdapter? = null

    companion object {
        val TAG:String = CheckOutFragment::class.java.simpleName
        var instance:CheckOutFragment? = CheckOutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AidlUtil.getInstance().initPrinter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        instance = this
        return inflater?.inflate(R.layout.fragment_check_out,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = context as MainActivity?
        mActivity?.setToolbar(false, getString(R.string.checkout))
        setTotalPrice()

        if (mActivity?.cart!!.isNotEmpty()){
            tv_empty_cart.visibility    = View.GONE
            img_cart.visibility         = View.GONE
            adapter                     = CheckOutRecyclerAdapter(context)
            rv_check_out.layoutManager  = LinearLayoutManager(context)
            rv_check_out.adapter        = adapter
        }

        btn_checkout.setOnClickListener {
            if (mActivity?.cart!!.isEmpty()){
                Toast.makeText(context, getString(R.string.no_item_to_check_out), Toast.LENGTH_SHORT).show()
            }else{
                val alert = AlertDialog.Builder(context)
                val v = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_checkout, null)
                alert.setView(v)

                v.tv_confirm_total_price.text = mTotal

                val dialog = alert.create()
                v.btn_check_now.setOnClickListener{
                    mActivity!!.newFragment(FragmentReceipt(),FragmentReceipt.TAG)
                    postCheckOut()
                    Handler().postDelayed({
                        mActivity!!.newFragment(SuccessFragment(),SuccessFragment.TAG)
                    }, 1000)

                    dialog.dismiss()
                    mActivity!!.removeFragment(FragmentReceipt())


                }
                dialog.show()
            }
        }


    }

    private fun postCheckOut() {

        try {
            val jsonObj     = JSONObject()
            val jsonArray   = JSONArray()

            for (i in 0 until mActivity?.cart!!.size){
                val product = mActivity?.cart!![i]
                jsonObj.put("productId", product.id)
                jsonObj.put("quantity", product.qty)
                jsonArray.put(jsonObj)
            }

            val items = JSONObject()
            items.put("items", jsonArray)

            val map:HashMap<String, String> = HashMap()
            map["data"] = jsonObj.toString()
            map["id"]   = mActivity?.session?.user()?.id.toString()

            val mapHeader:HashMap<String, String> = HashMap()
            mapHeader["x-access-token"] = Session(context).getToken()

            APIRequest.postWithToken(context, API.CHECKOUT, mapHeader, map, object : APIRequest.URLCallback{
                override fun didUrlResponse(response: String) {
                    Log.i(TAG, "checkout: $response")
                    val json = JSONObject(response)
                    if (json.getBoolean("success")){
                        Toast.makeText(context,"Transaction Successfully Submitted!",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun didUrlError(error: VolleyError) {
                    mActivity?.showRequestError(error)
                }

            })
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun setTotalPrice(){
        val total = (0 until mActivity?.cart!!.size)
                .map { mActivity?.cart!![it].qty * mActivity?.cart!![it].price }
                .sum()

        mTotal = String.format ("P %.2f", total.toFloat())

        btn_checkout.isEnabled = total > 0
        tv_checkout_total_price.text = mTotal
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}