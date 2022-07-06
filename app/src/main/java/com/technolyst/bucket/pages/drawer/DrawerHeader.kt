package com.technolyst.bucket.pages.drawer


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import com.technolyst.bucket.UserInfo
import com.technolyst.bucket.dataStore
import com.technolyst.bucket.userInfoDataStore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DrawerHeader() {

    val context = LocalContext.current
    val userNameKey = stringPreferencesKey("user_name")

    // in previous video i have show how to read data from
    // data store preference.
    val userName = flow<String> {
        context.dataStore.data.map {
            it[userNameKey]
        }.collect(collector = {
            if (it != null) {
                this.emit(it)
            }
        })

    }.collectAsState(initial = "")


    //Now Read info from proto data store.

    val userNameProto = flow<UserInfo> {
        context.userInfoDataStore.data.map {
            it
        }.collect(collector = {
            this.emit(it)
        })
    }.collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // now show data on UI.
        userNameProto.value?.let {
            Text(
                text = it.userName,
                fontSize = TextUnit(18F, TextUnitType.Sp),
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        userNameProto.value?.let {
            Text(text = it.userBio, fontSize = TextUnit(14F, TextUnitType.Sp), color = Color.Gray)
        }


    }

}