package com.diegoferreiracaetano.chuckNorris.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.chuckNorris.R
import com.diegoferreiracaetano.chuckNorris.databinding.ListItemCategoriesBinding

class CategoriesListAdapter(private val items: List<String>,private val callbacks: Callbacks? = null) :
        RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_categories, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.category = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ListItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}