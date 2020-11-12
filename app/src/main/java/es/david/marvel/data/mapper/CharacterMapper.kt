package es.david.marvel.data.mapper

import es.david.marvel.data.network.response.Data
import es.david.marvel.data.network.response.GetCharactersResponse
import es.david.marvel.data.network.response.Result
import es.david.marvel.data.network.response.Thumbnail
import es.david.marvel.dto.DataDTO
import es.david.marvel.dto.GetCharactersDTO
import es.david.marvel.dto.ResultDTO
import es.david.marvel.dto.ThumbnailDTO

class CharacterMapper {

    fun responseToDTO(response: GetCharactersResponse): GetCharactersDTO {
        return GetCharactersDTO(
            data = responseToDTO(response.data)
        )
    }

    private fun responseToDTO(response: Data): DataDTO {
        return DataDTO(
            count = response.count,
            results = responseToDTO(response.results)
        )
    }

    private fun responseToDTO(response: List<Result>): List<ResultDTO> {
        return response.map {
            ResultDTO(
                description = it.description,
                id = it.id,
                thumbnail = responseToDTO(it.thumbnail),
                name = it.name
            )
        }
    }

    private fun responseToDTO(response: Thumbnail): ThumbnailDTO {
        return ThumbnailDTO(
            extension = response.extension,
            path = response.path
        )
    }

}