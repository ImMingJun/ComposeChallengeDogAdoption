package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.model.Monster
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.statusBarsHeight

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val monster = intent?.getSerializableExtra("MONSTER")
        setContent {
            MyTheme {
                ProvideWindowInsets {
                    DetailPage(monster as Monster) {
                        finish()
                    }
                }
            }
        }
    }

}

@Composable
fun DetailPage(monster: Monster, onBackClick:() -> Unit) {
    val checkedState = remember { mutableStateOf(true) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (headImage, headOverlay, monsterName, monsterKind, monsterSex,
            masterName, masterAvatar, description,
            createTime, collectButton, contactButton) = createRefs()

        val (navigationSpace) = createRefs()

        Image(
            painter = painterResource(id = monster.imageUrl),
            contentDescription = "monster image",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.divider))
                .aspectRatio(1f)
                .constrainAs(headImage) {
                    top.linkTo(parent.top)
                },
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.immersive_sys_ui))
                .aspectRatio(1f)
                .constrainAs(headOverlay) {
                    top.linkTo(parent.top)
                }
        ) {
            Column {
                Spacer(modifier = Modifier.statusBarsHeight())
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Sharp.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }

        Text(
            text = monster.name,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp)
                .constrainAs(monsterName) {
                    bottom.linkTo(headOverlay.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.monster_kind_and_distance,
                monster.kind,
                monster.distance
            ),
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 15.dp)
                .constrainAs(monsterKind) {
                    bottom.linkTo(monsterName.top)
                    start.linkTo(parent.start)
                }
        )

        Icon(
            imageVector = if (monster.sex == 0) Icons.Sharp.Male else Icons.Sharp.Female,
            contentDescription = null,
            tint = if (monster.sex == 0) Color.Blue else Color.Red,
            modifier = Modifier
                .padding(start = 15.dp)
                .constrainAs(monsterSex) {
                    bottom.linkTo(monsterKind.top)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(id = monster.masterAvatar),
            contentDescription = "masterAvatar",
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .size(width = 40.dp, height = 40.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .constrainAs(masterAvatar) {
                    top.linkTo(headImage.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = monster.masterName,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 8.dp, top = 15.dp)
                .constrainAs(masterName) {
                    start.linkTo(masterAvatar.end)
                    top.linkTo(masterAvatar.top)
                }
        )

        Text(
            text = monster.createTime,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(createTime) {
                    start.linkTo(masterName.start)
                    top.linkTo(masterName.bottom)
                }
        )

        Text(
            text = monster.description,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .constrainAs(description) {
                    top.linkTo(masterAvatar.bottom)
                }
        )

        Card(
            elevation = 4.dp,
            modifier = Modifier
                .padding(15.dp)
                .constrainAs(collectButton) {
                    bottom.linkTo(navigationSpace.top)
                    start.linkTo(parent.start)
                    end.linkTo(contactButton.start)
                },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(23.dp)
        ) {
            IconToggleButton(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Sharp.Bookmark,
                    contentDescription = null,
                    tint = if (checkedState.value) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }
        }

        Card(
            elevation = 4.dp,
            modifier = Modifier
                .padding(bottom = 15.dp, end = 15.dp)
                .constrainAs(contactButton) {
                    bottom.linkTo(navigationSpace.top)
                    end.linkTo(parent.end)
                    start.linkTo(collectButton.end)
                    width = Dimension.fillToConstraints
                },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(22.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable {

                    }
                    .padding(start = 12.dp, top = 10.dp, end = 12.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Sharp.Phone, contentDescription = null)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Contact me",
                    color = Color.Black,
                )
            }
        }

        Spacer(modifier = Modifier
            .navigationBarsHeight()
            .constrainAs(navigationSpace) {
                bottom.linkTo(parent.bottom)
            })
    }
}
