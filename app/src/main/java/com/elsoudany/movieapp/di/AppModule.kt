package com.elsoudany.movieapp.di

import android.app.Application
import androidx.room.Room
import com.elsoudany.movieapp.BuildConfig
import com.elsoudany.movieapp.TMDP_BASE_URL
import com.elsoudany.movieapp.data.local.AppDatabase
import com.elsoudany.movieapp.data.remote.MovieApiService
import com.elsoudany.movieapp.data.repository.MoviesRepository
import com.elsoudany.movieapp.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRepository(remoteDataSource: MovieApiService, appDatabase: AppDatabase): MoviesRepository {
        return MoviesRepositoryImpl(remoteDataSource, appDatabase)
    }

    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient): MovieApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TMDP_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        client.addInterceptor(loggingInterceptor)
        //Add Api key
        client.addInterceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
            builder.addHeader(
                "Authorization",
                "Bearer " + BuildConfig.TMDP_API
            )
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "movie_database")
            .allowMainThreadQueries()
            .build()
    }

}