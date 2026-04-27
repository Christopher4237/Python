package com.example.python2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.python2.ui.theme.Python2Theme

@Composable
fun PythonHome(
    onClickPlay: () -> Unit,
    onClickRulesSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Button(
            onClick = onClickPlay
        ) {
            Text(
                text = "Play"
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.15f)
        )
        Button(
            onClick = onClickRulesSettings
        ) {
            Text(
                text = "Rules and Settings"
            )
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PythonHomePreview() {
    Python2Theme {
        PythonHome(
            onClickPlay = {},
            onClickRulesSettings = {}
        )
    }
}