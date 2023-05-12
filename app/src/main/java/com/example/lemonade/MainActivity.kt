package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeWithImageAndText(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )

}

@Composable
fun LemonadeWithImageAndText(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (currentStep) {
            1 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.lemon_tree_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 2
                                squeezeCount = (2..4).random()
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = stringResource(R.string.select))
                }
            }
            2 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){

                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.lemon_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable{
                                squeezeCount--
                                if (squeezeCount == 0) {
                                    currentStep = 3
                                    squeezeCount = (2..4).random()
                                }
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = stringResource(R.string.squeeze))
                }
            }
            3 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){

                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.lemonade_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable{
                                squeezeCount--
                                if (squeezeCount == 0) {
                                    currentStep = 4
                                }
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = stringResource(R.string.drink))
                }
            }
            4 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){

                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.empty_glass_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable{
                                currentStep = 1
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = stringResource(R.string.again))
                }
            }
        }
    }
}