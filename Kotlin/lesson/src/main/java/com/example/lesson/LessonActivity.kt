package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseView
import com.example.lesson.entity.Lesson
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener{
    private val lessonPresenter = LessonPresenter(this)

    private var lessonAdapter = LessonAdapter()

    override fun getPresenter(): LessonPresenter {
        return lessonPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = lessonAdapter
        list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        swipe_refresh_layout.setOnRefreshListener { getPresenter().fetchData() }
        swipe_refresh_layout.isRefreshing = true

        getPresenter().fetchData()
    }

    fun showResult(lessons: List<Lesson>){
        lessonAdapter.updateAndNotify(lessons)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        getPresenter().fetchData()
        return false
    }


}