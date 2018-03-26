package ru.techmas.barrier.adapters

import android.support.v7.widget.RecyclerView

/**
 * Created by mihailantipev on 13.09.17.
 */
abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>
    constructor(protected var items: ArrayList<T>) : RecyclerView.Adapter<VH>() {

    lateinit var onItemClickListener: OnItemClickListener

    lateinit var onTitleClickListener: OnItemClickListener


    interface OnItemClickListener {
        fun onClick(item: Any)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun replace(new: List<T>) {
        items.clear()
        items.addAll(new)
        notifyDataSetChanged()
    }

    fun add(new: List<T>) {
        items.addAll(new)
        notifyDataSetChanged()
    }

}