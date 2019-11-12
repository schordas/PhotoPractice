package io.mjolnir.photopracticefive.network

import io.mjolnir.photopracticefive.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.ACCESS_KEY}")
            .build()

        return chain.proceed(request)
    }

}