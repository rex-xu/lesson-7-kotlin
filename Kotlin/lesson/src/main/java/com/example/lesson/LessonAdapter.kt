package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.core.BaseViewHolder
import com.example.lesson.LessonAdapter.LessonViewHolder
import com.example.lesson.R.color
import com.example.lesson.R.id
import com.example.lesson.R.layout
import com.example.lesson.entity.Lesson
import com.example.lesson.entity.Lesson.State.LIVE
import com.example.lesson.entity.Lesson.State.PLAYBACK
import com.example.lesson.entity.Lesson.State.WAIT
import java.util.ArrayList

class LessonAdapter : Adapter<LessonViewHolder>() {
  private var list: List<Lesson> = ArrayList()
  fun updateAndNotify(list: List<Lesson>) {
    this.list = list
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LessonViewHolder {
    return LessonViewHolder.onCreate(parent)
  }

  override fun onBindViewHolder(
    holder: LessonViewHolder,
    position: Int
  ) {
    holder.onBind(list[position])
  }

  /**
   * 静态内部类
   */
  class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(
      itemView
  ) {
    fun onBind(lesson: Lesson) {
      var date = lesson.date
      if (date == null) {
        date = "日期待定"
      }
      setText(id.tv_date, date)
      setText(id.tv_content, lesson.content)
      val state = lesson.state
      if (state != null) {
        setText(id.tv_state, state.stateName())
        var colorRes = color.playback
        colorRes = when (state) {
          PLAYBACK -> {

            // 即使在 {} 中也是需要 break 的。
            color.playback
          }
          LIVE -> color.live
          WAIT -> color.wait
        }
        val backgroundColor = itemView.context
            .getColor(colorRes)
        getView<View>(id.tv_state)!!.setBackgroundColor(backgroundColor)
      }
    }

    companion object {
      fun onCreate(parent: ViewGroup): LessonViewHolder {
        return LessonViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(layout.item_lesson, parent, false)
        )
      }
    }
  }
}