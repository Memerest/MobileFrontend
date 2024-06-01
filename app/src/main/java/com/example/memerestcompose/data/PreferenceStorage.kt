package com.example.memerestcompose.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceStorage @Inject constructor(
    @ApplicationContext context: Context,
) {

    companion object {
        const val PREF_FILE_NAME = "pref_file"

        private const val PREF_USER_TOKEN = "pref_user_token"

        private const val PREF_USER_ID = "pref_user_id"
    }

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    var userToken: String
        get() = pref.getString(PREF_USER_TOKEN, "") ?: ""
        set(value) = pref.edit { putString(PREF_USER_TOKEN, value) }
    var userId: Int
        get() = pref.getInt(PREF_USER_ID, 1)
        set(value) = pref.edit { putInt(PREF_USER_ID, value) }
}