package com.asp.fatass.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asp.fatass.android._core.presentation.Routes
import com.asp.fatass.android.categories.presentation.AndroidCategoriesViewModel
//import com.asp.fatass.android.categories.presentation.AndroidCategoriesViewModel
import com.asp.fatass.android.categories.presentation.CategoriesScreen
import com.asp.fatass.android.products.presentation.AndroidProductsViewModel
//import com.asp.fatass.android.products.presentation.AndroidProductsViewModel
import com.asp.fatass.android.products.presentation.ProductsScreen
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        com.asp.fatass.android._core.theme.darkColors
    } else {
        com.asp.fatass.android._core.theme.lightColors
    }
    val SfProText = FontFamily(
        Font(
            resId = R.font.sf_pro_text_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.sf_pro_text_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.sf_pro_text_bold,
            weight = FontWeight.Bold
        ),
    )
    val typography = Typography(
        h1 = TextStyle(
            fontFamily = SfProText,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        h2 = TextStyle(
            fontFamily = SfProText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        h3 = TextStyle(
            fontFamily = SfProText,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        body1 = TextStyle(
            fontFamily = SfProText,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        body2 = TextStyle(
            fontFamily = SfProText,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ContentView()
                }
            }
        }
    }
}

@Composable
fun ContentView() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.CATEGORIES
    ) {
        composable(route = Routes.CATEGORIES) {
            val viewModel = hiltViewModel<AndroidCategoriesViewModel>()
            val state by viewModel.state.collectAsState()

            CategoriesScreen(
                state = state,
                onEvent = { viewModel.onEvent(it) }
            )
        }

        composable(
            route = Routes.PRODUCTS + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    defaultValue = "none"
                }
            )
        ) { backStackEntry ->
            val languageCode = backStackEntry.arguments?.getString("category") ?: "none"
            val viewModel = hiltViewModel<AndroidProductsViewModel>()
            val state by viewModel.state.collectAsState()

            ProductsScreen(
                state = state,
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}
