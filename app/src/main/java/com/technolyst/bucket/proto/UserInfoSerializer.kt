package com.technolyst.bucket.proto

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.technolyst.bucket.UserInfo
import java.io.InputStream
import java.io.OutputStream

object UserInfoSerializer : Serializer<UserInfo> {
    override val defaultValue: UserInfo
        get() = UserInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserInfo {
        try {
            return UserInfo.parseFrom(input)
        } catch (exp: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exp)
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        t.writeTo(output)
    }
}