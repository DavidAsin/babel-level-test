package es.david.marvel.data.repository

import es.david.marvel.data.mapper.DetailMapper
import es.david.marvel.data.network.MarvelAPI
import es.david.marvel.dto.GetCharacterDetailDTO
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

class DetailRepository(
    private val marvelAPI: MarvelAPI,
    private val detailMapper: DetailMapper
) {

    fun getCharacterDetail(characterID: Int): Single<GetCharacterDetailDTO> {
        return marvelAPI.getCharacterDetail(characterID).flatMap {
            detailMapper.responseToDTO(it).toSingle()
        }
    }

}