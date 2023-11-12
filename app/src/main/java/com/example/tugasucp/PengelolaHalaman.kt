@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tugasucp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationlayout.HalamanBuah

enum class PengelolaHalaman {
    Buah,
    Kota
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YogaAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        painterResource(R.drawable.baseline_arrow_back_24), contentDescription = stringResource(
                            R.string.back_button
                        )
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YogaApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            YogaAppBar(bisaNavigasiBack = false, navigasiUp = { /*TODO*/ })
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Buah.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PengelolaHalaman.Buah.name) {
                HalamanBuah(onNextButtonClicked = {
                    navController.navigate(PengelolaHalaman.Kota.name)
                }
                )
            }
            composable(route = PengelolaHalaman.Kota.name) {
                val context = LocalContext.current
                HalamanKota(onBackButtonClicked = {navController.popBackStack(PengelolaHalaman.Buah.name, false)})
            }
        }
    }
}
