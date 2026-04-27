package com.example.python2

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
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.python2.ui.theme.Python2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    options: Map<Color, String>,
    selectedOption: String,
    expanded: Boolean = false,
    //onClick: () -> Unit,
    onClickExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onClickOptionExpanded: (String) -> Unit,
    onClickExpanded: (Boolean) -> Unit
) {

    // State variables
    //var expanded by remember { mutableStateOf(false) }
    //var selectedOption by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        //onExpandedChange = { expanded = !expanded }
        onExpandedChange = onClickExpandedChange
    ) {
        BasicTextField(
            value = selectedOption,
            singleLine = true,
            onValueChange = {  },
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
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                label = { Text("Select an option", fontSize = 14.sp) }
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            //onDismissRequest = { expanded = false }
            onDismissRequest = onDismissRequest
        ) {
            options.values.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    /*
                    onClick = {
                        selectedOption = option.toString()
                        expanded = false
                    }
                     */
                    //onClick = onClick
                    onClick = {
                       onClickOptionExpanded(option)
                       onClickExpanded(expanded)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownTextFieldPreview() {
    Python2Theme {
        DropDownTextField(
            options = mapOf(),
            selectedOption = "",
            expanded = false,
            //onClick = {},
            onDismissRequest = {},
            onClickExpandedChange = {},
            onClickOptionExpanded = {},
            onClickExpanded = {},

        )
    }
}