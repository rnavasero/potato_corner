package com.sunmi.Activities

/**
 * Created by codemagnus on 4/5/18.
 */
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.VolleyError
import com.example.codemagnus.newproject.Session.Session
import com.mycart.advance.https.API
import com.mycart.advance.https.APIRequest
import com.mycart.advance.https.APIRequest.post
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by codemagnus on 3/19/18.
 */
class LoginActivity:AppCompatActivity() {

    private val storeName: List<String> = listOf("SM-Calamba","SM-Sta.Rosa", "SM-Turbina", "Waltermart-Calamba" )
    private val TAG2 = "#####################"
    private var session: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        session = Session(this)
        if (session?.isUserLogin()!!){
            startNewActivity()
        }

        btn_login.setOnClickListener {
            if (isFieldValid()){
                postLogin()
            }
        }

//        btn_delete_user.setOnClickListener {
//
//        val alert = AlertDialog.Builder(this    )
//        val v = LayoutInflater.from(this).inflate(R.layout.layout_delete_user, null)
//        alert.setView(v)
//        val dialog = alert.create()
//
//        v.btn_confirmDelete.setOnClickListener{
//            val du = v.et_deleteuserID.text.toString()
//
//
//            if (v.et_deleteuserID.text.toString().isEmpty()){
//                v.inputDeleteUse.error = "This field is required"
//                return@setOnClickListener
//            }
//
//            val params:MutableMap<String, String> = java.util.HashMap()
//            params["id"] = du
//            APIRequest.deleteUSER(this, API.DELETEUSER, du, object : APIRequest.URLCallback{
//                override fun didUrlResponse(response: String) {
//                    dialog.dismiss()
//                }
//
//                override fun didUrlError(error: VolleyError) {
//
//                }
//
//            })
//
//
//        }
//            dialog.show()
//        }



    }

    override fun onBackPressed() {


        val alert = AlertDialog.Builder(this)
        alert.setTitle("Exit Application")
        alert.setMessage("Do you really want to exit?")
        alert.setNegativeButton("No", { _, _ ->
        })
        alert.setPositiveButton("Yes", { _, _ ->
            finish()
        }).show()

    }

    private fun postLogin() {
        val params:MutableMap<String, String> = HashMap()
        params["username"] = et_login_username.text.toString()
        params["password"] = et_login_password.text.toString()

        val pDialog: ProgressDialog = ProgressDialog(this@LoginActivity).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        pDialog.show()
        post(this, API.LOGIN, params, object : APIRequest.URLCallback{
            override fun didUrlResponse(response: String) {


                Session(this@LoginActivity).authorize(response)
                startNewActivity()
                pDialog.dismiss()
                Toast.makeText(this@LoginActivity, "SUCCESSFULLY LOGGED-IN", Toast.LENGTH_SHORT).show()

            }

            override fun didUrlError(error: VolleyError) {

                pDialog.dismiss()
                Toast.makeText(this@LoginActivity,"Network Error", Toast.LENGTH_SHORT).show()

            }
        })
    }



    private fun isFieldValid(): Boolean{


        if (TextUtils.isEmpty(et_login_username.text.toString())){
            input_login_username.error = "Username is required"
            return false
        }

        input_login_username.error = null

        if (TextUtils.isEmpty(et_login_password.text.toString())){
            input_login_password.error = "Password is required"
            return false
        }

        input_login_password.error = null

        return true
    }

    private fun startNewActivity(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

}