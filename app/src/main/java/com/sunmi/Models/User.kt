package com.example.codemagnus.newproject.Models

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by codemagnus on 3/19/18.
 */
class User {
    var id = 0
    var firstname = ""
    var lastname = ""
    var storename = ""
    var username = ""
    var password = ""
    var token = ""

    constructor(storename: String, username: String, password: String) {
        this.storename = storename
        this.username = username
        this.password = password
    }

    constructor()

    constructor(id: Int, firstname: String, lastname: String, storename: String, username: String, password: String, token: String) {
        this.id = id
        this.firstname = firstname
        this.lastname = lastname
        this.storename = storename
        this.username = username
        this.password = password
        this.token = token
    }

    constructor(jsonObject: JSONObject){

        id = try {
            jsonObject.getInt("id")
        }catch (e: JSONException){
            0
        }

        firstname = try {
            jsonObject.getString("firstName")
        }catch (e: JSONException){
            ""
        }


        lastname = try {
            jsonObject.getString("lastName")
        }catch (e: JSONException){
            ""
        }

        storename = try {
            jsonObject.getString("storeName")
        }catch (e: JSONException){
            ""
        }

        username = try {
            jsonObject.getString("username")
        }catch (e: JSONException){
            ""
        }

        password = try {
            jsonObject.getString("password")
        }catch (e: JSONException){
            ""
        }


        token = try {
            jsonObject.getString("token")
        }catch (e: JSONException){
            ""
        }
    }

    override fun toString(): String {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", id)
            jsonObject.put("firstName", firstname)
            jsonObject.put("lastName", lastname)
            jsonObject.put("username", username)
            jsonObject.put("token", token)
        }catch (e: JSONException){
            e.printStackTrace()
        }

        return jsonObject.toString()
    }


}