package com.integrative.roommonitor.di

import com.integrative.roommonitor.api.ObjectDataApi
import com.integrative.roommonitor.api.ObjectDataStatusUpdateApi
import com.integrative.roommonitor.api.RoomDetailsApi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.socketio.client.SocketIoClient
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private const val BASE_URL = "https://roommonitor-api.herokuapp.com/"

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

    private val protocol = SocketIoClient({ BASE_URL })
    private val configuration = Scarlet.Configuration(
        messageAdapterFactories = listOf(MoshiMessageAdapter.Factory()),
        streamAdapterFactories = listOf(CoroutinesStreamAdapterFactory())
    )

    @Provides
    @Singleton
    fun provideScarlet(): Scarlet =
        Scarlet(protocol, configuration)

    @Provides
    @Singleton
    fun provideObjectDataStatusUpdateApi(scarlet: Scarlet): ObjectDataStatusUpdateApi =
        scarlet.create(ObjectDataStatusUpdateApi::class.java)
}