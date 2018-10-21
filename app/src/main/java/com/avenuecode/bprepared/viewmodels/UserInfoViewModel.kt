package com.avenuecode.bprepared.viewmodels

import com.avenuecode.bprepared.BaseApp
import com.avenuecode.bprepared.models.CurrentUser
import com.avenuecode.bprepared.repos.DataRepository
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(private var repository: DataRepository): BaseViewModel() {

    fun onSaveClick(){
        BaseApp.currentUser = CurrentUser(1, "Samuel Filizzola",
                "sfilizzola@gmail.com",
                'M',
                33,
                "Belo Horizonte",
                0,
                false,
                true,
                100,
                178.0,
                true,
                false,
                true,
                50.0F,
                "Bacon Lover",
                "Board Games",
                "Portuguese",
                true, true)
    }


}