package com.codingambitions.jetpackcomposetutorial7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codingambitions.jetpackcomposetutorial7.screens.RootNavGraph
import com.codingambitions.jetpackcomposetutorial7.ui.theme.JetpackComposeTutorial7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorial7Theme {
                RootNavGraph()
            }
        }
    }
}


