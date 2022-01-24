package com.komputerisasi.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komputerisasi.crud.R
import com.komputerisasi.crud.model.RmKeluarItem
import kotlinx.android.synthetic.main.item_data_rm.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RmKeluarAdapter(val data: List<RmKeluarItem>?, private val click: onClickItem) : RecyclerView.Adapter<RmKeluarAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_rm,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
        holder.itemView.btnHapus.setOnClickListener {
            click.delete(data?.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: RmKeluarItem?) {
            itemView.ItemNo.text = get?.ItemNo
            itemView.ItemDescription.text = get?.ItemDescription
            itemView.Unit1.text = get?.Unit1
            itemView.QtyRM.text = get?.QtyRm
            itemView.TglCreateRm.text = get?.TglCreateRm
            itemView.InputMinusPlus.text = get?.InputMinusPlus
            itemView.LoadNumber.text = get?.LoadNumber

        }
    }

    interface onClickItem{
        fun clicked (item: RmKeluarItem?)
        fun delete(item: RmKeluarItem?)

    }
}