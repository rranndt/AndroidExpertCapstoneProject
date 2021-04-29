package id.expert.capstoneproject.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.expert.capstoneproject.core.R
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

    fun setGlide(context: Context, imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_loading_placeholder)
            .error(R.drawable.ic_placeholder_error)
            .into(imageView)
    }

    fun setGlideWithRadius(context: Context, imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(RoundedCorners(16))
            .placeholder(R.drawable.ic_loading_placeholder)
            .error(R.drawable.ic_placeholder_error)
            .into(imageView)
    }

}