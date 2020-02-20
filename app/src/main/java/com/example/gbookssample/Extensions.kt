package com.example.gbookssample.com.example.gbookssample

import android.view.View

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.setInvisible(){
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}