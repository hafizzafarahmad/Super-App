package com.summersoft.dictionary.features.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.summersoft.dictionary.features.dictionary.domain.model.Definition

data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String?,
    @SerializedName("synonyms")
    val synonyms: List<Any>
){
    fun toDefinition(): Definition{
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}