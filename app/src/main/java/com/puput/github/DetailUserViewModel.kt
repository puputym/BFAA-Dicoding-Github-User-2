package com.puput.github

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puput.github.model.DetailUserModel
import com.puput.github.model.UserList
import com.puput.github.retrofit.RetrofitAPI
import com.puput.github.retrofit.ServiceAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    val detailUser = MutableLiveData<DetailUserModel>()
    var listFollowers = MutableLiveData<ArrayList<UserList>>()
    var listFollowings = MutableLiveData<ArrayList<UserList>>()

    var followers : LiveData<ArrayList<UserList>> = listFollowers
    var following : LiveData<ArrayList<UserList>> = listFollowings


    fun getUserDetail(): MutableLiveData<DetailUserModel>{
        return detailUser
    }
    fun setDetailUser(username : String){
        setListFollowers(username)
        setListFollowings(username)
        val retrofitAPI = RetrofitAPI.getRetrofitAPI().create(ServiceAPI::class.java)
        val call = retrofitAPI.getUserDetail(username)
        call.enqueue(object : Callback<DetailUserModel?> {
            override fun onResponse(
                call: Call<DetailUserModel?>,
                response: Response<DetailUserModel?>
            ) {
                if (response.isSuccessful){
                    detailUser.postValue(response.body())
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<DetailUserModel?>, t: Throwable) {
                detailUser.postValue(null)
                Log.d("Failure", t.message.toString())
            }
        })
    }


    fun setListFollowers(username: String){
        val retrofitAPI = RetrofitAPI.getRetrofitAPI().create(ServiceAPI::class.java)
        val call = retrofitAPI.getUserFollowers(username)
        call.enqueue(object : Callback<ArrayList<UserList>?> {
            override fun onResponse(
                call: Call<ArrayList<UserList>?>,
                response: Response<ArrayList<UserList>?>
            ) {
                if (response.isSuccessful){
                    listFollowers.postValue(response.body())
                }
                Log.d("FOLLOWERSFRAGMENT",listFollowers.toString())
            }

            override fun onFailure(call: Call<ArrayList<UserList>?>, t: Throwable) {
                Log.d("FOLLOWERSFRAGMENT", t.message.toString())
            }
        })
    }

    fun setListFollowings(username: String){
        val retrofitAPI = RetrofitAPI.getRetrofitAPI().create(ServiceAPI::class.java)
        val call = retrofitAPI.getUserFollowings(username)
        call.enqueue(object : Callback<ArrayList<UserList>?> {
            override fun onResponse(
                call: Call<ArrayList<UserList>?>,
                response: Response<ArrayList<UserList>?>
            ) {
                listFollowings.postValue(response.body())
                Log.d("FOLLOWINGSFRAGMENT",listFollowings.toString())
            }

            override fun onFailure(call: Call<ArrayList<UserList>?>, t: Throwable) {
                Log.d("FOLLOWINGFRAGMENT", t.message.toString())
            }
        })
    }

}