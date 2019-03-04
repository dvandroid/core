package core.android.com.android_core.ui.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import core.android.com.android_core.R
import core.android.com.android_core.data.database.repo.EmployeeObject
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeListAdapter(private val blogListItems: MutableList<EmployeeObject>) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EmployeeViewHolder = EmployeeViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_employee, parent, false))

    override fun getItemCount() = this.blogListItems.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    internal fun addBlogsToList(blogs: List<EmployeeObject>) {
        this.blogListItems.addAll(blogs)
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.tv_id.text = ""
            itemView.tv_name.text = ""
        }

        fun onBind(position: Int) {

            val (id, name) = blogListItems[position]
            inflateData(id, name)
        }


        private fun inflateData(id: String?, name: String?) {
            id?.let { itemView.tv_id.text = it }
            name?.let { itemView.tv_name.text = it }
        }

    }
}
