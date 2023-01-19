package com.example.ejerciciosdialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ejerciciosdialog.R
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screensplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screensplash.setKeepOnScreenCondition{false}


        var mAlertButton = findViewById<Button>(R.id.button)
        mAlertButton.setOnClickListener{
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Alerta")
            builder.setMessage("Mensaje de alerta")
            builder.setPositiveButton(R.string.aceptar){
                    dialogo, which ->
                Toast.makeText((applicationContext), "Aceptado", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancelar", null)
            builder.setNeutralButton(R.string.remindmelater){
                    dialogo, which ->
                Toast.makeText((applicationContext), "Recordar más tarde", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
        var mDateButton = findViewById<Button>(R.id.buttonDate)
        mDateButton.setOnClickListener{
            var fecha = Calendar.getInstance()
            var listenerfecha = DatePickerDialog.OnDateSetListener{
                    datePicker, year, month, day -> Toast.makeText((applicationContext), "Fecha: $day/${month+1}/$year", Toast.LENGTH_SHORT).show()
            }
            DatePickerDialog(this, listenerfecha, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH)).show()
        }

        var mHourButton = findViewById<Button>(R.id.buttonHour)
        mHourButton.setOnClickListener{
            var hora = Calendar.getInstance()
            var listenerhora = TimePickerDialog.OnTimeSetListener{
                    timePicker, hour, minute -> Toast.makeText((applicationContext), "Hora: $hour:$minute", Toast.LENGTH_SHORT).show()
            }
            TimePickerDialog(this, listenerhora, hora.get(Calendar.HOUR_OF_DAY), hora.get(Calendar.MINUTE), true).show()
        }

        var mAlertList = findViewById<Button>(R.id.buttonList)
        var programList = arrayOf("Android", "iOS", "Windows", "Linux")
        mAlertList.setOnClickListener{
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Que lenguaje conoces?")
            builder.setItems(programList){
                    imdice, posicion ->
                Toast.makeText(applicationContext, programList[posicion], Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        var mAlertListMultiple = findViewById<Button>(R.id.buttonLisMultiple)
        mAlertListMultiple.setOnClickListener{
            var builder = AlertDialog.Builder(this)
            var animalList = arrayOf("Perro", "Gato", "Pez", "Tortuga")
            var elegidos = ArrayList<Int>()
            builder.setTitle("Que animal conoces?")
            builder.setMultiChoiceItems(animalList, null){
                    indice, posicion, isChecked ->
                if(isChecked){
                    elegidos.add(posicion)
                    Toast.makeText(applicationContext, "${animalList[posicion]} seleccionado", Toast.LENGTH_SHORT).show()
                }else{
                    elegidos.remove(posicion)
                    Toast.makeText(applicationContext, "${animalList[posicion]} deseleccionado", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setPositiveButton(R.string.aceptar){
                    dialogo, posicion ->
                var respuesta = ""
                if (elegidos.size > 0) {
                    for (item in elegidos) {
                        respuesta += "${animalList[item]}"
                    }
                }else{
                    respuesta = "No has seleccionado ningun animal"
                }
                Toast.makeText(applicationContext, respuesta, Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancelar", null)

            builder.show()
        }

    }

}