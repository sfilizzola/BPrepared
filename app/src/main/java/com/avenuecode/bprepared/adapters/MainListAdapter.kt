package com.avenuecode.bprepared.adapters

import android.arch.lifecycle.MutableLiveData
import com.avenuecode.bprepared.R
import com.avenuecode.bprepared.databinding.ListItemRowBinding
import com.avenuecode.bprepared.models.ImportantItem
import com.avenuecode.bprepared.view.viewStatus.MainListViewStatus
import com.avenuecode.bprepared.viewmodels.ItemRowViewModel

class MainListAdapter(var data: MutableLiveData<MainListViewStatus>) : BaseAdapter<ImportantItem, ListItemRowBinding>(R.layout.list_item_row) {
    override fun areItemsTheSame(oldItem: ImportantItem, newItem: ImportantItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImportantItem, newItem: ImportantItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun bind(holder: DataBindViewHolder<ListItemRowBinding>, position: Int) {

        val currentItem = items[position]
        holder.binding.viewModel = ItemRowViewModel(currentItem, data)
        holder.binding.itemFoundCheck.isChecked = currentItem.checked
    }


    fun checkItem(item:ImportantItem){

        items.forEach {
            if (it.id == item.id){
                it.checked = true
            }
        }
    }

}