package es.david.marvel.data.util


sealed class StateData<out T : Any> {

    object Loading : StateData<Nothing>()
    class Success<out T : Any>(val data: T) : StateData<T>()
    data class Error(val throwable: Throwable) : StateData<Nothing>()

}