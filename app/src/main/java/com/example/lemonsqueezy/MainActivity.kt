package com.example.lemonsqueezy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                LemonSqueezyApp()
            }
        }
    }
}

@Composable
fun LemonSqueezyApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colorScheme.background
    ) {
        SqueezeImageWithStatus()
    }
}

@Composable
fun SqueezeImageWithStatus() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            //When the button is clicked the value of result changes,
            //Result is being Remembered with a MutableStateOf().
            // Causing the value to be observed, and any changes will cause recomposition
            //of the composable DiceWithButtonAndImage
            onClick = {},
        ) {
            Image(painter = painterResource(id = R.drawable.lemon_tree), null)
        }

        Text(stringResource(R.string.tap_tree))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonSqueezyTheme {
        LemonSqueezyApp()
    }
}