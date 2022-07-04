package com.technolyst.bucket.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.technolyst.bucket.pages.drawer.DrawerContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {

    val navController = rememberNavController();
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        DrawerContent(navController = navController, drawerState = drawerState)
    }) {
        Scaffold(topBar = { SmallTopAppBar(title = { Text(text = "Bucket") }) }) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = "HomePage") {
                    composable("HomePage") {


                    }
                }
            }
        }
    }

}