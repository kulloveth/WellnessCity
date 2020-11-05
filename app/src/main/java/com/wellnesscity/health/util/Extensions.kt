package com.wellnesscity.health.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.snackbar.Snackbar
import com.wellnesscity.health.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


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
@ExperimentalCoroutinesApi
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}

fun String.equalsIgnoreCase(other: String) =
    (this as java.lang.String).equalsIgnoreCase(other)
