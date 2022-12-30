package com.movies.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
abstract class BaseViewModel:ViewModel() {
    protected val mutableState = MutableLiveData<BaseState>()
    val state:LiveData<BaseState> = mutableState
}

interface BaseState