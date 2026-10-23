package com.example.habittracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HabitViewModel : ViewModel() {

    private val initialHabits = listOf(
        Habit(id = 1, title = "Пробіжка", icon = "🏃"),
        Habit(id = 2, title = "Випити 2л води", icon = "💧"),
        Habit(id = 3, title = "Читання 20 хв", icon = "📖"),
        Habit(id = 4, title = "Медитація", icon = "🧘"),
        Habit(id = 5, title = "Щоденник", icon = "📝"),
        Habit(id = 6, title = "Гітара 15 хв", icon = "🎸")
    )

    private val _habits = MutableStateFlow(initialHabits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    val completedCount: StateFlow<Int> = habits
        .map { it.count { h -> h.isDone } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val totalHabitsCount: Int = initialHabits.size

    fun toggleHabit(id: Int) {
        _habits.update { currentList ->
            currentList.map { habit ->
                if (habit.id == id) habit.copy(isDone = !habit.isDone) else habit
            }
        }
    }

    fun resetDay() {
        _habits.update { currentList ->
            currentList.map { it.copy(isDone = false) }
        }
    }
}