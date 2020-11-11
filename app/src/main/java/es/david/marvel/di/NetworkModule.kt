package es.david.marvel.di

import es.david.marvel.data.network.AuthInterceptor
import es.david.marvel.data.network.MarvelAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single { provideLoggingInterceptor() }
    single { AuthInterceptor() }
    single { provideHTTPClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideMarvelAPI(get()) }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/")
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideHTTPClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun provideMarvelAPI(retrofit: Retrofit): MarvelAPI = retrofit.create(MarvelAPI::class.java)

