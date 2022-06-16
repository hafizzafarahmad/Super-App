package com.summersoft.dictionary.features.dictionary.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@Composable
fun WordInfoScreen(
    navController: NavController,
    viewModel: WordInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is WordInfoViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colors.background)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn{
                    items(state.wordInfoItems.size){i ->
                        val wordInfo = state.wordInfoItems[i]
                        if(i > 0){
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        WordInfoItem(wordInfo = wordInfo)
                        if(i < state.wordInfoItems.size - 1){
                            Divider()
                        }
                    }
                }
            }
        }
    }
}