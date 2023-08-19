package com.example.lemonsqueezy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonsqueezy.ui.theme.LemonSqueezyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonSqueezyTheme {
                // A surface container using the 'background' color from the theme
                LemonSqueezeApp()
            }
        }
    }
}

@Composable
fun LemonSqueezeApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        var status by remember { mutableStateOf(1) }
        var squeezeCount by remember { mutableStateOf(1) }
        when (status) {
            1 -> {
                SqueezeImageWithStatus(imageResourceId = R.drawable.lemon_tree,
                    textResourceId = R.string.tap_tree,
                    onImageClick = {
                        status++
                        squeezeCount = (2..4).random()
                    })
            }

            2 -> {
                SqueezeImageWithStatus(imageResourceId = R.drawable.lemon_squeeze,
                    textResourceId = R.string.tap_lemon,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            status = 3;
                        }
                    })
            }

            3 -> {
                SqueezeImageWithStatus(imageResourceId = R.drawable.lemon_drink,
                    textResourceId = R.string.tap_full_glass,
                    onImageClick = {
                        status++
                    })
            }

            4 -> {
                SqueezeImageWithStatus(imageResourceId = R.drawable.lemon_restart,
                    textResourceId = R.string.tap_empty_glass,
                    onImageClick = { status = 1 })
            }
        }
    }
}

@Composable
fun SqueezeImageWithStatus(imageResourceId: Int, textResourceId: Int, onImageClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(5)
        ) {
            Image(
                painterResource(id = imageResourceId), null
            )
        }
        Text(stringResource(textResourceId))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonSqueezyTheme {
        LemonSqueezeApp()
    }
}