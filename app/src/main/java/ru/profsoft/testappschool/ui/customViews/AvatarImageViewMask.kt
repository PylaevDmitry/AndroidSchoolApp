package ru.profsoft.testappschool.ui.customViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import ru.profsoft.testappschool.dpToPx

class AvatarImageViewMask @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
    ): androidx.appcompat.widget.AppCompatImageView (context, attrs, defStyleAttr) {

    private var maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var viewRect = Rect()
    private lateinit var resultBm: Bitmap
    private lateinit var maskBm: Bitmap
    private lateinit var srcBm: Bitmap

//    companion object {
//        private const val DEFAULT_SIZE = 150
//    }
//
//    init {
//        scaleType = ScaleType.CENTER_CROP
//        setup()
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val initSize = resolveDefaultSize(widthMeasureSpec)
//        setMeasuredDimension(initSize, initSize)
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w==0) return
        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }
        prepareBitmaps(w, h)
        Log.e("tag","onSizeChanged")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(resultBm, viewRect, viewRect, null)
        Log.e("tag","onDraw")
    }

//    override fun setImageDrawable(drawable: Drawable?) {
//        super.setImageDrawable(drawable)
//        prepareBitmaps(width, height)
//        Log.e("tag","setImageDrawable")
//    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        prepareBitmaps(width, height)
        Log.e("tag","setImageURI")
    }

    private fun prepareBitmaps(w: Int, h:Int) {
        maskBm = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8)
        resultBm = maskBm.copy(Bitmap.Config.ARGB_8888, true)
        val maskCanvas = Canvas(maskBm)
        maskCanvas.drawRoundRect(viewRect.toRectF(), 100.0F, 100.0F, maskPaint)
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        srcBm = drawable.toBitmap(w, h, Bitmap.Config.ARGB_8888)

        val resultCanvas = Canvas (resultBm)

        resultCanvas.drawBitmap(maskBm, viewRect, viewRect, null)
        resultCanvas.drawBitmap(srcBm, viewRect, viewRect, maskPaint)
        Log.e("tag","prepareBitmaps")
    }

//    private fun setup() {
//        with(maskPaint) {
//            color = Color.RED
//            style = Paint.Style.FILL
//        }
//    }
//
//    private fun resolveDefaultSize(spec: Int): Int {
//        return when (MeasureSpec.getMode((spec))) {
//            MeasureSpec.UNSPECIFIED -> {
//                context.dpToPx(DEFAULT_SIZE).toInt()
//            }
//            MeasureSpec.AT_MOST -> {
//                MeasureSpec.getSize(spec)
//            }
//            MeasureSpec.EXACTLY -> {
//                MeasureSpec.getSize(spec)
//            }
//            else -> MeasureSpec.getSize(spec)
//        }
//    }

}
