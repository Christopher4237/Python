package com.example.python2

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlin.math.exp

enum class PythonScreen(@StringRes val title: Int) {
    Home(title = R.string.home),
    Game(title = R.string.game),
    RulesSettings(title = R.string.rulessettings),
}


@Composable
fun PythonNavigation (
    pythonViewModel: PythonViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PythonScreen.valueOf(
        backStackEntry?.destination?.route ?: PythonScreen.Home.name
    )

    val pythonUiState by pythonViewModel.uiState.collectAsState()

    // State variables
    var expandedFood by remember { mutableStateOf(false) }
    var expandedBody by remember { mutableStateOf(false) }
    var expandedPoison by remember { mutableStateOf(false) }
    var expandedHead by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var selectedOptionFoodColor by remember { mutableStateOf("") }
    var selectedOptionPoisonColor by remember { mutableStateOf("") }
    var selectedOptionHeadColor by remember { mutableStateOf("") }
    var selectedOptionBodyColor by remember { mutableStateOf("") }



    var idx by remember { mutableIntStateOf(0) }


    NavHost(
        navController = navController,
        startDestination = PythonScreen.Home.name
    ) {
        composable(
            route = PythonScreen.Home.name
        ) {
            PythonHome(
                onClickPlay = {
                    navController.navigate(PythonScreen.Game.name)
                },
                onClickRulesSettings = {
                    navController.navigate(PythonScreen.RulesSettings.name)
                }
            )
        }
        composable(
            route = PythonScreen.Game.name
        ) {

            LaunchedEffect(key1 = pythonUiState.currentPythonPieces) {
                delay(pythonUiState.speed)
                pythonViewModel.movePieces()
            }

            PythonGrid(
                speed = pythonUiState.speed,
                score = pythonUiState.score,
                food = pythonUiState.food,
                currentPythonPieces = pythonUiState.currentPythonPieces,
                barriers = pythonUiState.barriers,
                direction = pythonUiState.direction,
                directionsEnabled = pythonUiState.directionsEnabled,
                accelerateUp = {
                    pythonViewModel.accelerateUp()
                },
                updateDirectionUp = {
                    pythonViewModel.updateDirectionUp()
                },
                accelerateDown = {
                    pythonViewModel.accelerateUp()
                },
                updateDirectionDown = {
                    pythonViewModel.updateDirectionUp()
                },
                accelerateLeft = {
                    pythonViewModel.accelerateUp()
                },
                updateDirectionLeft = {
                    pythonViewModel.updateDirectionUp()
                },
                accelerateRight = {
                    pythonViewModel.accelerateUp()
                },
                updateDirectionRight = {
                    pythonViewModel.updateDirectionUp()
                },
                returnHome = {
                    navController.navigate(PythonScreen.Home.name)
                }
            )
        }
        composable(
            route = PythonScreen.RulesSettings.name
        ) {
            PythonRulesSettings(
                returnHome = {
                    navController.navigate(PythonScreen.Home.name)
                },
                options = pythonUiState.colors,
                foodColor = pythonUiState.foodColor,
                //foodColor = pythonUiState.colors.keys.elementAt(pythonUiState.colors.values.indexOf(selectedOptionFoodColor)),
                poisonColor = pythonUiState.poisonColor,
                headPythonColor = pythonUiState.headPythonColor,
                bodyPythonColor = pythonUiState.bodyPythonColor,
                //selectedOption = selectedOption,
                selectedOptionFoodColor = selectedOptionFoodColor,
                selectedOptionBodyColor = selectedOptionBodyColor,
                selectedOptionPoisonColor = selectedOptionPoisonColor,
                selectedOptionHeadColor = selectedOptionHeadColor,
                expandedFood = expandedFood,
                expandedBody = expandedBody,
                expandedPoison = expandedPoison,
                expandedHead = expandedHead,
                /*
                onClick = {
                    selectedOption = it.toString()
                    expanded = false
                    idx = pythonUiState.colors.values.indexOf(selectedOption)
                },
                 */
                onClickExpandedChangeFood = { expandedFood = !expandedFood },
                onClickExpandedChangeBody = { expandedBody = !expandedBody },
                onClickExpandedChangeHead = { expandedHead = !expandedHead },
                onClickExpandedChangePoison = { expandedPoison = !expandedPoison },
                onDismissRequestFood = { expandedFood = false },
                onDismissRequestBody = { expandedBody = false },
                onDismissRequestHead = { expandedHead = false },
                onDismissRequestPoison = { expandedPoison = false },
                onClickExpandedFood = { expandedFood = false },
                onClickExpandedBody = { expandedBody = false },
                onClickExpandedHead = { expandedHead = false },
                onClickExpandedPoison = { expandedPoison = false },
                onClickOptionExpandedFoodColor = {
                    selectedOptionFoodColor = pythonUiState.colors.values.elementAt(pythonUiState.colors.values.indexOf(it))
                    pythonUiState.foodColor = pythonUiState.colors.keys.elementAt(pythonUiState.colors.values.indexOf(selectedOptionFoodColor))
                },
                onClickOptionExpandedPoisonColor = {
                    selectedOptionPoisonColor = pythonUiState.colors.values.elementAt(pythonUiState.colors.values.indexOf(it))
                    pythonUiState.poisonColor = pythonUiState.colors.keys.elementAt(pythonUiState.colors.values.indexOf(selectedOptionPoisonColor))
                },
                onClickOptionExpandedHeadColor = {
                    selectedOptionHeadColor = pythonUiState.colors.values.elementAt(pythonUiState.colors.values.indexOf(it))
                    pythonUiState.headPythonColor = pythonUiState.colors.keys.elementAt(pythonUiState.colors.values.indexOf(selectedOptionHeadColor))
                },
                onClickOptionExpandedBodyColor = {
                    selectedOptionBodyColor = pythonUiState.colors.values.elementAt(pythonUiState.colors.values.indexOf(it))
                    pythonUiState.bodyPythonColor = pythonUiState.colors.keys.elementAt(pythonUiState.colors.values.indexOf(selectedOptionBodyColor))
                }
            )
        }
    }
}