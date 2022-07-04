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
import com.technolyst.bucket.dataStore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DrawerHeader() {

    val context = LocalContext.current
    val userNameKey = stringPreferencesKey("user_name")

    val userName = flow<String> {
        context.dataStore.data.map {
            it[userNameKey]
        }.collect(collector = {
            if (it != null) {
                this.emit(it)
            }
        })

    }.collectAsState(initial = "")


    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = userName.value,
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "xyz@gmail.com", fontSize = TextUnit(14F, TextUnitType.Sp), color = Color.Gray)


    }

}