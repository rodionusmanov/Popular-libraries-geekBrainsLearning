package com.example.popularlibraries.di

import com.example.popularlibraries.BuildConfig
import com.example.popularlibraries.PopularLibrariesApp
import com.example.popularlibraries.core.network.UsersApi
import com.example.popularlibraries.repository.impl.GithubRepositoryImpl
import com.example.popularlibraries.user.UserInfoPresenter
import com.example.popularlibraries.user.UserPresenter
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class PresentersModule {

    @Inject
    lateinit var currentRouter: Router

    val router : Router by lazy {
        PopularLibrariesApp.instance.applicationComponent.inject(this)
        currentRouter
    }

    @Provides
    @Singleton
    fun provideUserPresenter(): UserPresenter {
        return UserPresenter(
            provideImpl(),
            router
        )
    }

    @Provides
    @Singleton
    fun provideUserInfoPresenter(): UserInfoPresenter {
        return UserInfoPresenter(
            provideImpl(),
            router,
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    @Singleton
    fun provideImpl(): GithubRepositoryImpl {
        return GithubRepositoryImpl(
            provideUsersApi(),
            PopularLibrariesApp.instance.database.userDao(),
            PopularLibrariesApp.instance.getConnectSingle()
        )
    }

    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        val usersApi: UsersApi by lazy { provideRetrofit().create(UsersApi::class.java) }
        return usersApi
    }

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .client(provideClientWithInterceptor())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()

    @Provides
    @Singleton
    fun provideClientWithInterceptor(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "MY_API_KEY")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()
}