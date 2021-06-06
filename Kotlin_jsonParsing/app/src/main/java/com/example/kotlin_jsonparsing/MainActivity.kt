package com.example.kotlin_jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.kotlin_jsonparsing.Model.Data
import com.example.kotlin_jsonparsing.Model.Sample
import com.example.kotlin_jsonparsing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val dataList: MutableList<Data> = mutableListOf()
    private lateinit var myAdapter: MyAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup adapter
        myAdapter = MyAdapter(dataList)

        //setup recyclerview
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        binding.myRecyclerView.adapter = myAdapter

        //setup Android Networking
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://reqres.in/api/users?page=2")
            .build()
            .getAsObject(Sample::class.java, object : ParsedRequestListener<Sample> {
                override fun onResponse(response: Sample?) {
                    if (response != null) {
                        dataList.addAll(response.data)
                    }
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                }

            })
    }
}