package es.david.marvel.data.repository

import es.david.marvel.data.network.MarvelAPI
import es.david.marvel.data.network.response.get_characters.GetCharactersResponse
import io.reactivex.Single

class CharactersRepository(private val marvelAPI: MarvelAPI) {

    fun getCharacters(offset: Int): Single<GetCharactersResponse> {
        return marvelAPI.getCharacters(offset)
    }

}