package com.example.r8bug

import android.os.Bundle
import android.widget.TextView
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.full.primaryConstructor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Option 1: Comment this line; not instantiating the enclosing class will allow the
        // primary constructor to be found.
        Wrapper()

        val wrappedClsPrimaryCtr = Wrapper.Wrapped::class.primaryConstructor
        findViewById<TextView>(R.id.textView).text =
            wrappedClsPrimaryCtr?.toString() ?: "Cannot find primary constructor"
    }
}

// Option 2: Uncomment this line; @Keeping the enclosing class will allow the primary constructor
// to be found.
//@Keep
class Wrapper {
    @Keep
    data class Wrapped(val data: Int)
}
