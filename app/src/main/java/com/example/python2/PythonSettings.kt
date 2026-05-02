package com.example.python2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    headPythonColor: Color?,
    bodyPythonColor: Color?,

    //selectedOption: String,
    selectedOptionFoodColor: String,
    selectedOptionBodyColor: String,
    selectedOptionPoisonColor: String,
    selectedOptionHeadColor: String,
    expandedFood: Boolean = false,
    expandedBody: Boolean = false,
    expandedHead: Boolean = false,
    expandedPoison: Boolean = false,
    //onClick: () -> Unit,
    onClickExpandedChangeFood: (Boolean) -> Unit,
    onClickExpandedChangeBody: (Boolean) -> Unit,
    onClickExpandedChangeHead: (Boolean) -> Unit,
    onClickExpandedChangePoison: (Boolean) -> Unit,
    onDismissRequestFood: () -> Unit,
    onDismissRequestBody: () -> Unit,
    onDismissRequestHead: () -> Unit,
    onDismissRequestPoison: () -> Unit,
    onClickOptionExpandedFoodColor: (String) -> Unit,
    onClickOptionExpandedPoisonColor: (String) -> Unit,
    onClickOptionExpandedHeadColor: (String) -> Unit,
    onClickOptionExpandedBodyColor: (String) -> Unit,
    onClickExpandedFood: (Boolean) -> Unit,
    onClickExpandedPoison: (Boolean) -> Unit,
    onClickExpandedHead: (Boolean) -> Unit,
    onClickExpandedBody: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    var textFieldChosenValue by remember { mutableStateOf(TextFieldChosen.Food) }

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
        Text(
            text = "The aim is to have the head of your python (denoted: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        Text(
            text = ") to pick up food (denoted: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        Text(
            text = "in order to increase the body of your python, (denoted: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        Text(
            text = "whilst avoiding poison (denoted: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
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
                expandedFood = expandedFood,
                expandedBody = expandedBody,
                expandedPoison = expandedPoison,
                expandedHead = expandedHead,
                //onClick = onClick,
                //onClickExpandedChange = onClickExpandedChange,
                //onDismissRequest = onDismissRequest,
                onClickExpandedChangeFood = onClickExpandedChangeFood,
                onClickExpandedChangeBody = onClickExpandedChangeBody,
                onClickExpandedChangeHead = onClickExpandedChangeHead,
                onClickExpandedChangePoison = onClickExpandedChangePoison,
                onDismissRequestFood = onDismissRequestFood,
                onDismissRequestBody = onDismissRequestBody,
                onDismissRequestHead = onDismissRequestHead,
                onDismissRequestPoison = onDismissRequestPoison,
                onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickExpandedFood = onClickExpandedFood,
                onClickExpandedPoison = onClickExpandedPoison,
                onClickExpandedHead = onClickExpandedHead,
                onClickExpandedBody = onClickExpandedBody,
                textFieldChosen = TextFieldChosen.Food
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = foodColor ?: Color.Transparent
                    )
                    .background(color = foodColor ?: Color.Transparent)
                    .clip(shape = CircleShape)
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
                expandedFood = expandedFood,
                expandedBody = expandedBody,
                expandedPoison = expandedPoison,
                expandedHead = expandedHead,
                //onClick = onClick,
                onClickExpandedChangeFood = onClickExpandedChangeFood,
                onClickExpandedChangeBody = onClickExpandedChangeBody,
                onClickExpandedChangeHead = onClickExpandedChangeHead,
                onClickExpandedChangePoison = onClickExpandedChangePoison,
                onDismissRequestFood = onDismissRequestFood,
                onDismissRequestBody = onDismissRequestBody,
                onDismissRequestHead = onDismissRequestHead,
                onDismissRequestPoison = onDismissRequestPoison,
                onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickExpandedFood = onClickExpandedFood,
                onClickExpandedPoison = onClickExpandedPoison,
                onClickExpandedHead = onClickExpandedHead,
                onClickExpandedBody = onClickExpandedBody,
                textFieldChosen = TextFieldChosen.Poison
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = poisonColor ?: Color.Transparent
                    )
                    .background(color = poisonColor ?: Color.Transparent)
                    .clip(shape = CircleShape)
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
                text = "Head Python Colour: ",
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
                selectedOption = selectedOptionHeadColor,
                expandedFood = expandedFood,
                expandedBody = expandedBody,
                expandedPoison = expandedPoison,
                expandedHead = expandedHead,
                //onClick = onClick,
                onClickExpandedChangeFood = onClickExpandedChangeFood,
                onClickExpandedChangeBody = onClickExpandedChangeBody,
                onClickExpandedChangeHead = onClickExpandedChangeHead,
                onClickExpandedChangePoison = onClickExpandedChangePoison,
                onDismissRequestFood = onDismissRequestFood,
                onDismissRequestBody = onDismissRequestBody,
                onDismissRequestHead = onDismissRequestHead,
                onDismissRequestPoison = onDismissRequestPoison,
                onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickExpandedFood = onClickExpandedFood,
                onClickExpandedPoison = onClickExpandedPoison,
                onClickExpandedHead = onClickExpandedHead,
                onClickExpandedBody = onClickExpandedBody,
                textFieldChosen = TextFieldChosen.Head
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = headPythonColor ?: Color.Transparent
                    )
                    .background(color = headPythonColor ?: Color.Transparent)
                    .clip(shape = CircleShape)
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
                expandedFood = expandedFood,
                expandedBody = expandedBody,
                expandedPoison = expandedPoison,
                expandedHead = expandedHead,
                //onClick = onClick,
                onClickExpandedChangeFood = onClickExpandedChangeFood,
                onClickExpandedChangeBody = onClickExpandedChangeBody,
                onClickExpandedChangeHead = onClickExpandedChangeHead,
                onClickExpandedChangePoison = onClickExpandedChangePoison,
                onDismissRequestFood = onDismissRequestFood,
                onDismissRequestBody = onDismissRequestBody,
                onDismissRequestHead = onDismissRequestHead,
                onDismissRequestPoison = onDismissRequestPoison,
                onClickOptionExpandedFoodColor = onClickOptionExpandedFoodColor,
                onClickOptionExpandedPoisonColor = onClickOptionExpandedPoisonColor,
                onClickOptionExpandedHeadColor = onClickOptionExpandedHeadColor,
                onClickOptionExpandedBodyColor = onClickOptionExpandedBodyColor,
                onClickExpandedFood = onClickExpandedFood,
                onClickExpandedPoison = onClickExpandedPoison,
                onClickExpandedHead = onClickExpandedHead,
                onClickExpandedBody = onClickExpandedBody,
                textFieldChosen = TextFieldChosen.Body
            )
            Spacer(
                modifier = Modifier
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = bodyPythonColor ?: Color.Transparent
                    )
                    .background(color = bodyPythonColor ?: Color.Transparent)
                    .clip(shape = CircleShape)
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
                .weight(0.45f)
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
                .weight(0.1f)
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
            headPythonColor = Color.Black,
            bodyPythonColor = Color.Black,
            selectedOptionBodyColor = "",
            selectedOptionHeadColor = "",
            selectedOptionPoisonColor = "",
            selectedOptionFoodColor = "",
            expandedFood = false,
            expandedBody = false,
            expandedPoison = false,
            expandedHead = false,
            //onClick = {},
            //onDismissRequest = {},
            //onClickExpandedChange = {},
            onClickExpandedChangeFood = {},
            onClickExpandedChangeBody = {},
            onClickExpandedChangeHead = {},
            onClickExpandedChangePoison = {},
            onDismissRequestFood = {},
            onDismissRequestBody = {},
            onDismissRequestHead = {},
            onDismissRequestPoison = {},
            onClickExpandedFood = {},
            onClickExpandedPoison = {},
            onClickExpandedHead = {},
            onClickExpandedBody = {},
            onClickOptionExpandedFoodColor = {},
            onClickOptionExpandedPoisonColor = {},
            onClickOptionExpandedHeadColor = {},
            onClickOptionExpandedBodyColor = {}
        )
    }
}