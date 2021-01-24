package com.example.app.entity

class User(){
    @JvmField
    var username: String? = null
    @JvmField
    var password: String? = null
    @JvmField
    var code: String? = null



    constructor(username: String?, password: String?, code: String?) : this(){
        this.username = username
        this.password = password
        this.code = code
    }


}