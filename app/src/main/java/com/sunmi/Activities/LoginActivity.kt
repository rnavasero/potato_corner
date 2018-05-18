package com.sunmi.Activities

/**
 * Created by codemagnus on 4/5/18.
 */
import android.Manifest
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.codemagnus.newproject.Session.Session
import com.mycart.advance.https.API
import com.mycart.advance.https.APIRequest
import com.mycart.advance.https.APIRequest.post
import com.sunmi.printerhelper.R
import com.sunmi.printerhelper.R.string.email
import com.sunmi.printerhelper.R.string.password
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.Charset

/**
 * Created by codemagnus on 3/19/18.
 */
class LoginActivity:AppCompatActivity() {

    //private var mAuthTask: UserLoginTask? = null
    private var session:Session? = null
    val TAG = LoginActivity::class.java.simpleName
    private val CONTACTS_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //requestContactsPermission()

        session = Session(this)
        if (session?.isUserLogin()!!){
            startNewActivity()
        }

        //var loginImage = layoutInflater.inflate(R.layout.layout_arrow_right, null)

        includedLayout.setOnClickListener {
            attemptLogin()
        }
    }

    private fun postLogin(email:String, password:String) {
        val params:MutableMap<String, String> = HashMap()
        params["username"] = email
        params["password"] = password
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
                //Toast.makeText(this@LoginActivity, "SUCCESSFULLY LOGGED-IN", Toast.LENGTH_SHORT).show()

            }

            override fun didUrlError(error: VolleyError) {

                pDialog.dismiss()
                Toast.makeText(this@LoginActivity,"Network Error", Toast.LENGTH_SHORT).show()

            }
        })
    }
    private fun attemptLogin() {


        // Reset errors.
        et_login_username.error = null
        et_login_password.error = null

        // Store values at the time of the login attempt.
        val emailStr = et_login_username.text.toString()
        val passwordStr = et_login_password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            et_login_password.error = "This field is required"
            //focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            et_login_username.error = "This field is required"
            //focusView = email
            cancel = true
        }

//        else if (!isEmailValid(emailStr)) {
//            email.error = getString(R.string.error_invalid_email)
//            focusView = email
//            cancel = true
//        }
        postLogin(emailStr,passwordStr)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@LoginActivity)
        builder.setIcon(R.drawable.pclogonew)
        builder.setTitle("OMS-ECL-Test")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->

            super.onBackPressed()

        })
        builder.setNegativeButton("Not Now", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
        val dialog = builder.create()
        dialog.show()

    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

//    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {
//
//        override fun doInBackground(vararg params: Void): Boolean? {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                postLogin(mEmail,mPassword)
//                Thread.sleep(2000)
//                return true
//            } catch (e: InterruptedException) {
//                return false
//            }
//
//
//        }
//
//        override fun onPostExecute(success: Boolean?) {
//            mAuthTask = null
//            showProgress(false)
//
//            if (success!!) {
//                finish()
//            } else {
//                password.error = getString(R.string.error_incorrect_password)
//                password.requestFocus()
//            }
//        }
//
//        override fun onCancelled() {
//            mAuthTask = null
//            showProgress(false)
//        }
//    }

//    private fun showProgress(show: Boolean) {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
//
//            login_form.visibility = if (show) View.GONE else View.VISIBLE
//            login_form.animate()
//                    .setDuration(shortAnimTime)
//                    .alpha((if (show) 0 else 1).toFloat())
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            login_form.visibility = if (show) View.GONE else View.VISIBLE
//                        }
//                    })
//
//            login_progress.visibility = if (show) View.VISIBLE else View.GONE
//            login_progress.animate()
//                    .setDuration(shortAnimTime)
//                    .alpha((if (show) 1 else 0).toFloat())
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
//                        }
//                    })
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            login_progress.visibility = if (show) View.VISIBLE else View.GONE
//            login_form.visibility = if (show) View.GONE else View.VISIBLE
//        }
//    }

    private fun startNewActivity(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    fun requestContactsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {

            AlertDialog.Builder(this)
                    .setTitle("Permission Request")
                    .setMessage("This permission allows auto-fill of login fields")
                    .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(this@LoginActivity,
                                arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_PERMISSION_CODE)
                    })
                    .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                    .create().show()

        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == CONTACTS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
