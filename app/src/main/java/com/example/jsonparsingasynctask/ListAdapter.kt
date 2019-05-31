package com.example.jsonparsingasynctask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlin.collections.ArrayList

class ListAdapter(val context: Context,val list:ArrayList<JSONdata>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view:View=LayoutInflater.from(context).inflate(R.layout.rowlayout,parent,false)
        //val view:View=LayoutInflater.from(context).inflate(R.layout.rowlayout,parent,false)


        val id=view.findViewById<TextView>(R.id.pid)
        val name=view.findViewById<TextView>(R.id.pname)
        val email=view.findViewById<TextView>(R.id.pemail)
        val location=view.findViewById<TextView>(R.id.plocation)

       /* val quote=view.findViewById<TextView>(R.id.quote)
        val author=view.findViewById<TextView>(R.id.author)
        val length=view.findViewById<TextView>(R.id.length)
       val tags=view.findViewById<TextView>(R.id.t)
        val categorey=view.findViewById<TextView>(R.id.category)
        val title=view.findViewById<TextView>(R.id.title)
        val date=view.findViewById<TextView?>(R.id.date)
        val id=view.findViewById<TextView>(R.id.id)
        val copyright=view.findViewById<TextView>(R.id.copyright)

        quote.text=list[position].quote.toString()
        author.text=list[position].author.toString()
        categorey.text=list[position].category.toString()
        title.text=list[position].title.toString()
        date?.text=list[position].date.toString()
        id.text=list[position].id.toString()
        copyright.text=list[position].toString()
        length.text=list[position].toString()
       tags.text=list[position].toString()*/

        id.text=list[position].id.toString()
        name.text=list[position].name.toString()
        email.text=list[position].email.toString()
        location.text=list[position].location.toString()

        return view

    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}