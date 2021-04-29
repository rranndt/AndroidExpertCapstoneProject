package id.expert.capstoneproject.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Helper {

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(
        currentFormat: String,
        requiredFormat: String,
        dateString: String
    ): String {
        val oldFormatDate = SimpleDateFormat(currentFormat)
        val newFormatDate = SimpleDateFormat(requiredFormat)
        val updateDate = oldFormatDate.parse(dateString)
        return newFormatDate.format(updateDate!!)
    }

}