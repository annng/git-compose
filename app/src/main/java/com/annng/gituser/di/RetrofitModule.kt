package com.annng.gituser.di

import android.content.Context
import com.annng.gituser.BuildConfig
import com.annng.gituser.data.remote.GitApi
import com.annng.gituser.data.repository.GitRepositoryImpl
import com.annng.gituser.domain.repository.GitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(appContext : Context) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(getInterceptor(appContext))
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): GitApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context))
            .build()
            .create(GitApi::class.java)
    }

    @Provides
    @Singleton
    fun getInterceptor(context: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .header("Authorization", "Bearer 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")
                .cacheControl(CacheControl.Builder().noCache().build())
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideGitRepository(api: GitApi): GitRepository {
        return GitRepositoryImpl(api = api)
    }
}