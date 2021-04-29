package id.expert.capstoneproject.core.bindingadapters

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.expert.capstoneproject.core.BuildConfig.IMAGE_URL
import id.expert.capstoneproject.core.R
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_CURRENT_FORMAT
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_REQUIRED_FORMAT
import id.expert.capstoneproject.core.utils.Helper.changeDateFormat

class BindingAdapter {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImage(
            imageView: ImageView,
            imageUrl: String
        ) {
            Glide.with(imageView.context)
                .load("$IMAGE_URL$imageUrl")
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_placeholder_error)
                .into(imageView)
        }

        @BindingAdapter("loadImageWithRadiusFromUrl")
        @JvmStatic
        fun loadImageWithRadius(
            imageView: ImageView,
            imageUrl: String
        ) {
            Glide.with(imageView.context)
                .load("$IMAGE_URL$imageUrl")
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(RoundedCorners(16))
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_placeholder_error)
                .into(imageView)
        }

        @BindingAdapter("getRatingBar")
        @JvmStatic
        fun getRating(
            ratingBar: RatingBar,
            voteAverage: Float
        ) {
            ratingBar.rating = voteAverage / 2
        }

        @BindingAdapter("getReleaseYear")
        @JvmStatic
        fun getReleaseYear(
            setReleaseYear: TextView,
            release: String
        ) {
            val releaseYear = changeDateFormat(
                DATE_CURRENT_FORMAT,
                DATE_REQUIRED_FORMAT,
                release
            )
            setReleaseYear.text = releaseYear
        }

    }
}