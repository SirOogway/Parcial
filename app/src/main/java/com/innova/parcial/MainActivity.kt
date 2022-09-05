package com.innova.parcial

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.innova.parcial.databinding.ActivityMainBinding
import java.lang.Math.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // CREAR LOGICA PARA CUANDO HAYA UN EDIT TEXT VACIO Y SE PRECIONE EL BOTON NO SE CIERRE LA APP

        val operations = resources.getStringArray(R.array.operations)
        val adapter = ArrayAdapter(this, R.layout.list_item, operations)
        binding.dropDownMenu.apply {
            setAdapter(adapter)
            onItemClickListener = this@MainActivity
            inputType = InputType.TYPE_NULL
        }
//        Leer los valores de dos ángulos A y B, con un menú con las siguientes opciones:
//
//       * Mostar las Raíces Cuadradas y cúbicas de ambos ángulos
//       * Mostrar los valores de las funciones Seno, Coseno, Tangente y Cotangente
//       * Mostrar  A elevado a la  B
//                Todos los valores de repuesta debe ser presentados como números quebrados, por ejemplo 0.5 debe mostrarse como 1/2  y 3,141592 como 333/106
//
//        La tolerancia, es decir la diferencia absoluta entre 3.141592 y 333/106 debe ser como mínima de 0.01
//
//        Nota: Probar códigos https://replit.com/
//
//        Entregar:
//
//        Carpeta de proyecto comprimida
//                Informe de app (Documento en word describe el funcionamiento con pantallazos)
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
            }

            etAngleB.doAfterTextChanged {
                secondValidation = etAngleB.text.toString() != ""
                if (!firstValidation || !secondValidation) disableRadioButtons(myRadioButtonList)
                else activateRadioButtons(myRadioButtonList)
            }
        }
    }

    //tal vez sean radianes probar LUEGOOOOOOOOOOOOOOOOOOOOOOOO
//    fun calculateSine(angle: Double) = sin(angle)
//
//    fun calculateCosine(angle: Double) = cos(angle * Math.PI / 180)
//
//    fun calculateTangent(angle: Double) = kotlin.math.tan(angle * Math.PI / 180)

    //
    override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        binding.apply {
            val myRadioGroupList = listOf(rgRoot, rgTrigonoRelations)
            when (parent?.getItemAtPosition(pos).toString()) {
                //root es raiz
                "Roots" -> {
                    defineVisibilityToRadioGroup(rgRoot, myRadioGroupList)
                    btnCalculate.setOnClickListener {
                        val angleA: Double = etAngleA.text.toString().toDouble()
                        val angleB: Double = etAngleB.text.toString().toDouble()

                        tvResult.text = when {
                            rbSquare.isChecked -> "Root A: ${sqrt(angleA)} \nRoot B: ${sqrt(angleB)}"
                            rbCubic.isChecked -> "Root A: ${cbrt(angleA)} \nRoot B: ${cbrt(angleB)}"
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
                    }
                }
                "Trigonometric relations" -> {
                    defineVisibilityToRadioGroup(rgTrigonoRelations, myRadioGroupList)
                    btnCalculate.setOnClickListener {
                        val angleA: Double = etAngleA.text.toString().toDouble()
                        val angleB: Double = etAngleB.text.toString().toDouble()

                        tvResult.text = when {
                            rbSine.isChecked -> "Sine A: ${sin(angleA)} \nSine B ${sin(angleB)}"
                            rbCosine.isChecked -> "Cosine A: ${cos(angleA)} \nCosine B ${cos(angleB)}"
                            rbTangent.isChecked -> "Tangent A: ${kotlin.math.tan(angleA)} \nTangent B: ${
                                kotlin.math.tan(angleB)
                            }"
                            rbCotangent.isChecked -> "Cotangent A: ${cos(angleA) / sin(angleA)} \nCotangent B: ${
                                cos(angleB) / sin(angleB)
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
                    }
                }
                "Raise" -> {
                    defineVisibilityToRadioGroup(null, myRadioGroupList)
                }
            }
        }
    }

    private fun defineVisibilityToRadioGroup(
        rgToSetVisible: RadioGroup?,
        rgList: List<RadioGroup>
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

    //    Funciones de extención RADIO BUTTON
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
}