package com.technolyst.bucket

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.technolyst.bucket.proto.UserInfoSerializer

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "MyBucket")
val Context.userInfoDataStore : DataStore<UserInfo> by dataStore(fileName = "userinfo.pb", serializer = UserInfoSerializer)

class MyBucketListApplication : Application() {

}