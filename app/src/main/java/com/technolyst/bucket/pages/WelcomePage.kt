package com.technolyst.bucket.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import com.technolyst.bucket.dataStore
import com.technolyst.bucket.userInfoDataStore
import kotlinx.coroutines.launch


@Composable
fun WelcomePage(navController: NavHostController?) {

    var inputValue = rememberSaveable { mutableStateOf("") }
    var bioTexValue = rememberSaveable { mutableStateOf("") }
    var context = LocalContext.current
    var scope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan), contentAlignment = Alignment.Center
    ) {

        Column {

            Text(
                text = "Welcome",
                fontSize = 26.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(25.dp))

            TextField(value = inputValue.value,
                onValueChange = {
                    inputValue.value = it
                },
                placeholder = { Text(text = "Enter Your Name") }
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(value = bioTexValue.value,
                onValueChange = {
                    bioTexValue.value = it
                },
                placeholder = { Text(text = "Enter Your Bio") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (inputValue.value.trim().isNotEmpty()
                && bioTexValue.value.trim().isNotEmpty()
            ) {
                Button(
                    onClick = {

                        scope.launch {
                            saveYourNameAndBio(context, inputValue.value, bioTexValue.value)
                        }

                        navController?.navigate("NavigationPage")


                    },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Next")
                }
            }
        }

    }
}

suspend fun saveYourNameAndBio(context: Context, value: String, userBio: String) {

    val userNameKey = stringPreferencesKey("user_name")

    // it save data in data store prefernece
    context.dataStore.edit {
        it[userNameKey] = value
    }

    //Now we are going to save these two values in proto datastore

    context.userInfoDataStore.updateData { currentUserInfo ->
        currentUserInfo.toBuilder().setUserName(value).setUserBio(userBio).build()
    }

}


@Preview
@Composable
fun ComposablePreview() {
    WelcomePage(null)
}