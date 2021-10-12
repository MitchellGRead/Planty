package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun HomeScreen(name: String) {
    Surface {
        Text(text = "Hello $name!")
    }
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PlantyTheme {
        HomeScreen("Android")
    }
}
