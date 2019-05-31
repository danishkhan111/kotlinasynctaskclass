package com.example.jsonparsingasynctask

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import android.net.ConnectivityManager
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var pDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        if (ni != null && ni.type == ConnectivityManager.TYPE_WIFI) {

            val url = "https://coraciiform-altimet.000webhostapp.com"
            AsyncTaskHandler().execute(url)
        }
        else
            Toast.makeText(this@MainActivity,"Turn your WIFI On",Toast.LENGTH_LONG).show()

    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskHandler : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pDialog = ProgressDialog(this@MainActivity)
            pDialog.setMessage("Please Wait")
            pDialog.setCancelable(false)
            pDialog.show()
        }

        override fun doInBackground(vararg url: String?): String {
            val res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (pDialog.isShowing())
                pDialog.dismiss()
            jsonResult(result)


        }

        private fun jsonResult(jsonString: String?) {
            val jsonArray = JSONArray(jsonString)
            val list = ArrayList<JSONdata>()
            var i = 0
            while (i < jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                list.add(
                    JSONdata(

                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("email"),
                        jsonObject.getString("location")
                        /*jsonObject.getString("quote"),
                        jsonObject.getString("author"),
                        jsonObject.getInt("length"),
                        jsonObject.getString("tags"),
                        jsonObject.getString("category"),
                        jsonObject.getString("title"),
                        jsonObject.getString("date"),
                        jsonObject.getString("id"),
                        jsonObject.getString("copyright")*/
                    )
                )
                i++
            }
            val adapter = ListAdapter(this@MainActivity, list)
            mainview_listview.adapter = adapter
        }
    }
}
