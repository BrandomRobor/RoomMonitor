package com.integrative.roommonitor.di

import com.integrative.roommonitor.api.ObjectDataApi
import com.integrative.roommonitor.api.RoomDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.socket.client.IO
import io.socket.client.Socket
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private const val BASE_URL = "https://room-monitor-api.herokuapp.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRoomDetailsApi(retrofit: Retrofit): RoomDetailsApi =
        retrofit.create(RoomDetailsApi::class.java)

    @Provides
    @Singleton
    fun provideObjectDataApi(retrofit: Retrofit): ObjectDataApi =
        retrofit.create(ObjectDataApi::class.java)

    @Provides
    @Singleton
    fun provideSocketClient(): Socket =
        IO.socket(BASE_URL)
}