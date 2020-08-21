package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import android.widget.Toolbar.OnMenuItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.core.BaseView
import com.example.lesson.R.id
import com.example.lesson.R.layout
import com.example.lesson.R.menu
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter?>, OnMenuItemClickListener {
  override val presenter: LessonPresenter by lazy {
    LessonPresenter(this)
  }

  private val lessonAdapter = LessonAdapter()
  private lateinit var refreshLayout: SwipeRefreshLayout
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_lesson)
    val toolbar = findViewById<Toolbar>(id.toolbar)
    toolbar.inflateMenu(menu.menu_lesson)
    toolbar.setOnMenuItemClickListener(this)
    findViewById<RecyclerView>(id.list).run {
      this.layoutManager = LinearLayoutManager(this@LessonActivity)
      this.adapter = lessonAdapter
      this.addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
    }

    findViewById<SwipeRefreshLayout>(id.swipe_refresh_layout).run {
      refreshLayout = this

      refreshLayout.setOnRefreshListener(
          OnRefreshListener { presenter.fetchData() })
      refreshLayout.setRefreshing(true)
    }
    presenter.fetchData()
  }

  fun showResult(lessons: List<Lesson>) {
    lessonAdapter.updateAndNotify(lessons)
    refreshLayout.isRefreshing = false
  }

  override fun onMenuItemClick(item: MenuItem): Boolean {
    presenter.showPlayback()
    return false
  }
}