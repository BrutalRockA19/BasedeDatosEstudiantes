package com.example.basedatos

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsEntity
import com.example.basedatos.util.validation
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

        edtNacimientoEdicion.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    val mes = (monthOfYear + 1)
                    edtNacimiento.setText("" + dayOfMonth + "-" + mes.toString() + "-" + year)
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
                    rdbPrepa.id -> {
                        nivelAcademico = 1
                    }
                    rdbSecundaria.id -> {
                        nivelAcademico = 2
                    }
                    rdbUniversidad.id -> {
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
                            if (edtNacimiento.text.toString().trim().isNotEmpty()) {

                                var nacimiento = edtNacimiento.text.toString()
                                var values = StudentsEntity(
                                    -1,
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
                                var id = studentsDb.studentAdd(values)
                                Log.d("UDELP", "El elemento guardado es $id")
                                /*edtNombreAlta.text.clear()
                            edtApellidoPAlta.text.clear()
                            edtApellidoMAlta.text.clear()
                            spnGenero.setSelection(0)
                            rdgNivelAcademico.clearCheck()
                            spnEscuelaProcedencia.setSelection(0)
                            edtTelefonoAlta.text.clear()
                            edtCorreoAlta.text.clear()*/
                                edtNacimiento.setText("Fecha de Nacimiento")
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
