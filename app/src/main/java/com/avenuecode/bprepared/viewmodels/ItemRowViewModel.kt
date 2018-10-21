package com.avenuecode.bprepared.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.avenuecode.bprepared.models.ImportantItem
import com.avenuecode.bprepared.view.viewStatus.MainListViewStatus
import timber.log.Timber

class ItemRowViewModel(private val item: ImportantItem, private val data: MutableLiveData<MainListViewStatus>) : BaseViewModel() {

    @Bindable
    fun getTitle(): String = item.name

    @Bindable
    fun isChecked(): Boolean = item.checked

    @Bindable
    fun isMissing(): Boolean = item.missing

    fun onCheckClick(){
        item.checked = !item.checked
        data.postValue(MainListViewStatus.CheckedItem(item))
    }

}