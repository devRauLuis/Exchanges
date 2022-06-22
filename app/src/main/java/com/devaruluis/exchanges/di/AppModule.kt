//package com.devaruluis.exchanges.di
//
//import android.content.Context
//import androidx.room.Room
//import com.devaruluis.exchanges.database.ExchangesDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//object AppModule {
//    @Module
//    @InstallIn(SingletonComponent::class)
//    object RoomModule {
//        const val EXCHANGES_DATABASE_NAME = "exchanges_database"
//
//        @Singleton
//        @Provides
//        fun provideRoom(@ApplicationContext context: Context) =
//            Room.databaseBuilder(context, ExchangesDatabase::class.java, EXCHANGES_DATABASE_NAME)
//                .build()
////
////        @Singleton
////        @Provides
////        fun provideExchangeDao(db: ExchangesDatabase) = db.getExchangeDao()
//    }
//}