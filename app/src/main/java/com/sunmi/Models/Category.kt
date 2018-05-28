package com.sunmi.Models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Default on 25/05/2018.
 */
@Entity(tableName = "categoryData")
class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "image_path")
    var imageUrl: Int = 0

    @ColumnInfo(name = "category_id")
    var category_id: Int = 0



    constructor(jsonObject: JSONObject){
        id = try {
            jsonObject.getInt("id")
        }catch (e: JSONException){
            0
        }

        name = try {
            jsonObject.getString("name")
        }catch (e: JSONException){
            ""
        }

        imageUrl = try {
            jsonObject.getInt("image_path")
        }catch (e: JSONException){
            0
        }

        category_id = try {
            jsonObject.getInt("category_id")
        }catch (e: JSONException){
            0
        }

    }
}