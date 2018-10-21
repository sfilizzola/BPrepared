package com.avenuecode.bprepared.repos

import com.avenuecode.bprepared.models.ImportantItem
import com.avenuecode.bprepared.network.NetworkClient
import io.reactivex.Single
import io.reactivex.internal.operators.flowable.FlowableFromIterable
import javax.inject.Inject

class DataRepository @Inject constructor(private var service:NetworkClient) {

    fun getItemsFromDataBase(): Single<List<ImportantItem>> {
        return Single.just(ArrayList())
    }

    fun getSuggestedItems():Single<List<ImportantItem>>{
        return service.getList().flatMapPublisher {
            FlowableFromIterable(it.result)
        }.map {
            ImportantItem(it.item_id, it.item, false, false)
        }.toList()
    }

}