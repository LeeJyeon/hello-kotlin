package hello.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.*

fun LocalDateTime.format(): String = this.format(koreanDateFormatter)

private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }


private val englishDateFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd")
        .appendLiteral(" ")
        .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
        .appendLiteral(" ")
        .appendPattern("yyyy")
        .toFormatter(Locale.ENGLISH)

private val koreanDateFormatter = DateTimeFormatterBuilder()
        .appendText(ChronoField.YEAR, TextStyle.FULL)
        .appendLiteral("년")
        .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.FULL)
        .appendText(ChronoField.DAY_OF_MONTH, TextStyle.FULL)
        .appendLiteral("일")
        .toFormatter(Locale.KOREAN);


private fun getOrdinal(n: Int) = when {
    n in 11..13 -> "${n}th"
    n % 10 == 1 -> "${n}st"
    n % 10 == 2 -> "${n}nd"
    n % 10 == 3 -> "${n}rd"
    else -> "${n}th"
}

fun String.toSlug() = lowercase(Locale.getDefault())
        .replace("\n", " ")
        .replace("[^a-z\\d\\s가-힣]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")