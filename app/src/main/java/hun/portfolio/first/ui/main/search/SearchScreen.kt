package hun.portfolio.first.ui.main.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hun.portfolio.first.R
import hun.portfolio.first.ui.chat.UserInput

@Composable
fun SearchScreen() {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = "가요대축제 베이비복스 출연 소식에 대해 알려줘",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Text(
                        text = "답변",
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                LazyColumn {
                    item {
                        VideoLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        ImageLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        TextResultLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
            UserInput(onMessageSent = {})
        }
    }
}

private val layoutShape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp)
private val thumbnailShape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp)

@Composable
fun VideoLayout(modifier: Modifier = Modifier) {
    Surface(
        color = Color.Transparent,
        shape = layoutShape,
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Column {
                Row {
                    Image(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Text(text = "동영상")
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                LazyRow {
                    items(3) {
                        VideoLayoutItem()
                        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun VideoLayoutItem() {
    Box {
        Column {
            Image(
                painter = painterResource(R.drawable.someone_else),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 160.dp, height = 90.dp)
                    .clip(shape = thumbnailShape),
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = "Title",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            Text(
                text = "Sub",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}

@Composable
fun ImageLayout(modifier: Modifier = Modifier) {
    Surface(
        color = Color.Transparent,
        shape = layoutShape,
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Column {
                Row {
                    Image(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Text(text = "이미지")
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                LazyRow {
                    items(3) {
                        ImageLayoutItem()
                        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ImageLayoutItem() {
    Box {
        Column {
            Image(
                painter = painterResource(R.drawable.someone_else),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = thumbnailShape)
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = "Title",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1
            )
            Text(
                text = "Sub",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}

@Composable
fun TextResultLayout(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "베이비복스가 2024 KBS 가요대축제에 출연하여 14년만에 완전체 무대를 선보였습니다."
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
