/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.ContactPhone
import androidx.compose.material.icons.sharp.Female
import androidx.compose.material.icons.sharp.Male
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.model.Monster
import com.example.androiddevchallenge.model.MonsterRepo
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("MONSTER", it)
                    startActivity(intent)
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(onItemClick: (monster: Monster) -> Unit) {
    val allMonsters = remember { MonsterRepo.getMonsters() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Please Adopt Them !!!", color = Color.Black) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Sharp.AccountCircle,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) {
        LazyColumn {
            items(allMonsters) { monster ->
                MonsterItem(monster = monster, onItemClick)
            }
        }
    }
}

@Composable
fun MonsterItem(monster: Monster, onItemClick: (monster: Monster) -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 6.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.divider),
                shape = RoundedCornerShape(6.dp)
            )
            .background(Color.White)
            .clickable(true) {
                onItemClick(monster)
            }
    ) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            ConstraintLayout {
                val (avatar, masterName, createTime, contact,
                    monsterImage, monsterName, monsterSex,
                    monsterKind, monsterDescription) = createRefs()

                val (space1, space2) = createRefs()

                Spacer(modifier = Modifier
                    .width(12.dp)
                    .constrainAs(space1) {
                        start.linkTo(parent.start)
                    })

                Image(
                    painter = painterResource(id = monster.masterAvatar),
                    contentDescription = "masterAvatar",
                    modifier = Modifier
                        .size(width = 40.dp, height = 40.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .constrainAs(avatar) {
                            top.linkTo(parent.top)
                            start.linkTo(space1.end)
                        }
                )

                Text(
                    text = monster.masterName,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .constrainAs(masterName) {
                            start.linkTo(avatar.end)
                            top.linkTo(avatar.top)
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

                IconButton(
                    onClick = {},
                    modifier = Modifier.constrainAs(contact) {
                        top.linkTo(avatar.top)
                        bottom.linkTo(avatar.bottom)
                        end.linkTo(parent.end)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Sharp.ContactPhone,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }

                Image(
                    painter = painterResource(id = monster.imageUrl),
                    contentDescription = "masterAvatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .aspectRatio(ratio = 16f / 9f)
                        .constrainAs(monsterImage) {
                            top.linkTo(avatar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Spacer(modifier = Modifier
                    .height(8.dp)
                    .constrainAs(space2) {
                        top.linkTo(monsterImage.bottom)
                    })

                Text(
                    text = monster.name,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .constrainAs(monsterName) {
                            start.linkTo(parent.start)
                            top.linkTo(space2.bottom)
                        }
                )

                Text(
                    text = stringResource(
                        id = R.string.monster_kind_and_distance,
                        monster.kind,
                        monster.distance
                    ),
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .constrainAs(monsterKind) {
                            start.linkTo(monsterName.end)
                            bottom.linkTo(monsterName.bottom)
                        }
                )

                Text(
                    text = monster.description,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp)
                        .constrainAs(monsterDescription) {
                            start.linkTo(monsterName.start)
                            top.linkTo(monsterName.bottom)
                        }
                )

                Icon(
                    imageVector = if (monster.sex == 0) Icons.Sharp.Male else Icons.Sharp.Female,
                    contentDescription = null,
                    tint = if (monster.sex == 0) Color.Blue else Color.Red,
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(monsterSex) {
                            top.linkTo(monsterImage.bottom)
                            end.linkTo(parent.end)
                        }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp {}
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp {}
    }
}
