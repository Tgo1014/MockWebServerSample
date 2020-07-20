package tgo1014.mockwebserver

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/posts")
    fun getPosts(): Call<List<DemoResponseItem>>
}