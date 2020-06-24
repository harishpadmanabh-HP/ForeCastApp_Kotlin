package com.hp.forecastapp_kotlin.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="bc7e4beea0c80cd26ff531e1e849d9ae"

//http://api.weatherstack.com/current?access_key=bc7e4beea0c80cd26ff531e1e849d9ae&query=trivandrum

interface ApixuWeatherApiService {

    //endpoint calls
@GET("current")
fun getCurrentWeather(@Query("query") loc:String):Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke():ApixuWeatherApiService{
            val requestInterceptor = Interceptor{chain ->

                //adding api key
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

//creating request
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

        //adding interceptor
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            //building retrofit
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }

    }

}