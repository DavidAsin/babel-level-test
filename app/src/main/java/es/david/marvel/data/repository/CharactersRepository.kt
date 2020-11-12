package es.david.marvel.data.repository

import es.david.marvel.data.mapper.CharacterMapper
import es.david.marvel.data.network.MarvelAPI
import es.david.marvel.dto.GetCharactersDTO
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

class CharactersRepository(
    private val marvelAPI: MarvelAPI,
    private val characterMapper: CharacterMapper
) {

    fun getCharacters(offset: Int): Single<GetCharactersDTO> {
        return marvelAPI.getCharacters(offset).flatMap {
            characterMapper.responseToDTO(it).toSingle()
        }
    }

}