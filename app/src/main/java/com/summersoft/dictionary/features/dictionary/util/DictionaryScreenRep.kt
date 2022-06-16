package com.summersoft.dictionary.features.dictionary.util

sealed class DictionaryScreenRep(val route: String){
    object DictionaryInitScreen: DictionaryScreenRep("dictionary")
    object WordInfoScreen: DictionaryScreenRep("word_info_screen")
}