package com.example.paginationcompose.di

import com.example.paginationcompose.data.network.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { headerInterceptor() }
    single { okhttpClient(get()) }
    single { retrofit(get()) }
    single { apiService(get()) }
}

fun apiService(
    retrofit: Retrofit
): MovieApi =
    retrofit.create(MovieApi::class.java)



fun retrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("base url")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient(headerInterceptor: Interceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
   return OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(interceptor)
        .build()
}

fun headerInterceptor(): Interceptor =
    Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
           // .addQueryParameter("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.YzRhYzJjNDRmYTQ1YWVkMTRmZDJlYjBhMGFlYjNhNDI6NTA1MTcyNTQwMDE5YTYxNTNhNTU2NGM1Yzc5N2RmMDg1N2VjMjRkNTllNTRmYmU1OGY5MmE0NDRmMjVhODkwNWI1NDdiNTJjMGRkZjExMzUzM2VkMjEyODZiNTFkYjIwM2UyYjVmZjM5NjQ5Y2Q2OGViNGVkYjUwODc2ZWI0NzQ5YzcwZDU2MGNhYmFmNWIzYTFmMTE0MzZmMTU1MWFmYzgzYTkyMzI0NTMxZmMzMmQxM2M1NmQwN2MyNjIzZjVhODA3YjUwZDZlMWQxNDAxM2FhNGEzODViYzcxNjhmNDVkMzg5MGU1MmJkMjU3ODU3YTc1ZjMyYjlkMTUwMjhkOTBkOTI3OTJkZWVjYTBjMTM2NWViZTExYjEwZGVjZGNlMDVhM2ZlZTU5NmFmMjE5NDQzM2JmZjBiNGJhMzlkODc2NzU0M2FmYmJlNmEyZDllZThlNDVmZTU5NTdmNWRmNzg4OGYzZDI0NWM5Y2M5NzliOWVlN2RkNWNhNGEyNjYxZmRhNzhmNjM0YmMyNWUyMGM5OWZjZDQxMDQ0ZDE5ZjQ4M2YyMTljMGI5N2NjMWI1M2FjNDc4ZDUyODg1MzBjNDI0YjFjZjdhOWJiYjdkMjY4MjNhZGE4MDQxZjhiZDY1MTE5M2E4NGEwZTM0NDU5NTY3N2M2MjVkZmE3ZDY5MWNlZTYzNjRkMTc2NzgyY2E4ZjAzZTQ0OGY4NjdhOWI4ODEyMWI1MzI5ZTg5MTQzMWJkYWJjNjNjYWI0MDM5YTYwZGI5Yzk4MmM3ZjI0NjQ4ZTM3MDcwZTg1MDYwNjM3ZDAzMmVkNDIxNDk5NmFkZGE2YWM4YzY3YjAyZTE1M2FlYTI1ZTg1MjAwMjE4NmY1YTAwY2M5MTMwNjQzMjRhYTJh.MMLRifNYAM4qQlrkJ1MuPfpPBwLJGTWWZUAg66Rv_P4")
          //  .addQueryParameter("api_key", "20356f3c1a22878408763bf9a991e01b")
            .build()

        val newRequest = request.newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.YzRhYzJjNDRmYTQ1YWVkMTRmZDJlYjBhMGFlYjNhNDI6NTA1MTcyNTQwMDE5YTYxNTNhNTU2NGM1Yzc5N2RmMDg1N2VjMjRkNTllNTRmYmU1OGY5MmE0NDRmMjVhODkwNWI1NDdiNTJjMGRkZjExMzUzM2VkMjEyODZiNTFkYjIwM2UyYjVmZjM5NjQ5Y2Q2OGViNGVkYjUwODc2ZWI0NzQ5YzcwZDU2MGNhYmFmNWIzYTFmMTE0MzZmMTU1MWFmYzgzYTkyMzI0NTMxZmMzMmQxM2M1NmQwN2MyNjIzZjVhODA3YjUwZDZlMWQxNDAxM2FhNGEzODViYzcxNjhmNDVkMzg5MGU1MmJkMjU3ODU3YTc1ZjMyYjlkMTUwMjhkOTBkOTI3OTJkZWVjYTBjMTM2NWViZTExYjEwZGVjZGNlMDVhM2ZlZTU5NmFmMjE5NDQzM2JmZjBiNGJhMzlkODc2NzU0M2FmYmJlNmEyZDllZThlNDVmZTU5NTdmNWRmNzg4OGYzZDI0NWM5Y2M5NzliOWVlN2RkNWNhNGEyNjYxZmRhNzhmNjM0YmMyNWUyMGM5OWZjZDQxMDQ0ZDE5ZjQ4M2YyMTljMGI5N2NjMWI1M2FjNDc4ZDUyODg1MzBjNDI0YjFjZjdhOWJiYjdkMjY4MjNhZGE4MDQxZjhiZDY1MTE5M2E4NGEwZTM0NDU5NTY3N2M2MjVkZmE3ZDY5MWNlZTYzNjRkMTc2NzgyY2E4ZjAzZTQ0OGY4NjdhOWI4ODEyMWI1MzI5ZTg5MTQzMWJkYWJjNjNjYWI0MDM5YTYwZGI5Yzk4MmM3ZjI0NjQ4ZTM3MDcwZTg1MDYwNjM3ZDAzMmVkNDIxNDk5NmFkZGE2YWM4YzY3YjAyZTE1M2FlYTI1ZTg1MjAwMjE4NmY1YTAwY2M5MTMwNjQzMjRhYTJh.MMLRifNYAM4qQlrkJ1MuPfpPBwLJGTWWZUAg66Rv_P4")
            .build()
        chain.proceed(newRequest)
    }