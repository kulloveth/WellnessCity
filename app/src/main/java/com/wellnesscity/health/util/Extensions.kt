package com.wellnesscity.health.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.snackbar.Snackbar
import com.wellnesscity.health.R


fun View.setMenuBackgroundColor(context: Context, @ColorInt color: Int){
    val unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.menu_bg)
    val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
    DrawableCompat.setTint(wrappedDrawable, color)
}

fun Drawable.tint(context: Context, @ColorRes color: Int) {
    DrawableCompat.setTint(this, ContextCompat.getColor(context, color))
}

fun View.snackMessage(message: String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).show()
}
