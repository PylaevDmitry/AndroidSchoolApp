package ru.profsoft.testappschool.data.nework.interceptors

import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.profsoft.testappschool.data.nework.AuthApi
import ru.profsoft.testappschool.data.storage.Pref
import java.net.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator
//@Inject constructor(
//    private val authApi: AuthApi,
//    private val pref: Pref
//) : Authenticator {
//
////    override fun authenticate(route: Route?, response: Response): Request? {
////        if (response.code == 401) {
////            pref.isAuth = false
////            val tokenRes = authApi.token(pref.token).execute()
//            val refreshRes = authApi.refreshToken(pref.refreshToken).execute()
//            return if (refreshRes.isSuccessful) {
//                pref.token = refreshRes.body()!!.token
//                pref.refreshToken = refreshRes.body()!!.refresh
//                pref.isAuth = true
//                response.request.newBuilder()
//                    .header("Authorization", "Bearer ${refreshRes.body()!!.token}")
//                    .build()
//            } else {
//                null
//            }
//        }
//        return null
//    }
//}