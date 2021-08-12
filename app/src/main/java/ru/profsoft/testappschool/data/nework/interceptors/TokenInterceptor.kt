package ru.profsoft.testappschool.data.nework.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.profsoft.testappschool.data.storage.Pref

class TokenInterceptor (
    private val pref: Pref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = pref.token
        try {
            request = if (pref.isAuth || pref.isGuestAuth) {
                request.newBuilder().addHeader("Authorization", "Bearer $token").build()
            } else {
                request.newBuilder().build()
            }
        } catch (e: InterceptorException) {
            throw InterceptorException(e.message ?: "")
        }
        return chain.proceed(request)
    }
}