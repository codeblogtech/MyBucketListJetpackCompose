package com.technolyst.bucket.pages.drawer


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DrawerHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Prashant Goyal",
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "xyz@gmail.com", fontSize = TextUnit(14F, TextUnitType.Sp), color = Color.Gray)


    }

}