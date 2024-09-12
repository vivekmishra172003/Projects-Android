package com.example.recyler_view_in_class_basic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecycler(private val dataList: ArrayList<LinearPojo>) :
    RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val versionName: TextView = itemView.findViewById(R.id.version_name)
        val version: TextView = itemView.findViewById(R.id.version)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = dataList[position]
        holder.versionName.text = item.versionName
        holder.version.text = item.version
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}