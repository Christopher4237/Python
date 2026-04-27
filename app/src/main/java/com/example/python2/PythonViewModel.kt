package com.example.python2

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PythonViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(PythonUiState())

    val uiState: StateFlow<PythonUiState> = _uiState.asStateFlow()

    init {
        resetGame()
    }

    //KEYS ARE THE ITERATIVE LIST, VALUES ARE THE POSITIONS
    fun resetGame() {
        val fullRange = (0 until 50*50).toList()
        val excludeNumbers = _uiState.value.currentPythonPieces.values
        val filteredList = fullRange.filterNot { it in excludeNumbers }
        val foodStartValues = filteredList.shuffled().take(5).toMutableStateList()
        val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
            .filterNot { it in foodStartValues }
        val barrierStartValues = filteredListBarriers.shuffled().take(10).toMutableStateList()

        _uiState.value = PythonUiState(
            currentPythonPieces = mutableMapOf(0 to 4, 1 to 3, 2 to 2, 3 to 1, 4 to 0),
            //currentPythonPieces = mutableMapOf(0 to 6, 1 to 5, 2 to 4, 3 to 3, 4 to 2, 5 to 1, 6 to 0),
            food = foodStartValues,
            barriers = barrierStartValues
        )
    }


    suspend fun movePieces() {
        while(!_uiState.value.isGameOver) {
            delay(_uiState.value.speed)
            var listToIterate = (1 until _uiState.value.currentPythonPieces.size).toList()
            var listToIterateAdding = (1 .. _uiState.value.currentPythonPieces.size).toList()
            var newPythonPieces: MutableMap<Int, Int> = mutableMapOf()
            var mostRecent = _uiState.value.currentPythonPieces[0] ?: 0
            var secondMostRecent = _uiState.value.currentPythonPieces[1] ?: 0
            var difference: Int?
            if(_uiState.value.direction == Direction.Down) {
                if((mostRecent + 50 in _uiState.value.barriers) ||
                    (mostRecent / 50 == 49) ||
                    (mostRecent + 50 in _uiState.value.currentPythonPieces.values)) {
                    _uiState.update {
                        it.copy(
                            isGameOver = true
                        )
                    }
                } else if(mostRecent + 50 !in _uiState.value.food) {
                    difference = mostRecent - secondMostRecent
                    newPythonPieces.put(0, mostRecent + 50)
                    for (item in listToIterate) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces
                        )
                    }
                } else {
                    newPythonPieces.put(0, mostRecent + 50)
                    for (item in listToIterateAdding) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    val fullRange = (0 until 50*50).toList()


                    val excludeNumbers = newPythonPieces
                    val filteredList = fullRange.filterNot { it in excludeNumbers }
                    val foodNewValues = filteredList.random()
                    var excludedFood = _uiState.value.food.filterNot { it == (mostRecent + 50) }.toMutableStateList()
                    var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


                    val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                        .filterNot { it in newFood }
                    val barrierNewValues = filteredListBarriers.random()
                    var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent + 50) }.toMutableStateList()
                    var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces,
                            food = newFood,
                            score = _uiState.value.score.plus(1000),
                            barriers = newBarriers
                        )
                    }

                    if(_uiState.value.score % 5000 == 0) {
                        _uiState.update {
                            it.copy(
                                speed = _uiState.value.speed.minus(5L)
                            )
                        }
                    }
                }
            } else if(_uiState.value.direction == Direction.Right) {
                if((mostRecent + 1 in _uiState.value.barriers) ||
                    (mostRecent % 50 == 49 && secondMostRecent % 50 == 48) ||
                    (mostRecent + 1 in _uiState.value.currentPythonPieces.values)) {
                    _uiState.update {
                        it.copy(
                            isGameOver = true
                        )
                    }
                } else if(mostRecent + 1 !in _uiState.value.food) {
                    difference = mostRecent - secondMostRecent
                    newPythonPieces.put(0, mostRecent + 1)
                    for (item in listToIterate) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces
                        )
                    }
                } else {
                    newPythonPieces.put(0, mostRecent + 1)
                    for (item in listToIterateAdding) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    val fullRange = (0 until 50*50).toList()


                    val excludeNumbers = newPythonPieces
                    val filteredList = fullRange.filterNot { it in excludeNumbers }
                    val foodNewValues = filteredList.random()
                    var excludedFood = _uiState.value.food.filterNot { it == (mostRecent + 1) }.toMutableStateList()
                    var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


                    val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                        .filterNot { it in newFood }
                    val barrierNewValues = filteredListBarriers.random()
                    var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent + 1) }.toMutableStateList()
                    var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces,
                            food = newFood,
                            score = _uiState.value.score.plus(1000),
                            barriers = newBarriers
                        )
                    }

                    if(_uiState.value.score % 5000 == 0) {
                        _uiState.update {
                            it.copy(
                                speed = _uiState.value.speed.minus(5L)
                            )
                        }
                    }
                }
            } else if(_uiState.value.direction == Direction.Left) {
                if((mostRecent - 1 in _uiState.value.barriers) ||
                    (mostRecent % 50 == 0 && secondMostRecent % 50 == 1) ||
                    (mostRecent - 1 in _uiState.value.currentPythonPieces.values)) {
                    _uiState.update {
                        it.copy(
                            isGameOver = true
                        )
                    }
                } else if(mostRecent - 1 !in _uiState.value.food) {
                    difference = mostRecent - secondMostRecent
                    newPythonPieces.put(0, mostRecent - 1)
                    for (item in listToIterate) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces
                        )
                    }
                } else {
                    newPythonPieces.put(0, mostRecent - 1)
                    for (item in listToIterateAdding) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    val fullRange = (0 until 50*50).toList()
                    val excludeNumbers = newPythonPieces
                    val filteredList = fullRange.filterNot { it in excludeNumbers }
                    val foodNewValues = filteredList.random()
                    var excludedFood = _uiState.value.food.filterNot { it == (mostRecent - 1) }.toMutableStateList()
                    var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


                    val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                        .filterNot { it in newFood }
                    val barrierNewValues = filteredListBarriers.random()
                    var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent - 1) }.toMutableStateList()
                    var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces,
                            food = newFood,
                            score = _uiState.value.score.plus(1000),
                            barriers = newBarriers
                        )
                    }

                    if(_uiState.value.score % 5000 == 0) {
                        _uiState.update {
                            it.copy(
                                speed = _uiState.value.speed.minus(5L)
                            )
                        }
                    }
                }
            } else {
                if((mostRecent - 50 in _uiState.value.barriers) ||
                    (mostRecent / 50 == 0) ||
                    (mostRecent - 50 in _uiState.value.currentPythonPieces.values)) {
                    _uiState.update {
                        it.copy(
                            isGameOver = true
                        )
                    }
                } else if(mostRecent - 50 !in _uiState.value.food) {
                    difference = mostRecent - secondMostRecent
                    newPythonPieces.put(0, mostRecent - 50)
                    for (item in listToIterate) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces
                        )
                    }
                } else {
                    newPythonPieces.put(0, mostRecent - 50)
                    for (item in listToIterateAdding) {
                        newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
                    }
                    val fullRange = (0 until 50*50).toList()
                    val excludeNumbers = newPythonPieces
                    val filteredList = fullRange.filterNot { it in excludeNumbers }
                    val foodNewValues = filteredList.random()
                    var excludedFood = _uiState.value.food.filterNot { it == (mostRecent - 50) }.toMutableStateList()
                    var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


                    val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                        .filterNot { it in newFood }
                    val barrierNewValues = filteredListBarriers.random()
                    var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent - 50) }.toMutableStateList()
                    var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


                    _uiState.update {
                        it.copy(
                            currentPythonPieces = newPythonPieces,
                            food = newFood,
                            score = _uiState.value.score.plus(1000),
                            barriers = newBarriers
                        )
                    }

                    if(_uiState.value.score % 5000 == 0) {
                        _uiState.update {
                            it.copy(
                                speed = _uiState.value.speed.minus(5L)
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateDirectionDown() {
        _uiState.update {
            it.copy(
                direction = Direction.Down,
                directionsEnabled = listOf(Direction.Right, Direction.Left, Direction.Down)
            )
        }
    }

    fun updateDirectionLeft() {
        _uiState.update {
            it.copy(
                direction = Direction.Left,
                directionsEnabled = listOf(Direction.Up, Direction.Down, Direction.Left)
            )
        }
    }

    fun updateDirectionRight() {
        _uiState.update {
            it.copy(
                direction = Direction.Right,
                directionsEnabled = listOf(Direction.Up, Direction.Down, Direction.Right)
            )
        }
    }

    fun updateDirectionUp() {
        _uiState.update {
            it.copy(
                direction = Direction.Up,
                directionsEnabled = listOf(Direction.Right, Direction.Left, Direction.Up)
            )
        }
    }

    fun accelerateRight() {
        var mostRecent = _uiState.value.currentPythonPieces[0] ?: 0
        var newPythonPieces: MutableMap<Int, Int> = mutableMapOf()
        var listToIterate = (1 until _uiState.value.currentPythonPieces.size).toList()
        var listToIterateAdding = (1 .. _uiState.value.currentPythonPieces.size).toList()

        if((mostRecent + 1 in _uiState.value.barriers) ||
            (mostRecent / 50 == 49) ||
            (mostRecent + 1 in _uiState.value.currentPythonPieces.values)) {
            _uiState.update {
                it.copy(
                    isGameOver = true
                )
            }
        } else if(mostRecent + 1 !in _uiState.value.food) {
            newPythonPieces.put(0, mostRecent + 1)
            for (item in listToIterate) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces
                )
            }
        } else {
            newPythonPieces.put(0, mostRecent + 1)
            for (item in listToIterateAdding) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            val fullRange = (0 until 50*50).toList()


            val excludeNumbers = newPythonPieces
            val filteredList = fullRange.filterNot { it in excludeNumbers }
            val foodNewValues = filteredList.random()
            var excludedFood = _uiState.value.food.filterNot { it == (mostRecent + 1) }.toMutableStateList()
            var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


            val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                .filterNot { it in newFood }
            val barrierNewValues = filteredListBarriers.random()
            var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent + 1) }.toMutableStateList()
            var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces,
                    food = newFood,
                    score = _uiState.value.score.plus(1000),
                    barriers = newBarriers
                )
            }

            if(_uiState.value.score % 5000 == 0) {
                _uiState.update {
                    it.copy(
                        speed = _uiState.value.speed.minus(5L)
                    )
                }
            }
        }
    }

    fun accelerateLeft() {
        var mostRecent = _uiState.value.currentPythonPieces[0] ?: 0
        var newPythonPieces: MutableMap<Int, Int> = mutableMapOf()
        var listToIterate = (1 until _uiState.value.currentPythonPieces.size).toList()
        var listToIterateAdding = (1 .. _uiState.value.currentPythonPieces.size).toList()

        if((mostRecent - 1 in _uiState.value.barriers) ||
            (mostRecent / 50 == 49) ||
            (mostRecent - 1 in _uiState.value.currentPythonPieces.values)) {
            _uiState.update {
                it.copy(
                    isGameOver = true
                )
            }
        } else if(mostRecent - 1 !in _uiState.value.food) {
            newPythonPieces.put(0, mostRecent - 1)
            for (item in listToIterate) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces
                )
            }
        } else {
            newPythonPieces.put(0, mostRecent - 1)
            for (item in listToIterateAdding) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            val fullRange = (0 until 50*50).toList()


            val excludeNumbers = newPythonPieces
            val filteredList = fullRange.filterNot { it in excludeNumbers }
            val foodNewValues = filteredList.random()
            var excludedFood = _uiState.value.food.filterNot { it == (mostRecent - 1) }.toMutableStateList()
            var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


            val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                .filterNot { it in newFood }
            val barrierNewValues = filteredListBarriers.random()
            var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent - 1) }.toMutableStateList()
            var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces,
                    food = newFood,
                    score = _uiState.value.score.plus(1000),
                    barriers = newBarriers
                )
            }

            if(_uiState.value.score % 5000 == 0) {
                _uiState.update {
                    it.copy(
                        speed = _uiState.value.speed.minus(5L)
                    )
                }
            }
        }
    }

    fun accelerateUp() {
        var mostRecent = _uiState.value.currentPythonPieces[0] ?: 0
        var newPythonPieces: MutableMap<Int, Int> = mutableMapOf()
        var listToIterate = (1 until _uiState.value.currentPythonPieces.size).toList()
        var listToIterateAdding = (1 .. _uiState.value.currentPythonPieces.size).toList()

        if((mostRecent - 50 in _uiState.value.barriers) ||
            (mostRecent / 50 == 49) ||
            (mostRecent - 50 in _uiState.value.currentPythonPieces.values)) {
            _uiState.update {
                it.copy(
                    isGameOver = true
                )
            }
        } else if(mostRecent - 50 !in _uiState.value.food) {
            newPythonPieces.put(0, mostRecent - 50)
            for (item in listToIterate) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces
                )
            }
        } else {
            newPythonPieces.put(0, mostRecent - 50)
            for (item in listToIterateAdding) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            val fullRange = (0 until 50*50).toList()


            val excludeNumbers = newPythonPieces
            val filteredList = fullRange.filterNot { it in excludeNumbers }
            val foodNewValues = filteredList.random()
            var excludedFood = _uiState.value.food.filterNot { it == (mostRecent - 50) }.toMutableStateList()
            var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


            val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                .filterNot { it in newFood }
            val barrierNewValues = filteredListBarriers.random()
            var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent - 50) }.toMutableStateList()
            var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces,
                    food = newFood,
                    score = _uiState.value.score.plus(1000),
                    barriers = newBarriers
                )
            }

            if(_uiState.value.score % 5000 == 0) {
                _uiState.update {
                    it.copy(
                        speed = _uiState.value.speed.minus(5L)
                    )
                }
            }
        }
    }

    fun accelerateDown() {
        var mostRecent = _uiState.value.currentPythonPieces[0] ?: 0
        var newPythonPieces: MutableMap<Int, Int> = mutableMapOf()
        var listToIterate = (1 until _uiState.value.currentPythonPieces.size).toList()
        var listToIterateAdding = (1 .. _uiState.value.currentPythonPieces.size).toList()

        if((mostRecent + 50 in _uiState.value.barriers) ||
            (mostRecent / 50 == 49) ||
            (mostRecent + 50 in _uiState.value.currentPythonPieces.values)) {
            _uiState.update {
                it.copy(
                    isGameOver = true
                )
            }
        } else if(mostRecent + 50 !in _uiState.value.food) {
            newPythonPieces.put(0, mostRecent + 50)
            for (item in listToIterate) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces
                )
            }
        } else {
            newPythonPieces.put(0, mostRecent + 50)
            for (item in listToIterateAdding) {
                newPythonPieces.put(item, _uiState.value.currentPythonPieces[item - 1] ?: 0)
            }
            val fullRange = (0 until 50*50).toList()


            val excludeNumbers = newPythonPieces
            val filteredList = fullRange.filterNot { it in excludeNumbers }
            val foodNewValues = filteredList.random()
            var excludedFood = _uiState.value.food.filterNot { it == (mostRecent + 50) }.toMutableStateList()
            var newFood = excludedFood.plus(foodNewValues).toMutableStateList()


            val filteredListBarriers = fullRange.filterNot { it in excludeNumbers }
                .filterNot { it in newFood }
            val barrierNewValues = filteredListBarriers.random()
            var excludedBarriers = _uiState.value.barriers.filterNot { it == (mostRecent + 50) }.toMutableStateList()
            var newBarriers = excludedBarriers.plus(barrierNewValues).toMutableStateList()


            _uiState.update {
                it.copy(
                    currentPythonPieces = newPythonPieces,
                    food = newFood,
                    score = _uiState.value.score.plus(1000),
                    barriers = newBarriers
                )
            }

            if(_uiState.value.score % 5000 == 0) {
                _uiState.update {
                    it.copy(
                        speed = _uiState.value.speed.minus(5L)
                    )
                }
            }
        }
    }

}