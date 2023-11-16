package com.example.artspaceapp

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonsAndApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ButtonsAndApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    @DrawableRes art: Int,
    @StringRes title: Int,
    @StringRes artist: Int
) {
    var imageWidth by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxWidth()) {
        ElevatedCard(
            modifier = Modifier
                .shadow(elevation = 20.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            shape = RectangleShape
        ) {
            Image(
                painter = painterResource(art),
                contentDescription = "",
                Modifier
                    .padding(20.dp).onGloballyPositioned
                    { coordinates -> imageWidth = coordinates.size.width }
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Card(
            Modifier
                .padding(end = 25.dp, start = 25.dp)
                .align(Alignment.CenterHorizontally)
                .width(with(LocalDensity.current){imageWidth.toDp()}),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(id = title),
                Modifier
                    .align(Alignment.Start)
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp),
                fontSize = 20.sp,
            )
            Text(
                text = stringResource(id = artist),
                Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp, start = 5.dp, end = 5.dp),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun ButtonsAndApp(modifier: Modifier = Modifier) {
    var image by remember { mutableStateOf(R.drawable.poppy) }
    var title by remember { mutableStateOf(R.string.poppy_title) }
    var artist by remember { mutableStateOf(R.string.poppy_artist) }
    var currentStep by remember { mutableStateOf(1) }
    Column {
        ImageAndText(art = image, title = title, artist = artist)
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                when (currentStep) {
                    1 -> {
                        image = R.drawable.sailboat
                        title = R.string.sailboat_title
                        artist = R.string.sailboat_artist
                        currentStep = 4
                    }

                    2 -> {
                        image = R.drawable.poppy
                        title = R.string.poppy_title
                        artist = R.string.poppy_artist
                        currentStep = 1
                    }

                    3 -> {
                        image = R.drawable.flowers
                        title = R.string.flowers_title
                        artist = R.string.flowers_artist
                        currentStep = 2
                    }

                    else -> {
                        image = R.drawable.weeding
                        title = R.string.weeding_title
                        artist = R.string.weeding_artist
                        currentStep = 3
                    }
                }
            },
                Modifier.size(height = 30.dp, width = 125.dp)) {
                Text(text = stringResource(R.string.previous), fontSize = 10.sp)
            }
            Button(
                onClick = {
                    when (currentStep) {
                        1 -> {
                            image = R.drawable.flowers
                            title = R.string.flowers_title
                            artist = R.string.flowers_artist
                            currentStep = 2
                        }

                        2 -> {
                            image = R.drawable.weeding
                            title = R.string.weeding_title
                            artist = R.string.flowers_artist
                            currentStep = 3
                        }

                        3 -> {
                            image = R.drawable.sailboat
                            title = R.string.sailboat_title
                            artist = R.string.sailboat_artist
                            currentStep = 4
                        }

                        else -> {
                            image = R.drawable.poppy
                            title = R.string.poppy_title
                            artist = R.string.poppy_artist
                            currentStep = 1
                        }
                    }
                },
                Modifier.size(height = 30.dp, width = 125.dp)
            ) {
                Text(text = stringResource(R.string.next), fontSize = 10.sp)
            }
        }
    }

}
