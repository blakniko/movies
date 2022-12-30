package com.movies.commons.error

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.movies.commons.R

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class ErrorLayout(context: Context, val attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private var title: String = ""
    private var image: Int? = null
    private var textButton = ""
    private var listener:(()->Unit)? = null

    init {
        setupAttrs()
        LayoutInflater.from(context).inflate(R.layout.error_layout, this, true)
        bindError()
    }

    private fun setupAttrs() {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ErrorLayout)
        title = attrs.getString(R.styleable.ErrorLayout_title) ?: "no data"
        textButton = attrs.getString(R.styleable.ErrorLayout_textButton) ?: "no data"
        image = attrs.getInt(R.styleable.ErrorLayout_image,R.drawable.ic_error)
    }

    fun setListener(listener:()->Unit){
        this.listener = listener
    }

    private fun bindError() {
        findViewById<TextView>(R.id.title).apply {
            text = title
        }

        findViewById<Button>(R.id.materialButton).apply {
            text= textButton
            setOnClickListener {
                listener?.invoke()
            }
        }

        findViewById<ImageView>(R.id.imageView).apply {
            setImageDrawable(resources.getDrawable(image?:R.drawable.ic_error))
        }
    }
}