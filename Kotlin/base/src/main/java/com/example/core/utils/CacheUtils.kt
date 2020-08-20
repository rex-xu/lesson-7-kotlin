package com.example.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.core.BaseApplication.Companion.currentApplication
import com.example.core.R.string

object CacheUtils {
  @SuppressLint("StaticFieldLeak")
  private val context = currentApplication()
  private val SP = context.getSharedPreferences(
      context.getString(string.app_name), Context.MODE_PRIVATE
  )

  fun save(
    key: String?,
    value: String?
  ) {
    SP.edit()
        .putString(key, value)
        .apply()
  }

  fun get(key: String?): String? {
    return SP.getString(key, null)
  }
}