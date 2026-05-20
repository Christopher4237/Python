package com.example.python2

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.python2.ui.theme.Python2Theme
import kotlinx.coroutines.delay
import kotlin.Int
import kotlin.collections.toList
import kotlin.ranges.until

@Composable
fun PythonGrid(
    //pythonViewModel: PythonViewModel = viewModel(),
    speed: Long,
    score: Int,
    food: SnapshotStateList<Int>,
    currentPythonPieces: MutableMap<Int, Int>,
    barriers: SnapshotStateList<Int>,
    direction: Direction,
    accelerateUp: () -> Unit,
    updateDirectionUp: () -> Unit,
    accelerateDown: () -> Unit,
    updateDirectionDown: () -> Unit,
    accelerateLeft: () -> Unit,
    updateDirectionLeft: () -> Unit,
    accelerateRight: () -> Unit,
    updateDirectionRight: () -> Unit,
    directionsEnabled: List<Direction>,
    returnHome: () -> Unit,
    bodyColor: Color,
    foodColor: Color,
    poisonColor: Color,
    modifier: Modifier = Modifier
) {
    val data = (0 until 50*50).toList()

    //val pythonUiState by pythonViewModel.uiState.collectAsState()



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.15f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.15f)
            )
            Text(
                text = "Score: ${score}"
            )
            Spacer(
                modifier = Modifier
                    .weight(0.15f)
            )
            Text(
                text = "Speed: ${speed} ms"
            )
            Spacer(
                modifier = Modifier
                    .weight(0.15f)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(50),
            modifier = Modifier
                .size(
                    350.dp,
                    350.dp
                )
        ) {
            items(data) { item ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .border(
                            0.5.dp,
                            color = Color.Black
                        )
                        .size(
                            10.dp, 7.dp
                        )
                        .background(
                            color = when(item) {
                                //in currentPythonPieces.values -> Color.Black
                                //in food -> Color.Red
                                //in barriers -> Color.Blue
                                in currentPythonPieces.values -> bodyColor
                                in food -> foodColor
                                in barriers -> poisonColor
                                else -> Color.Transparent
                            }
                        )
                ) {}
            }
        }
        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Button(
                onClick = if(direction == Direction.Up) {
                    {
                        accelerateUp()
                    }
                } else {
                    {
                        updateDirectionUp()
                    }
                },
                enabled = Direction.Up in directionsEnabled
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Button(
                onClick = if(direction == Direction.Left) {
                    {
                        accelerateLeft()
                    }
                } else {
                    {
                        updateDirectionLeft()
                    }
                },
                enabled = Direction.Left in directionsEnabled
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Button(
                onClick = if(direction == Direction.Right) {
                    {
                        accelerateRight()
                    }
                } else {
                    {
                        updateDirectionRight()
                    }
                },
                enabled = Direction.Right in directionsEnabled
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Button(
                onClick = if(direction == Direction.Down) {
                    {
                        accelerateDown()
                    }
                } else {
                    {
                        updateDirectionDown()
                    }
                },
                enabled = Direction.Down in directionsEnabled
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.08f)
        )
        Button(
            onClick = returnHome
        ) {
            Text(
                text = "Return Home"
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.12f)
        )
        /*
        Text(
            text = pythonUiState.isGameOver.toString()
        )
        Text(
            text = pythonUiState.currentPythonPieces.toList().toString()
        )
         */
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PythonGridPreview() {
    Python2Theme {
        PythonGrid(
            speed = 0L,
            score = 0,
            food = mutableStateListOf(),
            currentPythonPieces = mutableMapOf(),
            barriers = mutableStateListOf(),
            direction = Direction.Right,
            accelerateUp = {},
            updateDirectionUp = {},
            accelerateDown = {},
            updateDirectionDown = {},
            accelerateLeft = {},
            updateDirectionLeft = {},
            accelerateRight = {},
            updateDirectionRight = {},
            directionsEnabled = listOf(),
            returnHome = {},
            bodyColor = Color.Black,
            poisonColor = Color.Black,
            foodColor = Color.Black,
        )
    }
}
