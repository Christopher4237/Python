package com.example.python2

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

data class PythonUiState (
    val currentPythonPieces: MutableMap<Int, Int> = mutableMapOf(0 to 3, 1 to 2, 2 to 1, 3 to 0),
    val directionsEnabled: List<Direction> = listOf(Direction.Up, Direction.Down, Direction.Right),
    val direction: Direction = Direction.Right,
    val food: SnapshotStateList<Int> = mutableStateListOf(),
    val score: Int = 0,
    val barriers: SnapshotStateList<Int> = mutableStateListOf(),
    val isGameOver: Boolean = false,
    val colors: Map<Color, String> = mapOf(Color.Black to "Black", Color.Blue to "Blue",
        Color.Red to "Red", Color.LightGray to "Light Gray",
        Color.Cyan to "Cyan", Color.DarkGray to "Dark Gray", Color.Gray to "Gray",
        Color.Green to "Green", Color.Magenta to "Magenta", Color.Yellow to "Yellow"),
    var foodColor: Color? = null,
    var poisonColor: Color? = null,
    var headPythonColor: Color? = null,
    var bodyPythonColor: Color? = null,
    val speed: Long = 100L
)