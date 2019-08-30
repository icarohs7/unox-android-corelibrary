package base.corelibrary.domain.extensions

import base.corelibrary.domain.toplevel.kget
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import splitties.init.appCtx
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 * Short hand version to create a retrofit instance
 * with the given [baseUrl] and with that create
 * an instance of a given service, which also
 * modifies the response, adding or setting the
 * "Content-Type" header to "application/json"
 */
inline fun <reified T> createRetrofitServiceJsonResponse(
        baseUrl: String,
        noinline clientExtraConfig: OkHttpClient.Builder.() -> Unit = {},
        builderExtraConfig: Retrofit.Builder.() -> Unit = {}
): T {
    val clientSetup: OkHttpClient.Builder.() -> Unit = {
        addInterceptor {
            val req = it.request()
            val res = it.proceed(req)
                    .newBuilder()
                    .header("Content-Type", "application/json")
                    .build()
            res
        }
    }

    return createRetrofitService(baseUrl, {
        clientSetup()
        clientExtraConfig()
    }, builderExtraConfig)
}

/**
 * Short hand version to create a retrofit instance
 * with the given [baseUrl] and with that create
 * an instance of a given service
 */
inline fun <reified T> createRetrofitService(
        baseUrl: String,
        noinline clientExtraConfig: OkHttpClient.Builder.() -> Unit = {},
        builderExtraConfig: Retrofit.Builder.() -> Unit = {}
): T {
    return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(RetrofitExtensions.getKotlinxSerializationConverter())
            .client(RetrofitExtensions.getHttpClient(clientExtraConfig))
            .apply(builderExtraConfig)
            .build()
            .create()
}

object RetrofitExtensions {
    fun getKotlinxSerializationConverter(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json
                .nonstrict
                .asConverterFactory(contentType)
    }

    fun getHttpClient(clientExtraConfig: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient {


        return OkHttpClient
                .Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(getHttpInspectorInterceptor())
                .apply(clientExtraConfig)
                .build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("Retrofit").d(message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private fun getHttpInspectorInterceptor(): Interceptor {
        val collector: ChuckerCollector = kget()
        return ChuckerInterceptor(appCtx, collector)
    }
}