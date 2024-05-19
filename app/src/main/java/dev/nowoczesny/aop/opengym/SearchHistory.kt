package dev.nowoczesny.aop.opengym

class SearchHistory() {
    val list = mutableListOf<String>()

    fun getAllSearches() = list
    fun saveSearch(query: String){
        list.add(query)
    }
}
