package com.avenuecode.bprepared.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.databinding.ObservableInt
import android.view.View
import com.avenuecode.bprepared.BaseApp
import com.avenuecode.bprepared.repos.DataRepository
import com.avenuecode.bprepared.view.viewStatus.MainListViewStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainListViewModel @Inject constructor(private var repository: DataRepository) : BaseViewModel() {

    private val data = MutableLiveData<MainListViewStatus>()

    private var currentDisaster: Disasters = Disasters.NONE

    var progressVisibility = ObservableInt(View.GONE)
    var recyclerVisibility = ObservableInt(View.VISIBLE)

    private enum class Disasters {
        NONE, FLOOD, HURRICANE
    }


    @Bindable
    fun getDisclaimerVisibility(): Int {
        return if (BaseApp.currentUser != null) {
            if (BaseApp.currentUser!!.profileFilled) View.GONE else View.VISIBLE
        } else {
            View.VISIBLE
        }
    }


    fun getServerList() {
        showLoading(true)
        compositeDisposable.add(repository.getSuggestedItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    data.postValue(MainListViewStatus.ListSuccess(it))
                    showLoading(false)
                }, {
                    data.postValue(MainListViewStatus.Error(it.message))
                }))
    }

    fun selectDisaster(position: Int) {

        currentDisaster = when (position) {
            0 -> Disasters.NONE
            1 -> Disasters.FLOOD
            2 -> Disasters.HURRICANE
            else -> Disasters.NONE
        }

    }


    fun onClearClick(){
        if (currentDisaster.equals(Disasters.NONE)){
            data.postValue(MainListViewStatus.Error("No Disaster selected please pick one"))
        } else {

        }
    }

    fun onSuggestionClick(){
        if (currentDisaster.equals(Disasters.NONE)){
            data.postValue(MainListViewStatus.Error("No Disaster selected please pick one"))
        } else {
            getServerList()
        }
    }

    fun onAddClick() {
        data.postValue(MainListViewStatus.fabClick)
    }



    fun getData():MutableLiveData<MainListViewStatus> = data

    fun showLoading(show:Boolean){
        if (show) {
            progressVisibility.set(View.VISIBLE)
            recyclerVisibility.set(View.GONE)
        } else  {
            progressVisibility.set(View.GONE)
            recyclerVisibility.set(View.VISIBLE)
        }
    }


}