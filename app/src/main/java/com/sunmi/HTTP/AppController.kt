package com.sunmi.HTTP

import com.android.volley.VolleyLog
import android.text.TextUtils
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue
import android.app.Application
import com.android.volley.Request


/**
 * Created by Default on 26/04/2018.
 */
class AppController : Application() {

    /**
     * Global request queue for Volley
     */
    private var mRequestQueue: RequestQueue? = null

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    // lazy initialize the request queue, the queue instance will be
    // created when it is accessed for the first time
    private val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return mRequestQueue!!
        }

    override fun onCreate() {
        super.onCreate()

        // initialize the singleton
        instance = this
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.setTag(if (TextUtils.isEmpty(tag)) TAG else tag)

        VolleyLog.d("Adding request to queue: %s", req.getUrl())

        requestQueue.add(req)
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     * @param tag
     */
    fun <T> addToRequestQueue(req: Request<T>) {
        // set the default tag if tag is empty
        req.setTag(TAG)

        requestQueue.add(req)
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {

        /**
         * Log or request TAG
         */
        val TAG = "VolleyPatterns"

        /**
         * A singleton instance of the application class for easy access in other places
         */
        /**
         * @return ApplicationController singleton instance
         */
        @get:Synchronized
        var instance: AppController? = null
            private set
    }
}