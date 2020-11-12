package es.david.marvel.dto

data class ResultDTO(
    val description: String,
    val id: Int,
    val thumbnail: ThumbnailDTO,
    val name: String,
)