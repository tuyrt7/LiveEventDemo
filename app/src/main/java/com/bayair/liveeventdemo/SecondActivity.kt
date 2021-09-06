package com.tuyrt.liveeventdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tuyrt.liveeventbus.LiveEventBus

class SecondActivity : AppCompatActivity() {

    private lateinit var foreverObserver: Observer<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        LiveEventBus.get("asd", String::class.java)
                .observe(this) {

                }
        LiveEventBus.get(Int::class.java).observeSticky(this) {
            Log.d("TAG", "SecondActivity observeSticky接收到的值：$it")
        }
        foreverObserver = Observer<Int> {
            Log.d("TAG", "SecondActivity observeForeverSticky接收到的值：$it")
        }
        LiveEventBus.get(Int::class.java).observeForeverSticky(foreverObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        LiveEventBus.get(Int::class.java).removeObserver(foreverObserver)
    }
}