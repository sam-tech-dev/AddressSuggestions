package com.airtel.addresssuggestions.di.module


import android.content.Context
import com.airtel.addresssuggestions.AddressSuggestions
import com.airtel.addresssuggestions.BuildConfig
import com.airtel.addresssuggestions.data.remote.ApiRequests
import com.airtel.addresssuggestions.di.annotations.ApplicationContext
import com.airtel.addresssuggestions.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor




@Module
class AppModule {

    @ApplicationContext
    @Provides
    internal  fun getApplicationContext(application: AddressSuggestions) : Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal  fun getApplication() : AddressSuggestions {
        return AddressSuggestions.getInstance()
    }


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    internal fun provideApiRequests(): ApiRequests {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiRequests::class.java)
    }


    @Provides
    internal fun provideScheduler(): AppSchedulerProvider{
        return AppSchedulerProvider()
    }

}
