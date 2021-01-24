package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LessonPresenter {
    companion object{
        private const val LESSON_PATH = "lessons"
    }

    private lateinit var activity: LessonActivity
    constructor(activity: LessonActivity){
        this.activity = activity
    }

    private var lessons : List<Lesson> = arrayListOf()
    private val type : Type = object : TypeToken<List<Lesson>>(){}.type

    fun fetchData(){
        HttpClient.INSTANCE.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>>{
            override fun onSuccess(lessons: List<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread { Utils.toast(message) }
            }

        })
    }

    fun showPlayback(){
        var playbackLessons : MutableList<Lesson> = arrayListOf()
        for (lesson in lessons){
            if (lesson.state == Lesson.State.PLAYBACK){
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }
}