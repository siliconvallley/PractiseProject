package com.xjsd.practiseproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xjsd.practiseproject.databinding.LayoutRvFuncItemBinding

class FuncAdapter(
    val list: MutableList<String>,
    val onItemClick: (itemView: View, position: Int) -> Unit
) : RecyclerView.Adapter<FuncAdapter.FuncViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncViewHolder {
        return FuncViewHolder(
            LayoutRvFuncItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FuncViewHolder, position: Int) {
        holder.bindData(list[position])

        holder.itemBinding.root.setOnClickListener {
            onItemClick.invoke(it, position)
        }
    }

    inner class FuncViewHolder(val itemBinding: LayoutRvFuncItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(text: String) {
            itemBinding.tvFuncTitle.text = text
        }
    }
}