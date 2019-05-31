package com.example.jsonparsingasynctask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_volleypost.*
import org.json.JSONArray
import org.json.JSONObject

class NewVolleyPost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volleypost)

        button.setOnClickListener {
            PostApicall()
        }
    }

    private fun PostApicall() {
        val mRequestQueue = Volley.newRequestQueue(this)
        val url = "http://119.73.65.154:8280/getsimpricingapi/1.0.0/getsimpricing"
        val mStringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
            //Toast.makeText(applicationContext, "Response="+ response, Toast.LENGTH_SHORT).show()

            val strResp = response.toString()
            val jsonObject: JSONObject = JSONObject(strResp)
            val dataObject = jsonObject.getJSONObject("data")
            val simPricingArray: JSONArray = dataObject.getJSONArray("simPricing")
//            val jsonArray:JSONArray=jsonObject.getJSONArray("data")
            var simpricing: String = ""
            for (i in 0 until simPricingArray.length()) {
                val jsonInner: JSONObject = simPricingArray.getJSONObject(i)
                simpricing =
                    simpricing + " \n" + jsonInner.get("type") + "\n" + jsonInner.get("price") + "\n" + jsonInner.get("deliveryCharges") + "\n"

            }
            textView2.text = simpricing

            val pointsarray: JSONArray = dataObject.getJSONArray("points")
            textView3.text = pointsarray.toString()

            val citiesarray: JSONArray = dataObject.getJSONArray("cityList")
            textView4.text = citiesarray.toString()


        }, Response.ErrorListener { error ->
            Toast.makeText(
                applicationContext,
                "Error" + error,
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
                headers.put(
                    "token",
                    "WXI5YU9PQkMrMTZkOExwSEpQMnp5UXVPK2svLzQreHVqUUF3cnAzcCtRdlk5Vk42VzNyd1RJQnAvTmFTOTBrZA=="
                )
                headers.put("network", "jazz")
                headers.put("authorization", "Bearer 4acfba95-6a60-3610-aa27-9950e17c8213")
                headers.put("customerId", "611552")
                headers.put("content-type", "application/json")
                headers.put("msisdn", "923000776089")
                return headers
            }
        }

        mRequestQueue.add(mStringRequest)
    }


}