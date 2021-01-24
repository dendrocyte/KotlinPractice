package com.example.core

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    @SuppressLint("UseSparseArrays")
    private val viewHashMap : MutableMap<Int, View> = mutableMapOf()

    @SuppressWarnings("unchecked")
    protected fun <T> getView(id: Int) :T{
        var view: View? = viewHashMap[id]
        if (view == null){
            view = itemView.findViewById(id)
            viewHashMap.put(id, view)
        }
        return view as T
    }


    protected fun setText(id: Int, text: String?){
        (getView(id) as TextView).text = text
    }

}