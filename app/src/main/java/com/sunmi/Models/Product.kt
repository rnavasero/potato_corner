package com.sunmi.Models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by CDI on 2/23/18
 */
@Entity(tableName = "cartData")
class Product{

    constructor()


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    var pid: Long = 0

    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "sku")
    var sku: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "category")
    var category:String = ""

    @ColumnInfo(name = "size")
    var size:String = ""

    @ColumnInfo(name = "flavor")
    var flavor:String = ""

    @ColumnInfo(name = "price")
    var price: Double = 0.00

    @ColumnInfo(name = "image_path")
    var imgUrl = 0

    var qty: Int = 0



    constructor(jsonObject: JSONObject){
        id = try {
            jsonObject.getString("id")
        }catch (e: JSONException){
            ""
        }

        sku = try {
            jsonObject.getString("sku")
        }catch (e: JSONException){
            ""
        }

        name = try {
            jsonObject.getString("name")
        }catch (e: JSONException){
            ""
        }

        category = try {
            jsonObject.getString("category")
        }catch (e: JSONException){
            ""
        }

        size = try {
            jsonObject.getString("size")
        }catch (e: JSONException){
            ""
        }

        flavor = try {
            jsonObject.getString("flavor")
        }catch (e: JSONException){
            ""
        }

        price = try {
            jsonObject.getDouble("price")
        }catch (e: JSONException){
            0.00
        }

        imgUrl = try {
            jsonObject.getInt("image_path")
        }catch (e: JSONException){
            0
        }
    }



    constructor(product_id:Long,id: String, sku: String, name: String, category: String, size: String, flavor: String, price: Double, imgUrl: Int, qty: Int) {
        this.pid = product_id
        this.id = id
        this.sku = sku
        this.name = name
        this.category = category
        this.size = size
        this.flavor = flavor
        this.price = price
        this.imgUrl = imgUrl
        this.qty = qty
    }

    constructor(id: String, sku: String, name: String, category: String, size: String, flavor: String, price: Double, imgUrl: Int, qty: Int) {
        this.id = id
        this.sku = sku
        this.name = name
        this.category = category
        this.size = size
        this.flavor = flavor
        this.price = price
        this.imgUrl = imgUrl
        this.qty = qty
    }

    override fun toString(): String {
        val jsonObject = JSONObject()

        try {
            jsonObject.put("id", id)
            jsonObject.put("sku", sku)
            jsonObject.put("name", name)
            jsonObject.put("category", category)
            jsonObject.put("size", size)
            jsonObject.put("flavor", flavor)
            jsonObject.put("price", price)
            jsonObject.put("image_path", imgUrl)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonObject.toString()
    }


}