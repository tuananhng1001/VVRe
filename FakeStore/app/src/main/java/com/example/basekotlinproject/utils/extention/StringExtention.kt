package com.example.basekotlinproject.utils.extention

import java.math.BigInteger
import java.security.MessageDigest
import java.text.Normalizer
import java.util.regex.Pattern

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

fun timeSecond2TimePk(timePk: Long, timeLine: Boolean): String {
    val ss = (timePk.rem(60)).toString().padStart(2, '0')
    val mm = ((timePk % 3600) / 60).toString().padStart(2, '0')
    val hh = (timePk / 3600).toString().padStart(2, '0')
    if (timeLine) {
        return "$hh:$mm:$ss"
    }
    return "$mm:$ss"
}

fun String.toSlug() = lowercase()
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")

val String.Companion.Empty
    inline get() = ""

fun String.deAccent(): String {
    val nfdNormalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    return pattern.matcher(nfdNormalizedString).replaceAll("").replace(" ", "-").replace("đ", "d")
}