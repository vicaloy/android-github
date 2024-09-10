package com.example.github.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.github.BuildConfig
import com.example.github.domain.action.GetRepo
import com.example.github.domain.repository.RepoRepository
import com.example.github.infra.client.RepoClient
import com.example.github.infra.repository.RepoRemoteRepository
import com.example.github.network.AuthInterceptor
import com.example.github.network.isNetworkAvailable
import com.example.github.ui.repos.ReposViewModelFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Di {

    fun provideReposViewModelFactory(
        context: Context,
        owner: SavedStateRegistryOwner
    ): ViewModelProvider.Factory {
        return ReposViewModelFactory(owner, provideGetRepo(context))
    }

    private fun provideGetRepo(context: Context) = GetRepo(provideRepoRepository(context))

    private fun provideRepoRepository(context: Context): RepoRepository {
        return RepoRemoteRepository(provideRepoClient(context))
    }

    private fun provideRepoClient(context: Context): RepoClient {
        val retrofit = provideRetrofit(providerOkHttpClient(context))
        return retrofit.create(RepoClient::class.java)
    }

    private fun providerOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cache = Cache(context.cacheDir, cacheSize)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.TOKEN))
            .addInterceptor(interceptor)
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isNetworkAvailable(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 300).build()
                else
                    request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 86400)
                        .build()
                chain.proceed(request)
            }
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}