package com.innova.parcial

import android.content.res.ColorStateList
import android.widget.RadioButton

fun RadioButton.disable(colorDisable: Int) {
    this.isClickable = false
    this.buttonTintList = ColorStateList.valueOf(colorDisable)
    this.setTextColor(colorDisable)
}

fun RadioButton.activate(colorButton: Int, colorText: Int) {
    this.isClickable = true
    this.buttonTintList = ColorStateList.valueOf(colorButton)
    this.setTextColor(colorText)
}