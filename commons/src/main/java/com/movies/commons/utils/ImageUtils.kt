package com.movies.commons.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class ImageUtils {
    companion object{
        fun String.stringToImage(image:ImageView){
            Glide.with(image.context).load(this).into(image)
        }
    }
}