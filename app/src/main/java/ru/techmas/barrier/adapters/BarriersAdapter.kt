package ru.techmas.barrier.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import ru.techmas.barrier.R
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.models.Photos
import ru.techmas.barrier.utils.ImageLoader

class BarriersAdapter(var context: Context, items: Barriers, val photos: Photos, val hand: Boolean, val added: ArrayList<String>)
    : BaseRecyclerAdapter<Barrier, BarriersAdapter.ViewHolder>(items) {

    lateinit var onBarrierClickListener: OnBarrierClickListener

    interface OnBarrierClickListener {
        fun onClickOpen(item: Barrier)
        fun onClickSettings(item: Barrier)
        fun onClickCamera(item: Barrier)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
                if (hand)
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.item_barrier_right, viewGroup, false)
                else
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.item_barrier, viewGroup, false)
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
                if (photos.containsKey(number)) {
                    ImageLoader.load(context, ivPhoto, photos[number]!!)
                }
                btnOpen.isSelected = opened
                if (number in added || isOld()) {
                    cardContainer.setCardBackgroundColor(context.resources.getColor(R.color.colorAccent))
                    ivSettings.setImageDrawable(context.resources.getDrawable(R.drawable.setting_2))
                } else {
                    cardContainer.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                    ivSettings.setImageDrawable(context.resources.getDrawable(R.drawable.setting_1))
                }
//                btnOpen.setBackgroundColor(
//                        if (opened) context.resources.getColor(R.color.colorOpen)
//                        else context.resources.getColor(R.color.light_background))
//                btnOpen.setTextColor(
//                        if (opened) context.resources.getColor(R.color.light_background)
//                        else context.resources.getColor(R.color.colorPrimaryDark))
                btnOpen.setOnClickListener { view -> openClick(view, item) }
                ivCamera.setOnClickListener { onBarrierClickListener.onClickCamera(item) }
                ivSettings.setOnClickListener { onBarrierClickListener.onClickSettings(item) }
            }
        }
    }

    private fun openClick(view: View, item: Barrier) {
        onBarrierClickListener.onClickOpen(item)
//        with(view as TextView) {
//            view.setBackground(context.resources.getDrawable(R.drawable.rounded_button_green))
//            view.setTextColor(context.resources.getColor(R.color.light_background))
//        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var ltContainer: RelativeLayout = itemView.findViewById(R.id.ltContainer)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
        var ivPhoto: CircleImageView = itemView.findViewById(R.id.ivPhoto)
        var tvName = itemView.findViewById(R.id.tvName) as TextView
        var tvNumber = itemView.findViewById(R.id.tvNumber) as TextView
        var tvAddress = itemView.findViewById(R.id.tvAddress) as TextView
        var btnOpen = itemView.findViewById(R.id.btnOpen) as TextView
        var ivCamera = itemView.findViewById(R.id.ivCamera) as ImageView
        var ivSettings = itemView.findViewById(R.id.ivSettings) as ImageView
    }
}