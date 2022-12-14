package com.faysalh.quotesapp

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RequestManager(context:Context) {

    var retrofit = Retrofit.Builder()
        .baseUrl("https://type.fit/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun GetAllQuotes(listener:QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callQuotes()
        call.enqueue(object :Callback<List<QuotesModel>>{
            override fun onResponse(
                call: Call<List<QuotesModel>>,
                response: Response<List<QuotesModel>>
            ) {
               if (!response.isSuccessful){
                   listener.didError(response.message())
                   return
               }
                response.body()?.let { listener.didFetch(it,response.message()) }
            }

            override fun onFailure(call: Call<List<QuotesModel>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }

    private interface CallQuotes{
        @GET("api/quotes")
        fun callQuotes(): Call<List<QuotesModel>>
    }
}