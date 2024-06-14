package com.example.myapppart3.di

import android.content.Context
import androidx.room.Room
import com.example.myapppart3.ItemAdapter
import com.example.myapppart3.data.entities.UserDao
import com.example.myapppart3.data.entities.UserRepository
import com.example.myapppart3.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_database3"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    fun provideItemAdapter(): ItemAdapter {
        return ItemAdapter(emptyList(), onUserCheckedChangeListener = { _, _ ->  })
    }
}