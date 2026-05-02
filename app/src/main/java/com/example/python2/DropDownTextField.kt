package com.example.python2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.python2.ui.theme.Python2Theme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    options: Map<Color, String>,
    selectedOption: String,
    expandedFood: Boolean = false,
    expandedBody: Boolean = false,
    expandedPoison: Boolean = false,
    expandedHead: Boolean = false,
    //onClick: () -> Unit,
    onClickExpandedChangeHead: (Boolean) -> Unit,
    onClickExpandedChangeFood: (Boolean) -> Unit,
    onClickExpandedChangePoison: (Boolean) -> Unit,
    onClickExpandedChangeBody: (Boolean) -> Unit,
    onDismissRequestHead: () -> Unit,
    onDismissRequestFood: () -> Unit,
    onDismissRequestPoison: () -> Unit,
    onDismissRequestBody: () -> Unit,
    onClickOptionExpandedFoodColor: (String) -> Unit,
    onClickOptionExpandedPoisonColor: (String) -> Unit,
    onClickOptionExpandedHeadColor: (String) -> Unit,
    onClickOptionExpandedBodyColor: (String) -> Unit,
    onClickExpandedFood: (Boolean) -> Unit,
    onClickExpandedPoison: (Boolean) -> Unit,
    onClickExpandedHead: (Boolean) -> Unit,
    onClickExpandedBody: (Boolean) -> Unit,
    textFieldChosen: TextFieldChosen
) {

    // State variables
    //var expanded by remember { mutableStateOf(false) }
    //var selectedOption by remember { mutableStateOf("") }

    //var textFieldChosenValue: TextFieldChosen = TextFieldChosen.Food
    var textFieldChosenValue by remember { mutableStateOf(TextFieldChosen.Food) }


    ExposedDropdownMenuBox(
        expanded = when (textFieldChosenValue) {
            TextFieldChosen.Food -> expandedFood
            TextFieldChosen.Head -> expandedHead
            TextFieldChosen.Body -> expandedBody
            TextFieldChosen.Poison -> expandedPoison
        },
        //expanded = expanded,
        //onExpandedChange = { expanded = !expanded }
        onExpandedChange = when (textFieldChosenValue) {
            TextFieldChosen.Food -> onClickExpandedChangeFood
            TextFieldChosen.Head -> onClickExpandedChangeHead
            TextFieldChosen.Body -> onClickExpandedChangeBody
            TextFieldChosen.Poison -> onClickExpandedChangePoison
        }
    ) {
        BasicTextField(
            value = selectedOption,
            singleLine = true,
            onValueChange = { },
            textStyle = TextStyle.Default,
            enabled = true,
            readOnly = false,
            cursorBrush = SolidColor(Color.Black),
            modifier = Modifier
                .menuAnchor() // Required for proper positioning
                .wrapContentHeight()
        ) { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = selectedOption.toString(),
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon =
                    if (textFieldChosenValue == TextFieldChosen.Food) {
                        {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFood)
                        }
                    } else if (textFieldChosenValue == TextFieldChosen.Head) {
                        {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedHead)
                        }
                    } else if (textFieldChosenValue == TextFieldChosen.Body) {
                        {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBody)
                        }
                    } else if (textFieldChosenValue == TextFieldChosen.Poison) {
                        {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPoison)
                        }
                    } else {
                        {}
                    },
                label = {
                    Text(
                        "Select an option",
                        //textFieldChosenValue.toString(),
                        //textFieldChosen.toString(),
                        fontSize = 14.sp
                    )
                }
            )
        }
        ExposedDropdownMenu(
            expanded = when (textFieldChosenValue) {
                TextFieldChosen.Food -> expandedFood
                TextFieldChosen.Head -> expandedHead
                TextFieldChosen.Body -> expandedBody
                TextFieldChosen.Poison -> expandedPoison
            },
            //onDismissRequest = { expanded = false }
            //onDismissRequest = onDismissRequest
            onDismissRequest = when (textFieldChosenValue) {
                TextFieldChosen.Food -> onDismissRequestFood
                TextFieldChosen.Head -> onDismissRequestHead
                TextFieldChosen.Body -> onDismissRequestBody
                TextFieldChosen.Poison -> onDismissRequestPoison
            },
        ) {
            if (textFieldChosenValue == TextFieldChosen.Food) {
                options.values.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onClickOptionExpandedFoodColor(option)
                            onClickExpandedFood(expandedFood)
                        }
                    )
                }
            } else if (textFieldChosenValue == TextFieldChosen.Head) {
                options.values.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onClickOptionExpandedHeadColor(option)
                            onClickExpandedHead(expandedHead)
                        }
                    )
                }
            } else if (textFieldChosenValue == TextFieldChosen.Body) {
                options.values.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onClickOptionExpandedBodyColor(option)
                            onClickExpandedBody(expandedBody)
                        }
                    )
                }
            } else if (textFieldChosenValue == TextFieldChosen.Poison) {
                options.values.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onClickOptionExpandedPoisonColor(option)
                            onClickExpandedPoison(expandedPoison)
                        }
                    )
                }
            }
        }
    }
    Button(onClick = { textFieldChosenValue = textFieldChosen }) {
        Text(text = "")
    }

}


@Preview(showBackground = true)
@Composable
fun DropDownTextFieldPreview() {
    Python2Theme {
        DropDownTextField(
            options = mapOf(),
            selectedOption = "",
            expandedFood = false,
            expandedBody = false,
            expandedPoison = false,
            expandedHead = false,
            //onClick = {},
            onDismissRequestFood = {},
            onDismissRequestBody = {},
            onDismissRequestPoison = {},
            onDismissRequestHead = {},
            onClickExpandedChangeFood = {},
            onClickExpandedChangeBody = {},
            onClickExpandedChangePoison = {},
            onClickExpandedChangeHead = {},
            onClickOptionExpandedFoodColor = {},
            onClickExpandedHead = {},
            onClickExpandedBody = {},
            onClickExpandedPoison = {},
            onClickExpandedFood = {},
            onClickOptionExpandedPoisonColor = {},
            onClickOptionExpandedHeadColor = {},
            onClickOptionExpandedBodyColor = {},
            textFieldChosen = TextFieldChosen.Food
        )
    }
}