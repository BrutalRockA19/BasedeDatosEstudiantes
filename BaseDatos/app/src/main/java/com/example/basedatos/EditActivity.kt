package com.example.basedatos

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsEntity
import com.example.basedatos.util.validation
//import com.example.basedatos.ListEdit.miAdaptador
import kotlinx.android.synthetic.main.activity_alta_estudiante.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {
    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val id = intent.extras?.getString("ID")
        //------------------------------DatePicker-------------------------
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var idEdit =-1
        edtNacimientoEdicion.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    val mes = (monthOfYear + 1)
                    edtNacimientoEdicion.setText("" + dayOfMonth + "-" + mes.toString() + "-" + year)
                },
                year,
                month,
                day
            )
            dpd.show()

        }
        //-----------------------DatePicker----------------------
        var student = StudentsEntity()
        if (id != null) {
            student = studentsDb.studentsGetOne(id.toInt())
             idEdit = id.toInt()
        }

        txvCuentaEdit.setText("${student.id.toString()}")
        txvNombreStudentEdit.setText("${student.name.toString()} ${student.lastName.toString()} ${student.motherLastName.toString()}")
        when (student.gender) {
            1 -> {
                txvGeneroEdit.setText("Masculino")
            }
            2 -> {
                txvGeneroEdit.setText("Femenino")
            }

        }
        //----------------TEST
       // rdgNivelAcademicoEdicion.check(student.academicLevel)
        spnEscuelaProcedenciaEdicion.setSelection(student.previousSchool)
        edtTelefonoEdicion.setText(student.phone)
        edtCorreoEdicion.setText(student.email)
        edtNacimientoEdicion.setText(student.birthday)
        //----------------TEST
        btnEditar.setOnClickListener{
            val selectedNivelAcademico = rdgNivelAcademicoEdicion.checkedRadioButtonId
            if (selectedNivelAcademico != -1) {
                var nivelAcademico = 0
                when (selectedNivelAcademico) {
                    rdgSecundariaE.id -> {
                        nivelAcademico = 1
                    }
                    rdgPrepaE.id -> {
                        nivelAcademico = 2
                    }
                    rdgUniversidadE.id -> {
                        nivelAcademico = 3
                    }
                }



                val escuelaposition = spnEscuelaProcedenciaEdicion.selectedItemPosition

                if (escuelaposition > 0) {
                    if (edtTelefonoEdicion.text.toString().trim().isNotEmpty()) {
                        var phone = edtTelefonoEdicion.text.toString()
                        if ((edtCorreoEdicion.text.toString().trim().isNotEmpty()) and (validation.isEmailValid(
                                edtCorreoEdicion.text.toString().trim().toString()
                            ))
                        ) {
                            var correo = edtCorreoEdicion.text.toString()
                            //-------------------------------------------------
                            if (edtNacimientoEdicion.text.toString().trim().isNotEmpty()) {

                                var nacimiento = edtNacimientoEdicion.text.toString()
                                var values = StudentsEntity(
                                    idEdit,
                                    student.name,
                                    student.lastName,
                                    student.motherLastName,
                                    student.gender,
                                    nivelAcademico,
                                    escuelaposition,
                                    phone,
                                    correo,
                                    nacimiento
                                )
                                var id = studentsDb.studentEdit(values)
                                Log.d("UDELP", "El elemento guardado es $id")


                                studentsDb.studentsGetAllString()
//                        -----------------------------------------------

                                Toast.makeText(
                                    this@EditActivity,
                                    "Editado con exito \uD83D\uDE1C\uD83E\uDD13",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@EditActivity,
                                    "Por favor selecciona fecha de nacimiento",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            //-------------------------------------------------
                        } else {
                            Toast.makeText(
                                this@EditActivity,
                                "Por favor selecciona correo",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@EditActivity,
                            "Por favor ingresa un telefono",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@EditActivity,
                        "Por favor selecciona una escuela",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }else{
                Toast.makeText(
                    this@EditActivity,
                    "Por favor ingresa el nivel Academico",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        }
    }
