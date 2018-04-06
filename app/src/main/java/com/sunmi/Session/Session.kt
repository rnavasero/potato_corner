package com.example.codemagnus.newproject.Session

/**
 * Created by codemagnus on 3/21/18.
 */
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.codemagnus.newproject.Models.User
//import com.mycart.advance.models.User
import org.json.JSONObject

/**
 * Created by CDI on 2/27/18
 */
class Session (context: Context){

    private val sharedPreferences:SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(USER_PREFERENCE, MODE_PRIVATE)
        editor            = sharedPreferences.edit()
    }

    fun authorize(raw: String){
        editor.putString(USER_OBJECT, raw).apply()
        setUserLogIn(true)
    }

    fun user(): User {
        val jsonObject = JSONObject(sharedPreferences.getString(USER_OBJECT, "")).getJSONObject("data").getJSONArray("items").getJSONObject(0)
        return User(jsonObject)
    }

    fun setToken(token:String){
        editor.putString(USER_TOKEN, token).apply()
    }

    fun getToken():String{
        return sharedPreferences.getString(USER_TOKEN,"")
    }

    private fun setUserLogIn(isLogin: Boolean){
        editor.putBoolean(USER_LOGIN, isLogin).apply()
    }

    fun isUserLogin(): Boolean{
        return sharedPreferences.getBoolean(USER_LOGIN, false)
    }

    fun deAuthourize(){
        editor.clear().apply()
    }

    companion object {
        const val USER_PREFERENCE = "com.mycart.advance"
        const val USER_OBJECT     = "userObject"
        const val USER_LOGIN      = "userLogin"
        var USER_TOKEN            = "userKey"
    }
}