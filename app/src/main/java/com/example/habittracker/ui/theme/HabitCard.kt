package com.example.habittracker.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.model.Habit

@Composable
fun HabitCard(
    habit: Habit,
    onHabitClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F7F9)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onHabitClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = if (habit.isDone) Color(0xFF34C759) else Color(0xFFE5E5EA),
                modifier = Modifier.size(32.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (habit.isDone) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Готово",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "${habit.icon}  ${habit.title}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (habit.isDone) Color.Black else Color.Gray,
                modifier = Modifier.weight(1f)
            )

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (habit.isDone) Color(0xFFE8F5E9) else Color(0xFFF2F2F7)
            ) {
                Text(
                    text = if (habit.isDone) "✓ готово" else "пропущено",
                    fontSize = 12.sp,
                    color = if (habit.isDone) Color(0xFF2E7D32) else Color(0xFF8E8E93),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}