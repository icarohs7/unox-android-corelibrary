package base.corelibrary.domain.extensions

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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
            .addConverterFactory(RetrofitExtensions.getJsonConverter())
            .client(RetrofitExtensions.getHttpClient(clientExtraConfig))
            .apply(builderExtraConfig)
            .build()
            .create()
}

object RetrofitExtensions {
    fun getJsonConverter(): Converter.Factory {
        val contentType = MediaType.get("application/json")
        return Json
                .nonstrict
                .asConverterFactory(contentType)
    }

    fun getHttpClient(clientExtraConfig: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient {
        return OkHttpClient
                .Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(getInterceptor())
                .addInterceptor(ChuckInterceptor(appCtx))
                .addInterceptor(StethoInterceptor())
                .apply(clientExtraConfig)
                .build()
    }

    private fun getInterceptor(): HttpLoggingInterceptor {
        val log = { s: String -> Timber.tag("Retrofit").d(s) }
        return HttpLoggingInterceptor(log)
                .apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}