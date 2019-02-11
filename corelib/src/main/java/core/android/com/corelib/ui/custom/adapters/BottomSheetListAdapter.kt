package core.android.com.corelib.ui.custom.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import core.android.com.corelib.R

/**
 * Created by Calvin Paham on 1/24/2019.
 */
class BottomSheetListAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<BottomSheetListAdapter.BottomSheetListViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    class BottomSheetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetListAdapter.BottomSheetListViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_simple_list, parent, false) as ConstraintLayout
        return BottomSheetListViewHolder(textView)
    }

    override fun onBindViewHolder(holder: BottomSheetListViewHolder, position: Int) {
        val tvItemName = holder.itemView.findViewById<TextView>(R.id.tv_item_name)
        tvItemName.text = list[position]

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount() = list.size
}