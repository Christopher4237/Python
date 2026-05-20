package com.example.python2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.python2.ui.theme.Python2Theme

enum class TextFieldChosen {
    Food,
    Poison,
    Head,
    Body
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PythonRulesSettings(
    options: Map<Color, String>,
    returnHome: () -> Unit,
    foodColor: Color?,
    poisonColor: Color?,
    bodyPythonColor: Color?,
    //selectedOption: String,
    selectedOptionFoodColor: String,
    selectedOptionBodyColor: String,
    selectedOptionPoisonColor: String,
    expandedFood: Boolean = false,
    expandedBody: Boolean = false,
    expandedPoison: Boolean = false,
    //expanded: Boolean = false,
    //onClick: () -> Unit,
    onClickChooseFood: () -> Unit,
    onClickChooseBody: () -> Unit,
    onClickChoosePoison: () -> Unit,
    onClickExpandedChangeFood: (Boolean) -> Unit,
    onClickExpandedChangeBody: (Boolean) -> Unit,
    onClickExpandedChangePoison: (Boolean) -> Unit,
    //onClickExpandedChange: (Boolean) -> Unit,
    onDismissRequestFood: () -> Unit,
    onDismissRequestBody: () -> Unit,
    onDismissRequestPoison: () -> Unit,
    //onDismissRequest: () -> Unit,
    onClickOptionExpandedFoodColor: (String) -> Unit,
    onClickOptionExpandedPoisonColor: (String) -> Unit,
    onClickOptionExpandedBodyColor: (String) -> Unit,
    //onClickOptionExpandedColor: (String) -> Unit,
    onClickExpandedFood: (Boolean) -> Unit,
    onClickExpandedPoison: (Boolean) -> Unit,
    onClickExpandedBody: (Boolean) -> Unit,
    //onClickExpanded: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            text = "Rules",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
        Spacer(
            modifier = Modifier
                .weight(0.02f)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "The aim is to have your python (denoted: ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
            Box(
                modifier = Modifier
                    .background(color = bodyPythonColor ?: Color.Transparent)
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .clip(shape = RectangleShape)
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = ")",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "to pick up food (denoted: ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
            Box(
                modifier = Modifier
                    .background(color = foodColor ?: Color.Transparent)
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .clip(shape = RectangleShape)
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = ")",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "in order to increase the body of your python, whilst avoiding poison",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "(denoted: ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
            Box(
                modifier = Modifier
                    .background(color = poisonColor ?: Color.Transparent)
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .clip(shape = RectangleShape)
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = ")",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = "Food Colour: ",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            DropDownTextField(
                options = options,
                /*
                onClick = {
                    selectedOption = option.toString()
                    expanded = false
                    idx = options.values.indexOf(selectedOption)
                },
                 */
                selectedOption = selectedOptionFoodColor,
                //expandedFood = expandedFood,
                //expandedBody = expandedBody,
                //expandedPoison = expandedPoison,
                //expandedHead = expandedHead,
                expanded = expandedFood,
                /*
                expanded = if(textFieldChosenValue == TextFieldChosen.Food) {
                    expandedFood
                } else {
                    false
                },
                 */
                onClick = onClickChooseFood,
                //onClickExpandedChange = onClickExpandedChange,
                //onDismissRequest = onDismissRequest,
                //onClickExpandedChangeFood = onClickExpandedChangeFood,
                //onClickExpandedChangeBody = onClickExpandedChangeBody,
                //onClickExpandedChangeHead = onClickExpandedChangeHead,
                //onClickExpandedChangePoison = onClickExpandedChangePoison,
                onClickExpandedChange = onClickExpandedChangeFood,
                //onDismissRequestFood = onDismissRequestFood,
                //onDismissRequestBody = onDismissRequestBody,
                //onDismissRequestHead = onDismissRequestHead,
                //onDismissRequestPoison = onDismissRequestPoison,
                onDismissRequest = onDismissRequestFood,
                //onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                //onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                //onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                //onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickOptionExpandedColor = onClickOptionExpandedFoodColor,
                //onClickExpandedFood = onClickExpandedFood,
                //onClickExpandedPoison = onClickExpandedPoison,
                //onClickExpandedHead = onClickExpandedHead,
                //onClickExpandedBody = onClickExpandedBody,
                onClickExpanded = onClickExpandedFood,
                textFieldChosen = TextFieldChosen.Food
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .background(color = foodColor ?: Color.Transparent)
                    .clip(shape = RectangleShape)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
            ) {}
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = "Poison Colour: ",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            DropDownTextField(
                options = options,
                selectedOption = selectedOptionPoisonColor,
                //expandedFood = expandedFood,
                //expandedBody = expandedBody,
                //expandedPoison = expandedPoison,
                //expandedHead = expandedHead,
                expanded = expandedPoison,
                onClick = onClickChoosePoison,
                //onClickExpandedChangeFood = onClickExpandedChangeFood,
                //onClickExpandedChangeBody = onClickExpandedChangeBody,
                //onClickExpandedChangeHead = onClickExpandedChangeHead,
                //onClickExpandedChangePoison = onClickExpandedChangePoison,
                onClickExpandedChange = onClickExpandedChangePoison,
                //onDismissRequestFood = onDismissRequestFood,
                //onDismissRequestBody = onDismissRequestBody,
                //onDismissRequestHead = onDismissRequestHead,
                //onDismissRequestPoison = onDismissRequestPoison,
                onDismissRequest = onDismissRequestPoison,
                //onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                //onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                //onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                //onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickOptionExpandedColor = onClickOptionExpandedPoisonColor,
                //onClickExpandedFood = onClickExpandedFood,
                //onClickExpandedPoison = onClickExpandedPoison,
                //onClickExpandedHead = onClickExpandedHead,
                //onClickExpandedBody = onClickExpandedBody,
                onClickExpanded = onClickExpandedPoison,
                textFieldChosen = TextFieldChosen.Poison
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .background(color = poisonColor ?: Color.Transparent)
                    .clip(shape = RectangleShape)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
            ) {}
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = "Body Python Colour: ",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            DropDownTextField(
                options = options,
                selectedOption = selectedOptionBodyColor,
                //expandedFood = expandedFood,
                //expandedBody = expandedBody,
                //expandedPoison = expandedPoison,
                //expandedHead = expandedHead,
                expanded = expandedBody,
                onClick = onClickChooseBody,
                //onClickExpandedChangeFood = onClickExpandedChangeFood,
                //onClickExpandedChangeBody = onClickExpandedChangeBody,
                //onClickExpandedChangeHead = onClickExpandedChangeHead,
                //onClickExpandedChangePoison = onClickExpandedChangePoison,
                onClickExpandedChange = onClickExpandedChangeBody,
                //onDismissRequestFood = onDismissRequestFood,
                //onDismissRequestBody = onDismissRequestBody,
                //onDismissRequestHead = onDismissRequestHead,
                //onDismissRequestPoison = onDismissRequestPoison,
                onDismissRequest = onDismissRequestBody,
                //onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                //onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                //onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                //onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickOptionExpandedColor = onClickOptionExpandedBodyColor,
                //onClickExpandedFood = onClickExpandedFood,
                //onClickExpandedPoison = onClickExpandedPoison,
                //nClickExpandedHead = onClickExpandedHead,
                //onClickExpandedBody = onClickExpandedBody,
                onClickExpanded = onClickExpandedBody,
                textFieldChosen = TextFieldChosen.Body
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .background(color = bodyPythonColor ?: Color.Transparent)
                    .clip(shape = RectangleShape)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
            ) {}
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.35f)
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
                .weight(0.2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PythonRulesSettingsTest() {
    Python2Theme {
        PythonRulesSettings(
            options = mapOf(),
            returnHome = {},
            foodColor = Color.Black,
            poisonColor = Color.Black,
            bodyPythonColor = Color.Black,
            selectedOptionBodyColor = "",
            selectedOptionPoisonColor = "",
            selectedOptionFoodColor = "",
            //expandedFood = false,
            //expandedBody = false,
            //expandedPoison = false,
            //expandedHead = false,
            //expanded = false,
            onClickChooseBody = {},
            onClickChooseFood = {},
            onClickChoosePoison = {},
            //onDismissRequest = {},
            //onClickExpandedChange = {},
            onClickExpandedChangeFood = {},
            onClickExpandedChangeBody = {},
            onClickExpandedChangePoison = {},
            //onClickExpandedChange = {},
            onDismissRequestFood = {},
            onDismissRequestBody = {},
            onDismissRequestPoison = {},
            //onDismissRequest = {},
            onClickExpandedFood = {},
            onClickExpandedPoison = {},
            onClickExpandedBody = {},
            //onClickExpanded = {},
            onClickOptionExpandedFoodColor = {},
            onClickOptionExpandedPoisonColor = {},
            onClickOptionExpandedBodyColor = {}
            //onClickOptionExpandedColor = {}
        )
    }
}