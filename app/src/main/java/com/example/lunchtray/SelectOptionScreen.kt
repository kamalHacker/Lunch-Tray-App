package com.example.lunchtray

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.data.DataSource.entreeMenuItems
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.ui.theme.LunchTrayTheme

@Composable
fun SelectOptionScreen(
    modifier: Modifier = Modifier,
    options: List<MenuItem>,
    onSelectionChanged: (MenuItem) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
) {
    var selectedItemName by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {

        Column(modifier = modifier.padding(8.dp)) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = (selectedItemName == item.name),
                        onClick = {
                            selectedItemName = item.name
                            onSelectionChanged(item)
                        }
                    )
                ) {
                    RadioButton(
                        selected = (selectedItemName == item.name),
                        onClick = {
                            selectedItemName = item.name
                            onSelectionChanged(item)
                        }
                    )
                    Column {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                        )
                        Text(
                            text = item.description,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                        )
                        Text(
                            text = item.getFormattedPrice(),
                            fontSize = 14.sp,
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel).uppercase())
            }
            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedItemName.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next).uppercase())
            }
        }

    }

}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun SelectOptionScreenPreview(){
    LunchTrayTheme{
        SelectOptionScreen(
            options = entreeMenuItems
        )
    }
}