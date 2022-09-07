package com.innova.parcial

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.innova.parcial.databinding.ActivityMainBinding
import com.innova.parcial.extensions.roundDecimals
import com.innova.parcial.extensions.toFraction
import java.lang.Exception
import java.lang.Math.*
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val operations = resources.getStringArray(R.array.operations)
        val adapter = ArrayAdapter(this, R.layout.list_item, operations)
        binding.dropDownMenu.apply {
            setAdapter(adapter)
            onItemClickListener = this@MainActivity
        }

        binding.apply {
            val myRadioButtonList =
                listOf(rbSquare, rbCubic, rbSine, rbCosine, rbTangent, rbCotangent)
            var firstValidation = false
            var secondValidation = false

            disableRadioButtons(myRadioButtonList)

            etAngleA.doAfterTextChanged {
                firstValidation = etAngleA.text.toString() != ""
                if (!firstValidation || !secondValidation) disableRadioButtons(myRadioButtonList)
                else activateRadioButtons(myRadioButtonList)

                etAngleA.error = if (!isFilledField(etAngleA)) "Enter a number" else null
            }

            etAngleB.doAfterTextChanged {
                secondValidation = etAngleB.text.toString() != ""
                if (!firstValidation || !secondValidation) disableRadioButtons(myRadioButtonList)
                else activateRadioButtons(myRadioButtonList)

                etAngleB.error = if (!isFilledField(etAngleB)) "Enter a number" else null
            }
        }
    }

    private fun isFilledField(editText: EditText) = editText.text.toString() != ""

    override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        binding.apply {
            val myRadioGroupList = listOf(rgRoot, rgTrigonoRelations)
            when (parent?.getItemAtPosition(pos).toString()) {
                //root es raiz
                "Roots" -> {
                    tvRaiseContent.text = null
                    defineVisibilityToRadioGroup(rgRoot, myRadioGroupList)
                    btnCalculate.setOnClickListener {
                        try {
                            val angleA: Double = etAngleA.text.toString().toDouble()
                            val angleB: Double = etAngleB.text.toString().toDouble()

                            tvResult.text = when {
                                rbSquare.isChecked -> "Root A: ${sqrt(angleA).roundDecimals()}\n${
                                    sqrt(angleA).roundDecimals().toFraction()
                                }\nRoot B: ${sqrt(angleB).roundDecimals()}\n${
                                    sqrt(angleB).roundDecimals().toFraction()
                                }"
                                rbCubic.isChecked -> "Root A: ${cbrt(angleA).roundDecimals()}\n${
                                    cbrt(angleA).roundDecimals().toFraction()
                                }\nRoot B: ${cbrt(angleB.roundDecimals()).roundDecimals()}\n${
                                    cbrt(angleB.roundDecimals()).roundDecimals().toFraction()
                                }"
                                else -> {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Choose a option",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    ""
                                }
                            }

                        } catch (e: Exception) {
                        }
                    }
                }
                "Trigonometric relations" -> {
                    tvRaiseContent.text = null
                    defineVisibilityToRadioGroup(rgTrigonoRelations, myRadioGroupList)
                    btnCalculate.setOnClickListener {
                        try {
                            val angleA: Double = etAngleA.text.toString().toDouble()
                            val angleB: Double = etAngleB.text.toString().toDouble()

                            tvResult.text = when {
                                rbSine.isChecked -> "Sine A: ${sin(angleA).roundDecimals()}\n${
                                    sin(angleA).roundDecimals().toFraction()
                                } \nSine B ${sin(angleB).roundDecimals()}\n${
                                    sin(angleB).roundDecimals().toFraction()
                                }"
                                rbCosine.isChecked -> "Cosine A: ${cos(angleA).roundDecimals()}\n${
                                    cos(angleA).roundDecimals().toFraction()
                                }\nCosine B ${cos(angleB).roundDecimals()}\n${
                                    cos(angleB).roundDecimals().toFraction()
                                }"
                                rbTangent.isChecked -> "Tangent A: ${
                                    kotlin.math.tan(angleA).roundDecimals()
                                }\n${
                                    kotlin.math.tan(angleA).roundDecimals().toFraction()
                                }\nTangent B: ${
                                    kotlin.math.tan(angleB).roundDecimals()
                                }\n${kotlin.math.tan(angleB).roundDecimals().toFraction()}"
                                rbCotangent.isChecked -> "Cotangent A: ${(cos(angleA) / sin(angleA)).roundDecimals()}\n ${
                                    (cos(angleA) / sin(angleA)).roundDecimals().toFraction()
                                }\nCotangent B: ${(cos(angleB) / sin(angleB)).roundDecimals()}\n ${
                                    (cos(angleB) / sin(angleB)).roundDecimals().toFraction()
                                }"
                                else -> {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Choose a option",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    ""
                                }
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
                "Pow" -> {
                    defineVisibilityToRadioGroup(null, myRadioGroupList)
                    btnCalculate.setOnClickListener {
                        try {
                            val angleA: Double = etAngleA.text.toString().toDouble()
                            val angleB: Double = etAngleB.text.toString().toDouble()

                            tvRaiseContent.text = "${angleA} ^ ${angleB}"

                            tvResult.text = "${angleA.pow(angleB).roundDecimals()}\n${
                                angleA.pow(angleB).roundDecimals().toFraction()
                            }"
                        } catch (e: NumberFormatException) {
                            Log.d("Error", "Este es mi error${e}")
                        }
                    }
                }
            }
        }
    }

    private fun defineVisibilityToRadioGroup(
        rgToSetVisible: RadioGroup?,
        rgList: List<RadioGroup>,
    ) {
        rgList.map {
            it.visibility = if (it.id == rgToSetVisible?.id) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun activateRadioButtons(radioButtonList: List<RadioButton>) {
        radioButtonList.map {
            it.activate(getColor(R.color.activate_button), getColor(R.color.activate_text))
        }
    }

    private fun disableRadioButtons(radioButtonList: List<RadioButton>) {
        radioButtonList.map {
//            it.isChecked = false
            it.disable(getColor(R.color.disable))
        }
    }
}