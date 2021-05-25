package com.puput.github.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserModel(
    val login : String,
    val id : String,
    val avatar_url : String,
    val followers_url : String,
    val following_url : String,
    val following : String,
    val followers: String,
    val repos_url : String,
    val public_repos : String,
    val location : String,
    val name : String,
    val company: String
): Parcelable
