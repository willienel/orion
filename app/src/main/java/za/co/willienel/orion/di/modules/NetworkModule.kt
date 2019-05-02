package za.co.willienel.orion.di.modules

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import za.co.willienel.orion.R
import java.util.concurrent.TimeUnit

val networkModule = Kodein.Module("networkModule") {

    bind<String>(tag = "api_url") with provider {

        instance<Context>()
            .applicationContext
            .resources
            .getString(R.string.api_url)
    }

    bind<Long>(tag = "http_read_timeout_in_seconds") with provider {

        instance<Context>()
            .applicationContext
            .resources
            .getInteger(R.integer.http_read_timeout_in_seconds)
            .toLong()
    }

    bind<Long>(tag = "http_connect_timeout_in_seconds") with provider {

        instance<Context>()
            .applicationContext
            .resources
            .getInteger(R.integer.http_connect_timeout_in_seconds)
            .toLong()
    }

    bind<HttpLoggingInterceptor>() with provider {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpLoggingInterceptor
    }

    bind<OkHttpClient>() with provider {

        val readTimeoutInSeconds: Long = instance(tag = "http_read_timeout_in_seconds")
        val connectTimeoutInSeconds: Long = instance(tag = "http_connect_timeout_in_seconds")

        OkHttpClient.Builder()
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
            .connectTimeout(connectTimeoutInSeconds, TimeUnit.SECONDS)
            .build()
    }

    bind<Retrofit>() with provider {

        val apiUrl: String = instance(tag = "api_url")

        Retrofit.Builder()
            .client(instance())
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}