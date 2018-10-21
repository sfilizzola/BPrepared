package com.avenuecode.bprepared.viewmodels

import com.avenuecode.bprepared.repos.DataRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private var repository: DataRepository) :  BaseViewModel() {
}