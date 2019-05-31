package com.example.jsonparsingasynctask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_volleypost.*
import org.json.JSONObject

class volleypost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volleypost)
        button.setOnClickListener {
            PostApicall()
        }

    }

    private fun PostApicall() {
        //RequestQueue initialized
        var mRequestQueue = Volley.newRequestQueue(this)

        val url = "http://119.73.65.154:8280/getsimpricingapi/1.0.0/getsimpricing"
        //String Request initialized
        var mStringRequest = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            Toast.makeText(applicationContext, "Logged In Successfully", Toast.LENGTH_SHORT).show()


        }, Response.ErrorListener { error ->
            Toast.makeText(
                applicationContext,
                "Error"+ error,
                Toast.LENGTH_LONG
            ).show()
        }) {


            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val params1 = HashMap<String, String>()
                params1.put("network", "jazz")
                params1.put("storeId", "1")
                params1.put("type", "postpaid")
                return JSONObject(params1).toString().toByteArray()


            }


            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("channel", "android")
                headers.put(
                    "subtoken",
                    "NWczek1hWHZjdlJBODR0VXVXRlhNSkdTYXlFa1FadmZNQXp1ajdlSm93VmgyUWVrSVBuRlF5UFBTNkkycndFdkFvd0Vja0JadWFnR2lCb1hLOGV1SEE9PQ=="
                )
                headers.put("language", "1")
                headers.put("parentmsisdn", "923000776089")
                headers.put("appversion", "1.1.1")
                headers.put("type", "postpaid")
                headers.put("deviceid", "a6d31f207d71beb7")
                headers.put("token",
                    "WXI5YU9PQkMrMTZkOExwSEpQMnp5UXVPK2svLzQreHVqUUF3cnAzcCtRdlk5Vk42VzNyd1RJQnAvTmFTOTBrZA=="
                )
                headers.put("network", "jazz")
                headers.put("authorization", "Bearer 4acfba95-6a60-3610-aa27-9950e17c8213")
                headers.put("customerId", "611552")
                headers.put("content-type", "application/json")
                headers.put("msisdn", "923000776089")

                return super.getHeaders()
            }


        }
        mRequestQueue.add(mStringRequest)


    }
}

/*internal var MyStringRequest: StringRequest =
    object : StringRequest(Request.Method.POST, url, object : Response.Listener<String>() {
        fun onResponse(response: String) {
            //This code is executed if the server responds, whether or not the response contains data.
            //The String 'response' contains the server's response.
        }
    }, object : Response.ErrorListener() { //Create an error listener to handle errors appropriately.
        fun onErrorResponse(error: VolleyError) {
            //This code is executed if there is an error.
        }
    }) {
        protected//Add the data you'd like to send to the server.
        val params: Map<String, String>
            get() {
                val MyData = HashMap<String, String>()
                MyData["Field"] = "Value"
                return MyData
            }
    }*/
