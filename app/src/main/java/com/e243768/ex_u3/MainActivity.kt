package com.e243768.ex_u3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.e243768.ex_u3.ui.navigation.PokeNavigation
import com.e243768.ex_u3.ui.theme.EX_U3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EX_U3Theme {
                PokeNavigation()
            }
        }
    }
}