package com.mycart.advance.https

/**
 * Created by CDI on 2/27/18
 */
object API {
    private const val BASE_URL      = "http://192.168.0.127:8000"
    const val LOGIN                 = "$BASE_URL/auth/login"
    const val LOGOUT                = "$BASE_URL/auth/logout"
    const val REGISTER              = "$BASE_URL/user"

    const val PRODUCTS              = "$BASE_URL/products"
    const val PRODUCTSIZE           = "$BASE_URL/product/size"
    const val PRODUCTFLAVOR         = "$BASE_URL/product/flavor"

    const val CHECKOUT              = "$BASE_URL/cart/checkout"
    const val CHANGEPASS            = "$BASE_URL/user/change_password"
    const val DELETEUSER            = "$BASE_URL/user/"
}