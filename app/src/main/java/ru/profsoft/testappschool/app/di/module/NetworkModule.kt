package ru.profsoft.testappschool.app.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moczul.ok2curl.CurlInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.profsoft.testappschool.BuildConfig
import ru.profsoft.testappschool.data.nework.AuthApi
import ru.profsoft.testappschool.data.nework.CourseApi
import ru.profsoft.testappschool.data.nework.interceptors.TokenInterceptor
import ru.profsoft.testappschool.data.storage.Pref
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson():Gson =
        GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @Named(WITHOUT_AUTH_CLIENT)
    fun provideOkHttpClient():OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(CurlInterceptor {
                Timber.v(it)
            })
            .build()


    @Provides
    @Singleton
    @Named(WITHOUT_AUTH_RETROFIT)
    fun provideRetrofit (gson: Gson, @Named(WITHOUT_AUTH_CLIENT) okHttpClient:OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideAuthApi(@Named(WITHOUT_AUTH_RETROFIT) retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    fun provideTokenInterceptor (pref: Pref) = TokenInterceptor(pref)

    @Provides
    @Singleton
    @Named(AUTH_CLIENT)
    fun provideProfileClient(pref:Pref):OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(provideTokenInterceptor(pref))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(CurlInterceptor {
                Timber.v(it)
            })
            .build()

    @Provides
    @Singleton
    @Named(AUTH_RETROFIT)
    fun provideProfileRetrofit (gson: Gson, @Named(AUTH_CLIENT) okHttpClient:OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCourseApi(@Named(AUTH_RETROFIT) retrofit: Retrofit): CourseApi =
        retrofit.create(CourseApi::class.java)

    companion object {
        private const val WITHOUT_AUTH_CLIENT = "without_auth_client"
        private const val WITHOUT_AUTH_RETROFIT = "without_auth_retrofit"

        private const val AUTH_CLIENT = "auth_client"
        private const val AUTH_RETROFIT = "auth_retrofit"
    }
}