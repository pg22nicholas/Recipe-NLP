package com.vfs.recipenlpapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier
import java.io.IOException
import java.util.*


/** Load TfLite model and provide predictions with task api.  */
class TextClassificationClient(private val context: Context) {
    var classifier: BertNLClassifier? = null
    fun load(modelName : String) {
        try {
            classifier = BertNLClassifier.createFromFile(context, modelName)
        } catch (e: IOException) {
            Log.e(TAG, e.message!!)
        }
    }

    fun unload() {
        classifier!!.close()
        classifier = null
    }

    fun classify(text: String?): List<Result> {
        val apiResults = classifier!!.classify(text)
        val results: MutableList<Result> = ArrayList<Result>(apiResults.size)
        for (i in apiResults.indices) {
            val category = apiResults[i]
            results.add(Result("" + i, category.label, category.score))
        }
        Collections.sort(results)
        Toast.makeText(context, results.toString(), Toast.LENGTH_LONG).show()
        return results
    }

    companion object {
        private const val TAG = "TaskApi"
        private const val MODEL_PATH = "FoodTimeModel.tflite"
    }
}