package ru.techmas.barrier.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import ru.techmas.barrier.R
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers

class BarriersAdapter(var context: Context, items: Barriers)
    : BaseRecyclerAdapter<Barrier, BarriersAdapter.ViewHolder>(items) {

    lateinit var onBarrierClickListener: OnBarrierClickListener

    interface OnBarrierClickListener {
        fun onClickOpen(item: Barrier)
        fun onClickSettings(item: Barrier)
        fun onClickCamera(item: Barrier)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_barrier, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        with(item) {
            with(holder) {
                tvName.text = name
                tvAddress.text = address
                tvNumber.text = number
//                avatar?.let { ImageLoader.load(context, civAvatar, it) }
                btnOpen.setOnClickListener { onBarrierClickListener.onClickOpen(item) }
                ivCamera.setOnClickListener { onBarrierClickListener.onClickCamera(item) }
                ivSettings.setOnClickListener { onBarrierClickListener.onClickSettings(item) }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var ltContainer: RelativeLayout = itemView.findViewById(R.id.ltContainer)
//        var ivPhoto: CircleImageView = itemView.findViewById(R.id.ivPhoto)
        var tvName = itemView.findViewById<TextView>(R.id.tvName)!!
        var tvNumber = itemView.findViewById<TextView>(R.id.tvNumber)!!
        var tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)!!
        var btnOpen = itemView.findViewById<TextView>(R.id.btnOpen)!!
        var ivCamera  = itemView.findViewById<ImageView>(R.id.ivCamera)!!
        var ivSettings  = itemView.findViewById<ImageView>(R.id.ivSettings)!!
    }
}