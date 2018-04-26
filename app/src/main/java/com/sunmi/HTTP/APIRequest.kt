package com.mycart.advance.https

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.codemagnus.newproject.Session.Session
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

@Suppress("DEPRECATION")
/**
 * Created by CDI on 2/27/1*
 */
object APIRequest {


    fun postWithToken(context: Context, url:String, token:MutableMap<String, String>, params:MutableMap<String, String>, urlCallback: URLCallback){

        val pDialog: ProgressDialog = ProgressDialog(context).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

        pDialog.show()

        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            pDialog.dismiss()
            urlCallback.didUrlResponse(response)
        }, Response.ErrorListener {error ->
            urlCallback.didUrlError(error)
            pDialog.dismiss()

            val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
            Toast.makeText(context, err, Toast.LENGTH_SHORT).show()

        }){
            override fun getHeaders(): MutableMap<String, String> {
                return if (token.isEmpty()){
                    super.getHeaders()
                }else{
                    token
                }
            }

            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        queue.add(request)
    }

    fun changePass(context: Context, url:String, params:MutableMap<String, String>, urlCallback: URLCallback){

        val pDialog: ProgressDialog = ProgressDialog(context).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

        pDialog.show()

        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            pDialog.dismiss()
            urlCallback.didUrlResponse(response)
            Toast.makeText(context,"PASSWORD CHANGED", Toast.LENGTH_LONG).show()
        }, Response.ErrorListener {error ->
            urlCallback.didUrlError(error)
            pDialog.dismiss()

            Toast.makeText(context, "Invalid Current Password", Toast.LENGTH_SHORT).show()

        }){
            override fun getHeaders(): MutableMap<String, String> {
                val token:MutableMap<String, String> = HashMap()
                token["x-access-token"] = Session(context).getToken()
                return token
            }

            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        queue.add(request)
    }

    fun deleteUSER(context: Context, url:String, du:String, urlCallback: URLCallback){

        val pDialog: ProgressDialog = ProgressDialog(context).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

        pDialog.show()

        val queue =  Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.DELETE, url+du, Response.Listener { response ->
            pDialog.dismiss()
            urlCallback.didUrlResponse(response)
            Toast.makeText(context,"USER DELETED", Toast.LENGTH_LONG).show()
        }, Response.ErrorListener {error ->
            urlCallback.didUrlError(error)
            pDialog.dismiss()

            Toast.makeText(context, "Invalid Parameter", Toast.LENGTH_SHORT).show()

        }){
            override fun getHeaders(): MutableMap<String, String> {
                val token:MutableMap<String, String> = HashMap()
                token["x-access-token"] = Session(context).getToken()
                return token
            }

        }
        queue.add(request)
    }

    fun post(context: Context, url:String, params:MutableMap<String, String>, urlCallback: URLCallback){


        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->

            try {
                val jsonResponse 	=  JSONObject(response)
                val jsonData       	=  jsonResponse.getJSONObject("data")
                val jsonArray       =  jsonData.getJSONArray("items")
                val jsonUser        =  jsonArray.getJSONObject(0)
                val token           =  jsonUser.getString("token")

                Session(context).setToken(token)



            }catch (e: JSONException){
                e.printStackTrace()
            }
            urlCallback.didUrlResponse(response)



        }, Response.ErrorListener {error ->
            urlCallback.didUrlError(error)


            if (error.networkResponse != null){
                val err = String(error.networkResponse.data, Charset.forName("UTF-8"))
                Toast.makeText(context, "INVALID USERNAME/PASSWORD", Toast.LENGTH_SHORT).show()
            }
        }){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        queue.add(request)
    }

    fun get(context: Context, url:String, urlCallback: URLCallback){
        val pDialog: ProgressDialog = ProgressDialog(context).apply {
            setMessage("Please wait...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        pDialog.show()

        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.GET, url, Response.Listener { response ->
            pDialog.dismiss()
            urlCallback.didUrlResponse(response)
        }, Response.ErrorListener {error ->
            pDialog.dismiss()
            urlCallback.didUrlError(error)
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val token:MutableMap<String, String> = HashMap()
                token["x-access-token"] = Session(context).user().token
                return token
            }
        }
        queue.add(request)
    }

    fun postLogout(context: Context, url: String, urlCallback: URLCallback){



        val queue = Volley.newRequestQueue(context)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->

            urlCallback.didUrlResponse(response)
        }, Response.ErrorListener { error ->

            urlCallback.didUrlError(error)
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val token:MutableMap<String, String> = HashMap()
                token["x-access-token"] = Session(context).getToken()
                return token
            }
        }
        queue.add(request)
    }

    interface URLCallback{
        fun didUrlResponse(response: String)
        fun didUrlError(error: VolleyError)
    }
}