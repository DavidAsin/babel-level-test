package es.david.marvel.data.repository

import es.david.marvel.data.network.MarvelAPI
import es.david.marvel.data.network.response.get_character_detail.GetCharacterDetailResponse
import io.reactivex.Single

class DetailRepository(private val marvelAPI: MarvelAPI) {

    fun getCharacterDetail(characterID: Int): Single<GetCharacterDetailResponse> {
        return marvelAPI.getCharacterDetail(characterID)
    }

}