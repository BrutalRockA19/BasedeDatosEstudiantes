package com.example.basedatos

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_alta_estudiante.*
import com.example.basedatos.util.validation.Companion.isEmailValid
import java.util.*

class AltaEstudiante : AppCompatActivity() {
    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_estudiante)

      //  edtNacimiento.setInputType(InputType.TYPE_NULL);
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtNacimiento.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                var mes= (monthOfYear + 1)
                var mesString =""
                if(mes in 1..9){
                    mesString = ("0")+mes.toString()
                }else{
                    mesString = mes.toString()
                }


                //edtNacimiento.setText("" + dayOfMonth + "-" + mes.toString() + "-" + year)
                edtNacimiento.setText("" + year + "/" + mesString + "/" + dayOfMonth)
            }, year, month, day)
            dpd.show()

        }


        btnRegistrar.setOnClickListener{
            if(edtNombreAlta.text.toString().trim().isNotEmpty()){
               // Toast.makeText(this@AltaEstudiante,"Que transa si le metiste datos",Toast.LENGTH_LONG).show()
                var nombreAlta= edtNombreAlta.text.toString()
                if(edtApellidoPAlta.text.toString().trim().isNotEmpty()){
                    //validacionAP
                    var ApAlta = edtApellidoPAlta.text.toString()
                    if(edtApellidoMAlta.text.toString().trim().isNotEmpty()){
                        var AmAlta = edtApellidoMAlta.text.toString()
                        val generoposition = spnGenero.selectedItemPosition

                        if (generoposition > 0){
                            val selectedNivelAcademico = rdgNivelAcademico.checkedRadioButtonId
                            if (selectedNivelAcademico != -1){
                                var nivelAcademico = 0
                                when(selectedNivelAcademico){
                                    rdbPrepa.id->{
                                        nivelAcademico = 1
                                    }
                                    rdbSecundaria.id->{
                                        nivelAcademico = 2
                                    }
                                    rdbUniversidad.id->{
                                        nivelAcademico = 3
                                    }
                                }

                                val escuelaposition = spnEscuelaProcedencia.selectedItemPosition

                                if (escuelaposition > 0){
                                    if(edtTelefonoAlta.text.toString().trim().isNotEmpty()){
                                        var phone = edtTelefonoAlta.text.toString()
                                        if((edtCorreoAlta.text.toString().trim().isNotEmpty()) and (isEmailValid(edtCorreoAlta.text.toString().trim().toString()))){
                                            var correo = edtCorreoAlta.text.toString()
                                            if(edtNacimiento.text.toString().trim().isNotEmpty()){

                                                var nacimiento = edtNacimiento.text.toString()
                                                var values = StudentsEntity(-1,nombreAlta,ApAlta,AmAlta,generoposition,nivelAcademico,escuelaposition,phone,correo,nacimiento)
                                                var id = studentsDb.studentAdd(values)
                                                Log.d("UDELP","El elemento guardado es $id $AmAlta")
                                                edtNombreAlta.text.clear()
                                                edtApellidoPAlta.text.clear()
                                                edtApellidoMAlta.text.clear()
                                                spnGenero.setSelection(0)
                                                rdgNivelAcademico.clearCheck()
                                                spnEscuelaProcedencia.setSelection(0)
                                                edtTelefonoAlta.text.clear()
                                                edtCorreoAlta.text.clear()
                                                edtNacimiento.setText("Fecha de Nacimiento")
                                                Toast.makeText(this@AltaEstudiante,"Registrado \uD83D\uDE1C\uD83E\uDD13",Toast.LENGTH_LONG).show()
                                            }else{
                                                Toast.makeText(this@AltaEstudiante,"Por favor selecciona fecha de nacimiento",Toast.LENGTH_LONG).show()

                                            }
                                        }else{
                                            Toast.makeText(this@AltaEstudiante,"Por favor ingresa el correo",Toast.LENGTH_LONG).show()
                                        }
                                    }else{
                                        Toast.makeText(this@AltaEstudiante,"Por favor ingresa el telefono",Toast.LENGTH_LONG).show()
                                    }
                                }else{
                                    Toast.makeText(this@AltaEstudiante,"Por favor selecciona una escuela",Toast.LENGTH_LONG).show()
                                }

                            }else{
                                Toast.makeText(this@AltaEstudiante,"Por favor ingresa el nivel Academico",Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Toast.makeText(this@AltaEstudiante,"Por favor ingresa el genero",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@AltaEstudiante,"Por favor ingresa el apellido materno",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@AltaEstudiante,"Por favor ingresa el apellido paterno",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@AltaEstudiante,"Por favor ingresa el nombre",Toast.LENGTH_LONG).show()
            }
        }
        //val textView     = findViewById<TextView>(R.id.dateTv)





    }

}
