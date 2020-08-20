package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import java.util.HashMap

open class BaseViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val viewHashMap = HashMap<Int, View>()

  protected fun <T : View?> getView(@IdRes id: Int): T? {
    var view = viewHashMap.get(id)
    if (view == null) {
      view = itemView.findViewById(id)
      viewHashMap.put(id, view)
    }
    return view as T?
  }

  protected fun setText(
    @IdRes id: Int,
    text: String?
  ) {
    (getView<View>(id) as TextView?)!!.text = text
  }

}