package com.puput.github.retrofit

import com.puput.github.UserData
import com.puput.github.model.DetailUserModel
import com.puput.github.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchData(@Query(value = "q")query : String): Call<UserData>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
     fun getUserDetail(@Path("username") username : String): Call<DetailUserModel>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(@Path("username") username : String):  Call<ArrayList<UserList>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowings(@Path("username") username : String): Call<ArrayList<UserList>>

}