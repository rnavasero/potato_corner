package com.example.codemagnus.newproject.Models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by CDI on 2/23/18
 */
@Entity(tableName = "cartData")
class Product{

    constructor()

    @PrimaryKey(autoGenerate = true)
    var dbId: Int = 0

    @ColumnInfo(name = "productId")
    var id: String = ""

    @ColumnInfo(name = "productName")
    var name: String = ""

    @ColumnInfo(name = "description")
    var description: String = ""

    @ColumnInfo(name = "imageUrl")
    var imgUrl = 0

    @ColumnInfo(name = "category")
    var category:String = ""

    @ColumnInfo(name = "flavor")
    var flavor:String = ""

    @ColumnInfo(name = "size")
    var size:String = ""

    @ColumnInfo(name = "price")
    var price: Double = 0.00

    @ColumnInfo(name = "qty")
    var qty: Int = 0

    constructor(jsonObject: JSONObject){
        id = try {
            jsonObject.getString("id")
        }catch (e: JSONException){
            ""
        }

        name = try {
            jsonObject.getString("name")
        }catch (e: JSONException){
            ""
        }

        description = try {
            jsonObject.getString("description")
        }catch (e: JSONException){
            ""
        }

        price = try {
            jsonObject.getDouble("price")
        }catch (e: JSONException){
            0.00
        }

        imgUrl = try {
            jsonObject.getInt("imageUrl")
        }catch (e: JSONException){
            0
        }
    }

    constructor(id: String, name: String, description: String, imgUrl: Int, category: String, flavor: String, size: String, price: Double, qty: Int) {
        this.id = id
        this.name = name
        this.description = description
        this.imgUrl = imgUrl
        this.category = category
        this.flavor = flavor
        this.size = size
        this.price = price
        this.qty = qty
    }


}