package com.komputerisasi.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komputerisasi.crud.R
import com.komputerisasi.crud.model.DataLogin


class LoginAdapter(val data: List<DataLogin>?, private val click: onClickItem) : RecyclerView.Adapter<LoginAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_login_utama,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))

            click.getter(data?.get(position))

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: DataLogin?) {
           get?.username
           get?.email
           get?.privilage
           get?.accesstoken
        }
    }

    interface onClickItem{
        fun getter (item: DataLogin?)

    }
}