package com.example.basedatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.extras?.getString("ID")

        var student = StudentsEntity()
        if (id != null) {
            student = studentsDb.studentsGetOne(id.toInt())
        }

        txvCuentaEstudiante.setText("${student.id.toString()}")
        txvNombreStudent.setText("${student.name.toString()} ${student.lastName.toString()} ${student.motherLastName.toString()}")
        when(student.gender){
            1->{
                txvGeneroStudent.setText("Masculino")
            }
            2->{
                txvGeneroStudent.setText("Femenino")
            }

        }
        when(student.academicLevel){
            1->{
                txvNivelEscolarStudent.setText("Secundaria")
            }
            2->{
                txvNivelEscolarStudent.setText("Preparatoria")
            }
            3->{
                txvNivelEscolarStudent.setText("Universidad")

            }
        }
        txvTelStudent.setText(student.phone)
        txvCorreoStudent.setText(student.email)
        txvNacimientoStudent.setText(student.birthday)


    }
}
