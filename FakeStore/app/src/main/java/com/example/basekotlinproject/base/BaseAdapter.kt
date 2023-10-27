package com.example.basekotlinproject.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerviewAdapter<B : ViewDataBinding, T> :
    RecyclerView.Adapter<BaseRecyclerviewAdapter.ViewHolder<B>>() {
    protected var currentList = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> =
        onCreateVH(parent, viewType)

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) =
        onBindVH(holder, position)

    override fun getItemCount(): Int = currentList.size

    abstract fun onCreateVH(parent: ViewGroup, viewType: Int): ViewHolder<B>

    abstract fun onBindVH(holder: ViewHolder<B>, position: Int)

    @SuppressLint("NotifyDataSetChanged")
    open fun submitList(list: List<T>) {
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    fun appendList(list: List<T>) {
        currentList.addAll(list)
        notifyItemRangeInserted(currentList.size - list.size, list.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun resetData() {
        currentList.clear()
        notifyDataSetChanged()
    }

    open fun removeItem(index: Int) {
        currentList.removeAt(index)
        notifyItemRemoved(index)
    }

    open fun removeItem(item: T) {
        val index = currentList.indexOf(item)
        removeItem(index)
    }

    class ViewHolder<B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)
}

