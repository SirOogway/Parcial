package com.innova.parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
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

        val operations = resources.getStringArray(R.array.operations)
        val adapter = ArrayAdapter(this, R.layout.list_item, operations)
        et.setAdapter(adapter)

    }
}