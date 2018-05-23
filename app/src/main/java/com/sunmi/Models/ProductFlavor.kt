package com.sunmi.Models

import org.json.JSONException
import org.json.JSONObject


class ProductFlavor {

    var id:String = ""
    var franchise_id: String = ""
    var code: String = ""
    var description: String = ""
    var created: String = ""
    var updated: String = ""
    var deleted: String = ""




    constructor(jsonObject: JSONObject) {
        id = try {
            jsonObject.getString("id")
        } catch (e: JSONException) {
            ""
        }

        franchise_id = try {
            jsonObject.getString("franchise_id")
        } catch (e: JSONException) {
            ""
        }

        code = try {
            jsonObject.getString("code")
        } catch (e: JSONException) {
            ""
        }

        description = try {
            jsonObject.getString("description")
        } catch (e: JSONException) {
            ""
        }

        created = try {
            jsonObject.getString("created")
        } catch (e: JSONException) {
            ""
        }

        updated = try {
            jsonObject.getString("updated")
        } catch (e: JSONException) {
            ""
        }

        deleted = try {
            jsonObject.getString("deleted")
        } catch (e: JSONException) {
            ""
        }

    }

    constructor(id: String, franchise_id: String, code: String, description: String, created: String, updated: String, deleted: String) {
        this.id = id
        this.franchise_id = franchise_id
        this.code = code
        this.description = description
        this.created = created
        this.updated = updated
        this.deleted = deleted
    }

}