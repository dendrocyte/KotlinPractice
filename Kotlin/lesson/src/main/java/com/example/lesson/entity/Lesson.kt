package com.example.lesson.entity

//因為不想讓其他的模組使用到, 將Lesson改成internal
//相關用到的function 也要改成internal
internal class Lesson {

    lateinit var date: String
    lateinit var content: String
    lateinit var state: State

    constructor(date: String, content: String, state: State){
        this.date = date
        this.content = content
        this.state = state
    }

    enum class State{
        PLAYBACK{
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE{
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT{
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String
    }
}