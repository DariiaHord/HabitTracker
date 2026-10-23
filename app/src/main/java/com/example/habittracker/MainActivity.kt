package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.habittracker.ui.HabitScreen
import com.example.habittracker.viewmodel.HabitViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    HabitScreen(viewModel = viewModel)
                }
            }
        }
    }
}