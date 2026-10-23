package com.example.habittracker.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habittracker.viewmodel.HabitViewModel

@Composable
fun HabitScreen(
    viewModel: HabitViewModel
) {
    val habits by viewModel.habits.collectAsStateWithLifecycle()
    val completedCount by viewModel.completedCount.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            Button(
                onClick = { viewModel.resetDay() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .height(50.dp)
            ) {
                Text("Скинути день", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🌱", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Звички",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)) {
                Text(
                    text = "Виконано сьогодні: ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "$completedCount з ${viewModel.totalHabitsCount}",
                    color = Color(0xFF34C759),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(habits, key = { it.id }) { habit ->
                    HabitCard(
                        habit = habit,
                        onHabitClick = { viewModel.toggleHabit(habit.id) }
                    )
                }
            }
        }
    }
}