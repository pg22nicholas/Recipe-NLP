package com.vfs.recipenlpapp


/** An immutable result returned by a TextClassifier describing what was classified.  */
class Result(

  //  A unique identifier for what has been classified. Specific to the class
  val id: String?,

  // Display name for the result.
  var title: String?, confidence: Float?
) :
  Comparable<Result?> {

  // A sortable score for how good the result is relative to others. Higher should be better.
  val confidence: Float?

  override fun toString(): String {
    var resultString = ""
    if (id != null) {
      resultString += "[$id] "
    }
    if (title != null) {
      resultString += "$title "
    }
    if (confidence != null) {
      resultString += String.format("(%.1f%%) ", confidence * 100.0f)
    }
    return resultString.trim { it <= ' ' }
  }

  override operator fun compareTo(other: Result?): Int {
    return other?.confidence!!.compareTo(confidence!!)
  }

  init {
    this.confidence = confidence
  }
}