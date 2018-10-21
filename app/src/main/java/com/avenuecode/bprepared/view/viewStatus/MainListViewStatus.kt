package com.avenuecode.bprepared.view.viewStatus

import com.avenuecode.bprepared.models.ImportantItem

sealed class MainListViewStatus {
    data class Error(val message: String?) : MainListViewStatus()
    data class ListSuccess(val list: List<ImportantItem>) : MainListViewStatus()
    data class CheckedItem(val item:ImportantItem):MainListViewStatus()
    object fabClick : MainListViewStatus()

    fun list(): List<ImportantItem> {
        return when (this) {
            is ListSuccess -> this.list
            else -> ArrayList<ImportantItem>()
        }
    }

    fun message(): String? {
        return when (this) {
            is Error -> this.message
            else -> null
        }
    }


    fun item(): ImportantItem? {
        return when (this) {
            is CheckedItem -> this.item
            else -> null
        }
    }
}