package com.puput.github.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserList(val login : String, val id : String, val avatar_url: String ): Parcelable
