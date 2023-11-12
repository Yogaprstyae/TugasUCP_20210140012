@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.navigationlayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasucp.OrderViewModel
import com.example.tugasucp.R
import com.example.tugasucp.data.DataRadio.buah
import com.example.tugasucp.data.OrderUIState

@Composable
fun HalamanBuah(
    ViewModel: OrderViewModel = viewModel(),
    onNextButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    val dataForm: OrderUIState
    val uiState by ViewModel.stateUI.collectAsState()
    dataForm = uiState
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            SelectBuah(
                options = buah.map { id -> context.resources.getString(id) },
                onSelectionChanged = {ViewModel.setBuah(it)}
            )
            Row (modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        ViewModel.insertDataBuah(dataForm.fruit)
                    }
                ) {
                    Text(text = stringResource(R.string.submit),
                        fontSize = 16.sp
                    )
                }
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = onNextButtonClicked
                ) {
                    Text(text = stringResource(R.string.next),
                        fontSize = 16.sp
                    )
                }


            }
            TextHasil(
                buahnya = ViewModel.pilihBh
            )
        }
    }
}


@Composable
fun SelectBuah(
    options: List<String>,
    onSelectionChanged: (String) -> Unit={}
){
    Text(text = "Jenis Buah")
    var selectedValue by rememberSaveable{ mutableStateOf("") }
    Column(modifier = Modifier.padding(5.dp)) {
        options.forEach {item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun TextHasil(
    buahnya: String,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Halo!",
            modifier = Modifier
                .padding(
                    horizontal = 10.dp, vertical = 4.dp
                )
        )
        Text(
            text = "Buah yang kamu pilih adalah : ",
            modifier = Modifier
                .padding(
                    horizontal = 10.dp, vertical = 5.dp
                )
        )
        Text(
            text = buahnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

    }
}