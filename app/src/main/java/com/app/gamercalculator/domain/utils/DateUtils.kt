package com.app.gamercalculator.domain.utils

import com.app.gamercalculator.domain.utils.DateUtils.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val DATE_TIME_FORMAT_YEAR = "dd/MM/yy"
    const val DATE_FORMAT_DAY_MONTH = "dd/MM"
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"
    const val DATE_TIME_FORMAT_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    fun formatTimeZ(date: String): String {
        val format = SimpleDateFormat(DATE_TIME_FORMAT_Z, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateParsed = format.parse(date)
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(dateParsed)
    }

}
