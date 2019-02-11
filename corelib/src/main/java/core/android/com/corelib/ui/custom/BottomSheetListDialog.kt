package core.android.com.corelib.ui.custom

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import core.android.com.corelib.R
import core.android.com.corelib.ui.custom.adapters.BottomSheetListAdapter

/**
 * Used to display a list of strings
 * TODO change to list of objects instead of strings
 * */
class BottomSheetListDialog(context: Context, list: ArrayList<String>): BottomSheetDialog(context) {

    private var recyclerView: RecyclerView
    private var viewAdapter: BottomSheetListAdapter
    private var viewManager: RecyclerView.LayoutManager
    var onItemClickListener: BottomSheetListAdapter.OnItemClickListener? = null
        set(value) {
            viewAdapter.itemClickListener = value
        }

    init{
        val contentView = View.inflate(getContext(), R.layout.layout_bottom_sheet_list, null)
        setContentView(contentView)

        viewManager = LinearLayoutManager(context)
        viewAdapter = BottomSheetListAdapter(list)

        recyclerView = findViewById<RecyclerView>(R.id.rv_list)?.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }!!

        viewAdapter.itemClickListener = onItemClickListener
    }

}