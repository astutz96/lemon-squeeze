package com.example.lemonsqueezy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonSqueezeApp() {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
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
            shape = RoundedCornerShape(5),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painterResource(id = imageResourceId), null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(textResourceId), style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonSqueezyTheme {
        LemonSqueezeApp()
    }
}