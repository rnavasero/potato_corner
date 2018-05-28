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
class CartProduct {

    constructor()
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.PRODUCT_ID)
    var pid: Int = 0

    @ColumnInfo(name = Constants.ITEM_ID)
    var id: String = ""

    @ColumnInfo(name = Constants.PRODUCT_SKU)
    var sku: String = ""

    @ColumnInfo(name = Constants.PRODUCT_NAME)
    var name: String = ""

    @ColumnInfo(name = Constants.PRODUCT_CATEGORY)
    var category:String = ""

    @ColumnInfo(name = Constants.PRODUCT_SIZE)
    var size:String = ""

    @ColumnInfo(name = Constants.PRODUCT_FLAVOR)
    var flavor:String = ""

    @ColumnInfo(name = Constants.PRODUCT_PRICE)
    var price: Double = 0.00

    @ColumnInfo(name = Constants.PRODUCT_IMAGE)
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





    constructor(product_id:Int,id: String, sku: String, name: String, category: String, size: String, flavor: String, price: Double, imgUrl: Int, qty: Int) {
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

    constructor(id: String, sku: String, name: String, category: String, size: String, flavor: String, price: Double, imgUrl: Int) {
        this.id = id
        this.sku = sku
        this.name = name
        this.category = category
        this.size = size
        this.flavor = flavor
        this.price = price
        this.imgUrl = imgUrl
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