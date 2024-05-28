package dev.nowoczesny.aop.opengym

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

interface SearchHistory {
    fun getAllSearches(): List<String>
    fun saveSearch(query: String)
}

class SharedPrefSearchHistory(val context: Context) : SearchHistory {

    val asd = BuildConfig.APPLICATION_ID

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun getAllSearches(): List<String> {
        return sharedPreferences.getStringSet(HISTORIC_SEARCHES, emptySet())?.toList()
            ?: emptyList()
    }

    override fun saveSearch(query: String) {

        val searches = getAllSearches().toMutableList().apply {
            add(query)
        }.toSet()

        sharedPreferences.edit()
            .putStringSet(HISTORIC_SEARCHES, searches)
            .apply()
    }

    companion object {
        const val HISTORIC_SEARCHES = "HISTORIC_SEARCHES"
        const val SHARED_PREF_NAME = "SHARED_PREF"
    }
}