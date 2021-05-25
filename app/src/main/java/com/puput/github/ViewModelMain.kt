package com.puput.github


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puput.github.model.UserList
import com.puput.github.retrofit.RetrofitAPI
import com.puput.github.retrofit.ServiceAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMain: ViewModel(){
    val rvData = MutableLiveData<ArrayList<UserList>>()

    fun getSearchData(): MutableLiveData<ArrayList<UserList>>{
        return rvData
    }
    fun setSearchData(query : String){
        val retrofitAPI = RetrofitAPI.getRetrofitAPI().create(ServiceAPI::class.java)
        val call = retrofitAPI.getSearchData(query)
        call.enqueue(object : Callback<UserData?> {
            override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
                if (response.isSuccessful){
                    rvData.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserData?>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
    }


}