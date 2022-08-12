package com.komputerisasi.sprinter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.model.FgMasukItem
import kotlinx.android.synthetic.main.item_data.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FgMasukAdapter(val data: List<FgMasukItem>?, private val click: onClickItem) : RecyclerView.Adapter<FgMasukAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
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
        fun onBind(get: FgMasukItem?) {
            itemView.ItemNo.text = get?.ItemNo
            itemView.ItemDescription.text = get?.ItemDescription
            if (get?.Unit3 == null) {
                itemView.Unit1.text = get?.Unit1
            }else{
                itemView.Unit1.text = get?.Unit3
            }
            itemView.Catatan.text = get?.Catatan
            itemView.QtyFG.text = get?.Qty
            itemView.TglCreateFg.text = get?.TglCatatan
            itemView.InputMinusPlus.text = get?.InputMinusPlus
            itemView.LotNumber.text = get?.LotNumber

        }
    }

    interface onClickItem{
        fun clicked (item: FgMasukItem?)
        fun delete(item: FgMasukItem?)

    }
}