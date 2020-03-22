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
        when(student.previousSchool){
            1->{
                txvEscuelaDetalle.setText("Reims")
            }
            2->{
                txvEscuelaDetalle.setText("Escuela Nacional Preparatoria")
            }
            3->{
                txvEscuelaDetalle.setText("La salle")
            }
            4->{
                txvEscuelaDetalle.setText("UVM")
            }
            5->{
                txvEscuelaDetalle.setText("Tecnologico de Monterrey")
            }
            6->{
                txvEscuelaDetalle.setText("Tecnologico de Monterrey")
            }
            6->{
                txvEscuelaDetalle.setText("Unitec")
            }
            7->{
                txvEscuelaDetalle.setText("Otra")
            }

        }

        txvTelStudent.setText(student.phone)
        txvCorreoStudent.setText(student.email)
        txvNacimientoStudent.setText(student.birthday)


    }
}
