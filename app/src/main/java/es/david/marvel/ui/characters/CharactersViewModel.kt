package es.david.marvel.ui.characters

import androidx.lifecycle.ViewModel
import es.david.marvel.data.util.StateLiveData
import es.david.marvel.data.network.response.get_characters.Result
import es.david.marvel.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var offset: Int = 0

    val stateCharactersLoad = StateLiveData<List<Result>>()

    init {
        getCharacters()
    }

    fun getCharacters() {
        charactersRepository.getCharacters(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                stateCharactersLoad.postLoading()
            }
            .subscribe(
                { value ->
                    offset += value.data.count
                    stateCharactersLoad.postSuccess(value.data.results)
                },
                { error ->
                    stateCharactersLoad.postError(error)
                }
            ).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

}