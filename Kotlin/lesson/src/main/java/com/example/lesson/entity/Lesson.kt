package com.example.lesson.entity

class Lesson {

    lateinit var date: String
    lateinit var content: String
    lateinit var state: State

    constructor(date: String, content: String, state: State){
        this.date = date
        this.content = content
        this.state = state
    }

    enum class State(var stateName: String){
        PLAYBACK("有回放"),
        LIVE("正在直播"),
        WAIT("等待中");
    }
}