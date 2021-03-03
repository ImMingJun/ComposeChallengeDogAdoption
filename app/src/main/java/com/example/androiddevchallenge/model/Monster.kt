/*
 * Copyright 2020 The Android Open Source Project
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
package com.example.androiddevchallenge.model

import androidx.compose.runtime.Immutable
import com.example.androiddevchallenge.R
import java.io.Serializable

@Immutable // Tell Compose runtime that this object will not change so it can perform optimizations
data class Monster(
    val id: Long,
    val name: String,
    val description: String = "",
    val kind: String,
    val sex: Int, // 0 male 1 female
    val distance: String,
    val imageUrl: Int,
    val masterName: String,
    val masterAvatar: Int,
    val createTime: String,
) : Serializable

/**
 * A fake repo
 */
object MonsterRepo {
    fun getMonsters(): List<Monster> = monsters
}

val monsters = listOf(
    Monster(
        id = 0,
        name = "Candy",
        description = "Round fat face, a pair of big eyes flickering, especially the big mouth, smile slightly upturned corners of the mouth, speaking of words very loud.",
        kind = "Shiba Inu",
        sex = 0,
        distance = "0.1km",
        imageUrl = R.mipmap.monster1,
        masterName = "Peter",
        masterAvatar = R.mipmap.avatar1,
        createTime = "1 minutes ago",
    ),
    Monster(
        id = 1,
        name = "Honey",
        description = "He is as smooth as the loach back, dark healthy arms, full and strong legs, between the brows of the ineffable lovely expression, lovely attractive cherry mouth, is how lovely ah!  ",
        kind = "Mad Dog",
        sex = 0,
        distance = "2km",
        imageUrl = R.mipmap.monster2,
        masterName = "Parker",
        masterAvatar = R.mipmap.avatar2,
        createTime = "5 minutes ago",
    ),
    Monster(
        id = 2,
        name = "Barbara",
        description = "She is a lively and lovely little girl, white face, curved eyebrows under a pair of ShuiLing eyes.",
        kind = "Cry Dog",
        sex = 1,
        distance = "5km",
        imageUrl = R.mipmap.monster3,
        masterName = "Aime",
        masterAvatar = R.mipmap.avatar3,
        createTime = "21 minutes ago",
    ),
    Monster(
        id = 3,
        name = "Anna",
        description = "In the sun, I saw a small stature, face black red boy, a pair of big eyes under the thick eyebrows. Wearing a red armband on his left arm, the word \"on duty\" was very conspicuous.",
        kind = "Husky",
        sex = 0,
        distance = "100km",
        imageUrl = R.mipmap.monster4,
        masterName = "XueLi",
        masterAvatar = R.mipmap.avatar4,
        createTime = "35 minutes ago",
    ),
    Monster(
        id = 4,
        name = "Katrina",
        description = "He is a Bai boy who loves to talk and laugh.",
        kind = "Shiba Inu",
        sex = 0,
        distance = "1km",
        imageUrl = R.mipmap.monster5,
        masterName = "Aba Aba",
        masterAvatar = R.mipmap.avatar5,
        createTime = "51 minutes ago",
    ),
    Monster(
        id = 5,
        name = "Cinderella",
        description = "She loves singing and dancing, and she joined the school dance team! She is wearing that dress with red ground and white flowers. When she dances, she moves beautifully!",
        kind = "Husky",
        sex = 0,
        distance = "10km",
        imageUrl = R.mipmap.monster6,
        masterName = "Wooseidisy",
        masterAvatar = R.mipmap.avatar6,
        createTime = "1 hour ago",
    ),

)
