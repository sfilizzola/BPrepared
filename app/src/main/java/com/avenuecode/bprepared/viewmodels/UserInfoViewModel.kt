package com.avenuecode.bprepared.viewmodels

import com.avenuecode.bprepared.repos.DataRepository
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(private var repository: DataRepository): BaseViewModel() {
}