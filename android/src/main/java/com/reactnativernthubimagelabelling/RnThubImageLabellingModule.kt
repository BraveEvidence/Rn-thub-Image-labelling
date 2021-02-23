package com.reactnativernthubimagelabelling

import android.net.Uri
import com.facebook.react.bridge.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.File
import java.io.IOException
import java.lang.StringBuilder

class RnThubImageLabellingModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  private var imageLabellingSuccessCallback: Callback? = null
  private var imageLabellingFailureCallback: Callback? = null
  private val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

  override fun getName(): String {
    return "RnThubImageLabelling"
  }

  @ReactMethod
  fun imageLabelDetection(uri: String, successCallback: Callback, failureCallback: Callback) {
    imageLabellingSuccessCallback = successCallback
    imageLabellingFailureCallback = failureCallback

    val image: InputImage
    try {
      val file = File(uri)
      image = InputImage.fromFilePath(reactContext, Uri.parse(file.toString()))
      labeler.process(image)
        .addOnSuccessListener { labels ->
          val sb = StringBuilder()
          for (label in labels) {
            val text = label.text
            val confidence = label.confidence
            sb.append("${text}, Confidence: $confidence \n")
          }

          imageLabellingSuccessCallback?.invoke("$sb")
        }
        .addOnFailureListener { e ->
          imageLabellingFailureCallback?.invoke("${e.message.toString()}, Cannot label image")
        }
    } catch (e: IOException) {
      e.printStackTrace()
      imageLabellingFailureCallback?.invoke(e.message.toString())
    }
  }


}
