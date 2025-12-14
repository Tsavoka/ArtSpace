package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}


@Composable
fun ArtSpaceLayout() {
    var screen by remember { mutableStateOf(1) }



    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 12.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier.size(120.dp, 40.dp),
                    onClick = { if (screen in (2..3)) screen-- else screen = 3 })
                { Text("Previous") }
                Button(
                    modifier = Modifier.size(120.dp, 40.dp),
                    onClick = { if (screen in (1..2)) screen++ else screen = 1 })
                { Text("Next") }
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(top = 64.dp)

        ) {
            when (screen) {
                1 -> ImageAndText(
                    drawableResource = R.drawable.beliy,
                    contentDescriptionResource = R.string.first_desc,
                    textTitleResource = R.string.first_title,
                    textArtistResource = R.string.first_artist,
                    textYearResource = R.string.first_year
                )
                2 -> ImageAndText(
                    drawableResource = R.drawable.injyr,
                    contentDescriptionResource = R.string.second_desc,
                    textTitleResource = R.string.second_title,
                    textArtistResource = R.string.second_artist,
                    textYearResource = R.string.second_year
                )
                3 -> ImageAndText(
                    drawableResource = R.drawable.rozovii,
                    contentDescriptionResource = R.string.third_desc,
                    textTitleResource = R.string.third_title,
                    textArtistResource = R.string.third_artist,
                    textYearResource = R.string.third_year
                )
            }

        }
    }
}

@Composable
fun ImageAndText(
    drawableResource: Int,
    contentDescriptionResource: Int,
    textTitleResource: Int,
    textArtistResource: Int,
    textYearResource: Int,
    modifier: Modifier = Modifier) {

    Surface(
        modifier = Modifier.padding(24.dp),
        shadowElevation = 4.dp
    ) {
        Image(
            painter = painterResource(drawableResource),
            modifier = Modifier.padding(24.dp),
            contentDescription = stringResource(contentDescriptionResource)
        )
    }
    Column(
        modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(colorResource(R.color.grey_for_background))
    ) {
        Text(
            text = stringResource(textTitleResource),
            modifier.padding(12.dp),
            fontSize = 24.sp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(textArtistResource))
                }
                append(" (${stringResource(textYearResource)})")
            },
            modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    ArtSpaceLayout()
}