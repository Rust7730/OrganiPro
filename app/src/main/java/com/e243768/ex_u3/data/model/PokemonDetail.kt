package com.e243768.ex_u3.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeResponse>,
    val stats: List<StatResponse>,
    val sprites: Sprites
)

data class TypeResponse(
    val slot: Int,
    val type: TypeName
)

data class TypeName(
    val name: String
)

data class StatResponse(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: StatName
)

data class StatName(
    val name: String
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("other") val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?
)