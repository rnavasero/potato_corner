package com.sunmi.Activities

/**
 * Created by codemagnus on 4/5/18.
 */
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.codemagnus.newproject.Adapters.ProductAdapter
import com.example.codemagnus.newproject.Fragments.CheckOutFragment
import com.example.codemagnus.newproject.Fragments.SuccessFragment
import com.example.codemagnus.newproject.Models.Product
import com.example.codemagnus.newproject.Models.ProductDataBase
import com.example.codemagnus.newproject.Models.StaticData
import com.example.codemagnus.newproject.Models.StaticSizeData
import com.example.codemagnus.newproject.Session.Session
import com.mycart.advance.https.API
import com.mycart.advance.https.APIRequest
import com.mycart.advance.https.APIRequest.changePass
import com.sunmi.printerhelper.R
import com.sunmi.printerhelper.utils.AidlUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.changepass.*
import kotlinx.android.synthetic.main.changepass.view.*
import kotlinx.android.synthetic.main.dialog_confirm_checkout.view.*
import kotlinx.android.synthetic.main.layout_cart.view.*
import kotlinx.android.synthetic.main.menu.*
import org.json.JSONObject
import java.nio.charset.Charset
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val TAG2 = "#####################"

    var menu: View? = null
    var cartmenu: View? = null
    var cart: MutableList<Product> = ArrayList()
    private var cartView: View? = null
    var fm: FragmentManager? = null
    var session: Session? = null
    var productDB: ProductDataBase? = null
    var productCount = 0
    var staticData: MutableList<Product> = mutableListOf()
    var sData:MutableList<Product> = mutableListOf()
    var sData2:MutableList<Product> = mutableListOf()
    var sData3:MutableList<Product> = mutableListOf()
    var currentPass = ""
    var newPass = ""
    var cnewPass = ""

    private var isAidl: Boolean = false

    fun isAidl(): Boolean {
        return isAidl
    }

    fun setAidl(aidl: Boolean) {
        isAidl = aidl
    }


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isAidl = true
        AidlUtil.getInstance().connectPrinterService(this)


        fm = supportFragmentManager
        staticData = StaticSizeData.getlists()
        sData = StaticData.getlists4()
        sData2 = StaticData.getlists2()
        sData3 = StaticData.getlists3()
        setSupportActionBar(mToolbar)

        menu = layoutInflater.inflate(R.layout.menu, null)
        mToolbar.addView(menu, 0, Toolbar.LayoutParams(Gravity.END))
        cartmenu = layoutInflater.inflate(R.layout.layout_cart, null)
        mToolbar.addView(cartmenu, 1, Toolbar.LayoutParams(Gravity.END))

        cartmenu?.setOnClickListener {
            setToolbar(false, "")
            fm!!.beginTransaction().apply {
                replace(R.id.main_frame, CheckOutFragment(), CheckOutFragment.TAG)
                addToBackStack(CheckOutFragment.TAG)
            }.commit()
        }

        setToolbar(true, "Potato Corner")

        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        rv_main1.layoutManager = GridLayoutManager(this, 4)
        rv_main1.adapter = ProductAdapter(this, StaticData.getlists())

        rv_main2.layoutManager = GridLayoutManager(this, 4)
        rv_main2.adapter = ProductAdapter(this, StaticData.getlists3())

        rv_main3.layoutManager = GridLayoutManager(this, 4)
        rv_main3.adapter = ProductAdapter(this, StaticData.getlists2())


        val menu = PopupMenu(this, menu_view)
        menu.inflate(R.menu.menu_pop_up)
        //menu.show()
        menu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_change_pass -> {

                    val alert = AlertDialog.Builder(this    )
                    val v = LayoutInflater.from(this).inflate(R.layout.changepass, null)
                    alert.setView(v)
                    val dialog = alert.create()

                    v.btn_confirm.setOnClickListener{
                        val cp = v.et_current_password.text.toString()
                        val np = v.et_new_password.text.toString()
                        val cnp = v.et_cnew_password.text.toString()

                        if (v.et_current_password.text.toString().isEmpty()){
                            v.input_current_password.error = "This field is required"
                            return@setOnClickListener
                        }

                        if (v.et_new_password.text.toString().isEmpty()){
                            v.input_new_password.error = "This field is required"
                            return@setOnClickListener
                        }

                        if (v.et_cnew_password.text.toString().isEmpty()){
                            v.input_cnew_password.error = "This field is required"
                            return@setOnClickListener
                        }

                        if(v.et_new_password.text.toString()!= v.et_cnew_password.text.toString()){
                            v.input_cnew_password.error = "Password mismatch!"
                        }

0
                        val params:MutableMap<String, String> = HashMap()
                        params["currentPassword"] = cp
                        params["newPassword"] = np
                        params["confirmPassword"] = cnp

                        APIRequest.changePass(this, API.CHANGEPASS, params, object : APIRequest.URLCallback{
                            override fun didUrlResponse(response: String) {
                                dialog.dismiss()
                            }

                            override fun didUrlError(error: VolleyError) {

                            }

                        })


                    }

                    dialog.show()

                }

                R.id.menu_logout -> {

                    if (cart.isNotEmpty()) {
                        val alert = AlertDialog.Builder(this)
                        alert.setTitle("You have pending transaction!")
                        alert.setMessage("Are you sure you want to logout?")
                        alert.setNegativeButton("No", { _, _ ->

                        })
                        alert.setPositiveButton("Yes", { _, _ ->
                            logout()
                        }).show()

                    }else
                        logout()
                }
            }
            true

        }
        menu_view.setOnClickListener {
            menu.show()
        }


    }

    override fun onBackPressed() {

        if(fm?.backStackEntryCount == 2)
        {
            super.onBackPressed()
        }

        if (fm?.backStackEntryCount == 0) {
            if (cart.isNotEmpty()) {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("You have pending transaction!")
                alert.setMessage("Are you sure you want to logout?")
                alert.setNegativeButton("No", { _, _ ->

                })
                alert.setPositiveButton("Yes", { _, _ ->
                    logout()
                }).show()

            } else {

                val alert = AlertDialog.Builder(this)
                alert.setTitle("Exit to Login Page")
                alert.setMessage("Are you sure you want to logout?")
                alert.setNegativeButton("No", { _, _ ->

                })
                alert.setPositiveButton("Yes", { _, _ ->
                    logout()
                }).show()

            }
        } else {
            setToolbar(true, "Potato Corner")
            super.onBackPressed()
        }
    }

    private fun logout(){

        val pDialog: ProgressDialog = ProgressDialog(this@MainActivity).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        pDialog.show()

        APIRequest.postLogout(this,API.LOGOUT, object : APIRequest.URLCallback{
            override fun didUrlResponse(response: String) {
                Session(this@MainActivity).deAuthourize()
                startNewActivity()
                pDialog.dismiss()
            }

            override fun didUrlError(error: VolleyError) {
                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_SHORT).show()
                pDialog.dismiss()
            }
        })

    }


    fun setToolbar(isMain: Boolean, mTitle: String) {
        title = mTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(!isMain)
        when {isMain -> {
            menu?.visibility = View.VISIBLE
            cartmenu?.visibility = View.VISIBLE
        }
            else -> {
                menu?.visibility = View.GONE
                cartmenu?.visibility = View.GONE
            }
        }
    }

    fun newFragment(fragment: Fragment?, tag: String) {
        fm!!.beginTransaction().apply {
            replace(R.id.main_frame, fragment, tag)
            addToBackStack(tag)
        }.commit()
    }

    fun setCartCount(count: Int) {
        cartmenu?.tv_cart_count?.text = count.toString()
        productCount = count
    }

    override fun onDestroy() {
        super.onDestroy()
        ProductDataBase.destroyInstance()
    }

    fun showRequestError(error: VolleyError) {
        if (error.networkResponse != null) {
            val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
            val errString = JSONObject(err).getJSONArray("errors").getJSONObject(0).getString("message")
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Error")
                setMessage(errString)
                setPositiveButton("Ok", null)
            }.show()
            Log.e("MainActivity", "error: $err")
        }
    }

    private fun startNewActivity(){
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }

    fun isNetworkAvailable(c: Activity):Boolean{
        val state: Boolean
        val cmg = c
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cmg.activeNetworkInfo
        state = activeNetworkInfo != null && activeNetworkInfo.isConnected

        return state

    }

}