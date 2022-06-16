
package com.summersoft.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.summersoft.dictionary.core.theme.DictionaryTheme
import com.summersoft.dictionary.features.auth.presentation.AuthViewModel
import com.summersoft.dictionary.features.dictionary.presentation.WordInfoItem
import com.summersoft.dictionary.features.dictionary.presentation.WordInfoViewModel
import com.summersoft.dictionary.features.dictionary.util.DictionaryScreenRep
import com.summersoft.dictionary.features.dictionary.util.dictionaryGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                authViewModel.isAuthenticated.value
            }
        }

        setContent {
            DictionaryTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = DictionaryScreenRep.DictionaryInitScreen.route){
                    dictionaryGraph(navController)
                }
            }
        }

    }
}