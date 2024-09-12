package com.example.recyler_view_in_class_basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterRecycler
    private val dataList = ArrayList<LinearPojo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewLinear)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataList.add(LinearPojo("Android 11", "11"))
        // Add other data items here

        adapter = AdapterRecycler(dataList)
        recyclerView.adapter = adapter
    }
}