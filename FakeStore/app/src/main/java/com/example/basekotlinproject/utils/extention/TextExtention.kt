package com.example.basekotlinproject.utils.extention

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun TextView.setSpannableColor(result: String, color: Int, start: Int, end: Int) {
    val builder = SpannableStringBuilder()
    builder.append(result)
    builder.setSpan(
        ForegroundColorSpan(color), start, end,
        Spanned.SPAN_INCLUSIVE_INCLUSIVE
    )
    text = builder
}

fun Context.getSpannableColor(
    result: String,
    color: Int,
    start: Int,
    end: Int
): SpannableStringBuilder {
    val builder = SpannableStringBuilder()
    builder.append(result)
    builder.setSpan(
        ForegroundColorSpan(color), start, end,
        Spanned.SPAN_INCLUSIVE_INCLUSIVE
    )
    return builder
}


fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
                textPaint.color = textPaint.linkColor
                // toggle below value to enable/disable
                // the underline shown below the clickable text
                textPaint.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}


fun EditText.setTextIfDifferent(newText: CharSequence?): Boolean {
    if (!isTextDifferent(newText, text)) {
        // Previous text is the same. No op
        return false
    }
    setText(newText)
    // Since the text changed we move the cursor to the end of the new text.
    // This allows us to fill in text programmatically with a different value,
    // but if the user is typing and the view is rebound we won't lose their cursor position.
    setSelection(newText?.length ?: 0)
    return true
}

private fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
    if (str1 === str2) {
        return false
    }
    if (str1 == null || str2 == null) {
        return true
    }
    val length = str1.length
    if (length != str2.length) {
        return true
    }
    if (str1 is Spanned) {
        return str1 != str2
    }
    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}

fun TextView.startDrawable(@DrawableRes id: Int = 0, @DimenRes sizeRes: Int) {
    if (id != 0) {
        val drawable = ContextCompat.getDrawable(context, id)
        val size = resources.getDimensionPixelSize(sizeRes)
        drawable?.setBounds(0, 0, size, size)
        this.setCompoundDrawables(drawable, null, null, null)
    } else {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }
}

fun TextView.endDrawable(@DrawableRes id: Int = 0, @DimenRes sizeRes: Int) {
    if (id != 0) {
        val drawable = ContextCompat.getDrawable(context, id)
        val size = resources.getDimensionPixelSize(sizeRes)
        drawable?.setBounds(0, 0, size, size)
        this.setCompoundDrawables(null, null, drawable, null)
    } else {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }
}