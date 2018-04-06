package com.mycart.advance.https

/**
 * Created by CDI on 2/27/18
 */
object API {
    private const val BASE_URL      = "http://api-express-staging.codedisruptors.com:7010"
    const val LOGIN                 = "$BASE_URL/auth/login"
    const val LOGOUT                = "$BASE_URL/auth/logout"
    const val REGISTER              = "$BASE_URL/user"
    const val PRODUCTS              = "$BASE_URL/product"
    const val CHECKOUT              = "$BASE_URL/cart/checkout"
    const val CHANGEPASS            = "$BASE_URL/user/change_password"
}