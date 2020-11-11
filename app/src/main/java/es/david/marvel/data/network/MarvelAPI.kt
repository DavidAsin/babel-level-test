package es.david.marvel.data.network

import es.david.marvel.data.network.response.get_character_detail.GetCharacterDetailResponse
import es.david.marvel.data.network.response.get_characters.GetCharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    @GET("v1/public/characters")
    fun getCharacters(@Query("offset") offset: Int): Single<GetCharactersResponse>

    @GET("v1/public/characters/{characterID}")
    fun getCharacterDetail(@Path("characterID") characterID: Int): Single<GetCharacterDetailResponse>
}