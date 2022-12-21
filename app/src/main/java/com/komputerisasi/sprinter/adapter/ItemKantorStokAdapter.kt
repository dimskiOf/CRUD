package com.komputerisasi.sprinter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.model.DataItemKantor
import kotlinx.android.synthetic.main.item_data_stok_kantor.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ItemKantorStokAdapter(val data: List<DataItemKantor>?, private val click: onClickItem) : RecyclerView.Adapter<ItemKantorStokAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data_stok_kantor, parent, false)
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
        fun onBind(get: DataItemKantor?) {
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
        fun clicked(item: DataItemKantor?)
        fun delete(item: DataItemKantor?)

    }
}
