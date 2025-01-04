package com.mariods.tablerocaa

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mariods.tablerocaa.data.CoreVocabularyProvider
import com.mariods.tablerocaa.data.coreVoabulary
import com.mariods.tablerocaa.ui.theme.TableroCAATheme
import com.mariods.tablerocaa.ui.theme.categoryPrepositions
import com.mariods.tablerocaa.ui.theme.categoryPronouns
import com.mariods.tablerocaa.ui.theme.categoryQuestions
import com.mariods.tablerocaa.ui.theme.categoryVerbs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            TableroCAATheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                ) { innerPadding ->
                    PrincipalBoard(
                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PrincipalBoard(
    name: String,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    //val items = (1..80).map { "Item $it" }//List(80) { "Item $it" }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(500.dp, 50.dp)
                    .align(Alignment.CenterVertically),
                text = "Hola",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Button(
                    colors = ButtonColors(
                        contentColor = Color.Red,
                        containerColor = Color.Red,
                        disabledContentColor = Color.Blue,
                        disabledContainerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {


                    }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Vocabulario\nNúcleo",
                        color = Color.White
                    )
                }
                Text(text = "Mario")
                Text(text = "Mario")
                Text(text = "Mario")
                Text(text = "Mario")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(10),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxSize()

//                        .background(Color.Gray).verticalScroll(
//                            enabled = false,
//                            state = rememberScrollState()
//                        )
                ) {
                    items(coreVoabulary) { item ->
                        Item(item)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
                    .height(100.dp)
            ) { }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun PrincipalBoardPreview() {
    TableroCAATheme {
        PrincipalBoard("Mi tablero de comunicación")
    }
}

@Composable
fun Item(item: CoreVocabularyProvider) {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }
    val categoryColor = {
        when (item.category) {
            "pronombres" -> categoryPronouns
            "verbos" -> categoryVerbs
            "preposiciones" -> categoryPrepositions
            "preguntas" -> categoryQuestions
            else -> {
                Color.Gray
            }
        }
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = categoryColor.invoke(),
            disabledContentColor = Color.Blue,
            disabledContainerColor = Color.Black
        ),
        onClick = {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, R.raw.hola)
            }
            mediaPlayer?.start()
        }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            textAlign = TextAlign.Center,
            text = item.word
        )
        Image(
            modifier = Modifier
                .size(100.dp, 45.dp)
                .padding(4.dp),
            painter = painterResource(R.drawable.picto_hola),
            contentDescription = "Imagen"
        )
    }
}