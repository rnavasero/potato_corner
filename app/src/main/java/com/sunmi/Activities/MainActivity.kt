package com.sunmi.Activities

/**
 * Created by codemagnus on 4/5/18.
 */
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.Toolbar
import android.text.TextUtils.replace
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
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
import com.sunmi.Fragments.FragmentReceipt
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val TAG2 = "#####################"

    var menu: View? = null
    var cartmenu: View? = null
    var cart: MutableList<Product> = mutableListOf()
    private var cartView: View? = null
    var fm: FragmentManager? = null
    var session: Session? = null
    var productDB: ProductDataBase? = null
    var productCount = 0
    var staticData: MutableList<Product> = mutableListOf()
    var sData:MutableList<Product> = mutableListOf()
    var sData2:MutableList<Product> = mutableListOf()
    var sData3:MutableList<Product> = mutableListOf()
    var boolean_permission: Boolean = false
    val REQUEST_PERMISSIONS = 1
    var itemCheck:Boolean = false


    private var isAidl: Boolean = false

    fun isAidl(): Boolean {
        return isAidl
    }

    fun setAidl(aidl: Boolean) {
        isAidl = aidl
    }



    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isAidl = true
        AidlUtil.getInstance().connectPrinterService(this)
        fn_permission()

//        btn_View.setOnClickListener {
//            val intent1 = Intent(applicationContext, PDFViewActivity::class.java)
//            startActivity(intent1)
//        }


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
//
        val f = supportFragmentManager.findFragmentById(R.id.main_frame)
        if (f is SuccessFragment) {
            if(fm!!.backStackEntryCount == 2) {
                fm!!.beginTransaction().remove(SuccessFragment()).commitAllowingStateLoss()
            }
            else if(fm!!.backStackEntryCount >2){
                fm!!.beginTransaction().remove(SuccessFragment()).commitAllowingStateLoss()
                super.onBackPressed()
            }
        }

        if(fm!!.backStackEntryCount == 2)
        {
            super.onBackPressed()
            setToolbar(true, "Potato Corner")
        }

        if(fm!!.backStackEntryCount >2)
        {
            super.onBackPressed()
            super.onBackPressed()
            setToolbar(true, "Potato Corner")
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
                alert.setTitle("Confirm Logout")
                alert.setMessage("Are you sure you want to logout?")
                alert.setNegativeButton("No", { _, _ ->

                })
                alert.setPositiveButton("Yes", { _, _ ->
                    logout()
                }).show()

            }
        }
        else{
            super.onBackPressed()
            setToolbar(true, "Potato Corner")
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

    fun addnewFragment(fragment: Fragment?, tag: String) {
        fm!!.beginTransaction().apply {
            replace(R.id.main_frame, fragment, tag)
            //addToBackStack(tag)
        }.commit()
    }

    fun removeFragment(fragment: Fragment?){
        fm!!.beginTransaction().remove(fragment).commitAllowingStateLoss()
        fm!!.popBackStack()

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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun fn_permission() {
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_PERMISSIONS)

            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_PERMISSIONS)

            }
        } else {
            boolean_permission = true


        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Toast.makeText(applicationContext, "Permission granted", Toast.LENGTH_LONG).show()
                boolean_permission = true


            } else {
                Toast.makeText(applicationContext, "Please allow the permission", Toast.LENGTH_LONG).show()

            }
        }
    }

    @SuppressLint("SdCardPath")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun createPdf(bitmap:Bitmap) {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val displaymetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val hight = displaymetrics.heightPixels.toFloat()
        val width = displaymetrics.widthPixels.toFloat()

        val convertHighet = hight.toInt()
        val convertWidth = width.toInt()

        //        Resources mResources = getResources();
        //        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create()
        val page = document.startPage(pageInfo)

        val canvas = page.canvas


        val paint = Paint()
        paint.color = Color.parseColor("#ffffff")
        canvas.drawPaint(paint)



        //bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true)

        paint.color = Color.BLUE
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        document.finishPage(page)


        // write the document content
        val targetPdf = "/sdcard/test.pdf"
        val filePath = File(targetPdf)
        document.writeTo(FileOutputStream(filePath))

        // close the document
        document.close()
    }


}