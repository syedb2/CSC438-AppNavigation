package com.example.appnavigation.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
data class GalleryItem(@StringRes val name: Int, @DrawableRes val image: Int, @StringRes val description: Int)
