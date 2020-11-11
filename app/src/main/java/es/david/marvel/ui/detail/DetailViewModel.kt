package es.david.marvel.ui.detail

import androidx.lifecycle.ViewModel
import es.david.marvel.data.network.response.get_character_detail.Result
import es.david.marvel.data.repository.DetailRepository
import es.david.marvel.data.util.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val stateDetailLoad = StateLiveData<Result>()

    fun getCharacterDetail(characterID: Int) {
        detailRepository.getCharacterDetail(characterID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

            }
            .subscribe(
                { value ->
                    stateDetailLoad.postSuccess(value.data.results.first())
                },
                { error ->
                    stateDetailLoad.postError(error)
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