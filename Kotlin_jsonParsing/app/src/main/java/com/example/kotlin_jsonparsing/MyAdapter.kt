package com.example.kotlin_jsonparsing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_jsonparsing.Model.Data
import com.example.kotlin_jsonparsing.databinding.ItemViewBinding
import com.squareup.picasso.Picasso

class MyAdapter(var dataList: List<Data>) : RecyclerView.Adapter<MyAdapter.BindingHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent!!.context)
        var binding = ItemViewBinding.inflate(layoutInflater, parent, false)

        return BindingHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = dataList[position]
        val userFullNameTextView = holder.binding.userFullName
        val userAvatarImgView = holder.binding.userAvatar

        val fullName = "${data.firstName} ${data.lastName}"
        userFullNameTextView.text = fullName

        Picasso.get()
            .load(data.avatar)
            .into(userAvatarImgView)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show()
        }
    }

    class BindingHolder(@NonNull var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

}