package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {

    private val gson = Gson()
    //允許json為null
    private fun <T> convert(json: String?, type: Type) : T{
        return gson.fromJson(json, type)
    }




    @SuppressWarnings("unchecked")
    fun <T> get(path: String, type: Type, entityCallback: EntityCallback<T>){
        val request: Request = Request.Builder()
            .url("https://api.hencoder.com/$path")
            .build()
        //因為是自己本身，所以用this 取代INSTANCE
        val call : Call = this.newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when(response.code()){
                    in 200..299 ->{
                        //因為不想要省略catch IOException, 以免exception造成crash
                        //當若知道不會造成IOException 就可以刪除try-catch
                        var json : String? = null
                        try {
                            json = response.body()!!.string()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        entityCallback.onSuccess(convert(json, type))
                    }
                    in 400..499 -> entityCallback.onFailure("客户端错误")
                    //java源碼寫錯
                    in 500..599 -> entityCallback.onFailure("服务器错误")
                    else -> entityCallback.onFailure("未知错误")
                }
            }
        })
    }


}