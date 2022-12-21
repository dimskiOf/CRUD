package com.komputerisasi.sprinter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.model.DataItemSparepart
import kotlinx.android.synthetic.main.item_data_stok_sparepart.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ItemSparepartStokAdapter(val data: List<DataItemSparepart>?, private val click: onClickItem) : RecyclerView.Adapter<ItemSparepartStokAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data_stok_sparepart, parent, false)
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
        fun onBind(get: DataItemSparepart?) {
            itemView.ItemNo.text = get?.ItemCode
            itemView.ItemDescription.text = get?.Itemdes
            if (get?.Unit3 == null) {
                itemView.Unit1.text = get?.Unit1
            } else {
                itemView.Unit1.text = get?.Unit3
                itemView.QtyStokMinimum.text = get?.Minimumqty
                itemView.QtyStok.text = get?.Quantity

            }
        }
    }

        interface onClickItem {
            fun clicked(item: DataItemSparepart?)
            fun delete(item: DataItemSparepart?)

        }
}
