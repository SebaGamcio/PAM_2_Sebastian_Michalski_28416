package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                TopAppBar()
                LemonImageAndText(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    LemonadeAppTheme {
        TopAppBar()
        LemonImageAndText( modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white)
                    )
            },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(R.color.teal_200)
                )
            )
        }
    ) {
        innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            ) {
        }
    }
}

@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(0) }
    var imageID by remember { mutableStateOf(R.drawable.lemon_tree) }
    var textID by remember { mutableStateOf(R.string.tap) }
    var onClick by remember { mutableStateOf({}) }
    when (step) {
        1 -> {
            imageID = R.drawable.lemon_tree
            textID = R.string.tap
            onClick = {
                step = 2
                squeeze = (2..4).random()
            }
        }
        2 -> {
            imageID = R.drawable.lemon_squeeze
            textID = R.string.keep_tapping
            onClick = {
                squeeze --
                if (squeeze == 0){
                    step = 3
                }
            }
        }
        3 -> {
            imageID = R.drawable.lemon_drink
            textID = R.string.drink
            onClick = {
                step = 4
            }
        }
        4 -> {
            imageID = R.drawable.lemon_restart
            textID = R.string.empty_glass
            onClick = {
                step = 1
            }
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick,
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
        ) {
            Image(painter = painterResource(imageID),
                contentDescription = stringResource(textID)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textID),
            fontSize = 18.sp
        )
    }
}

