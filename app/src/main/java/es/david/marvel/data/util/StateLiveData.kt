package es.david.marvel.data.util

import androidx.lifecycle.MutableLiveData


class StateLiveData<T : Any> : MutableLiveData<StateData<T>>() {

    fun postLoading() {
        postValue(StateData.Loading)
    }

    fun postSuccess(data: T) {
        postValue(StateData.Success(data))
    }

    fun postError(throwable: Throwable) {
        postValue(StateData.Error(throwable))
    }

}