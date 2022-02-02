package com.utility.mylibrary

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import java.text.DecimalFormat

/**Contains top level function for smaller
 * */

inline fun EditText.onTextChanged(crossinline work: (query: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                work.invoke(s.toString())
            }
        }
    })
}

inline fun SearchView.search(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}

fun String?.isNotNullNorEmpty(): Boolean = (this != null && this.trim().isNotEmpty())

fun Any.getCurrency(prefix: String = "₹"): String {
    try {
        val df = DecimalFormat("0.00")
        return "$prefix ${df.format(this)}"
    } catch (e: Exception) {
        throw e
    }
}