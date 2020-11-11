package es.david.marvel.data.network.response.get_character_detail


import com.google.gson.annotations.SerializedName

data class GetCharacterDetailResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int
)

data class Result(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
)

data class Thumbnail(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)