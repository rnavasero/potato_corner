package com.sunmi.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.VolleyError
import com.mycart.advance.https.API
import com.mycart.advance.https.APIRequest
import com.sunmi.Activities.MainActivity
import com.sunmi.printerhelper.R
import kotlinx.android.synthetic.main.changepass.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.HashMap


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private var mActivity: MainActivity? = null

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = context as MainActivity?
        mActivity!!.setCustomToolbarTitle("ACCOUNT")

        tv_sign_out.setOnClickListener {

            val alert = AlertDialog.Builder(context)
            alert.setTitle("PC_POS_SYSTEM")
            alert.setMessage("Are you sure you want to logout?")
            alert.setNegativeButton("No", { _, _ ->

            })
            alert.setPositiveButton("Yes", { _, _ ->
                mActivity!!.logout()
            }).show()

        }

        tv_change_pass.setOnClickListener {

            val alert = AlertDialog.Builder(context)
            val v = LayoutInflater.from(context).inflate(R.layout.changepass, null)
            alert.setView(v)
            val dialog = alert.create()

            v.btn_confirm.setOnClickListener{
                val cp = v.et_current_password.text.toString()
                val np = v.et_new_password.text.toString()
                val cnp = v.et_cnew_password.text.toString()

                if (v.et_current_password.text.toString().isEmpty()){
                    v.input_current_password.error = "This field is required"
                    return@setOnClickListener
                }

                if (v.et_new_password.text.toString().isEmpty()){
                    v.input_new_password.error = "This field is required"
                    return@setOnClickListener
                }

                if (v.et_cnew_password.text.toString().isEmpty()){
                    v.input_cnew_password.error = "This field is required"
                    return@setOnClickListener
                }

                if(v.et_new_password.text.toString()!= v.et_cnew_password.text.toString()){
                    v.input_cnew_password.error = "Password mismatch!"
                }

                val params:MutableMap<String, String> = HashMap()
                params["currentPassword"] = cp
                params["newPassword"] = np
                params["_confirmPassword"] = cnp

                APIRequest.changePass(context, API.CHANGEPASS, params, object : APIRequest.URLCallback{
                    override fun didUrlResponse(response: String) {
                        dialog.dismiss()
                    }

                    override fun didUrlError(error: VolleyError) {

                    }

                })


            }

            dialog.show()

        }
    }

}// Required empty public constructor
