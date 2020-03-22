package com.example.basedatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.basedatos.Data.StudentsDb
import kotlinx.android.synthetic.main.activity_list_students.*

class ListDelete : AppCompatActivity() {
    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_delete)

        if( studentsDb.studentsGetAllString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@ListDelete, android.R.layout.simple_list_item_1,studentsDb.studentsGetAllString())

            ltvMisEstudiantes.adapter=miAdaptador

            ltvMisEstudiantes.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)
                /*Toast.makeText(
                    this@SurveysActivity,
                    "$position $id $itemSeleccionado",
                    Toast.LENGTH_SHORT
                ).show()*/
                // var index= listStringIndex[position]
                var idStudent =  StudentsDb.listStringIDS[id.toInt()]
                Toast.makeText(
                    this@ListDelete,

                    "Seleccionaste el $idStudent ",
                    Toast.LENGTH_SHORT
                ).show()
                miDialogo("Eliminar a ${idStudent.trim().toString()}",idStudent.trim().toInt()).show()

            }

        }

    }

    private fun miDialogo(texto: String, id: Int): AlertDialog {
        val miAlerta = AlertDialog.Builder(this@ListDelete)
        miAlerta.setTitle("Estar seguro que deseas eliminar?").setMessage(texto)
        miAlerta.setPositiveButton("SI"){dialog,which ->
            studentsDb.studentDelete(id)
            Toast.makeText(this@ListDelete,"ok, Eliminado",Toast.LENGTH_SHORT).show()
            finish();
            startActivity(intent);
        }
        miAlerta.setNegativeButton("NO"){ dialog,which ->
            Toast.makeText(this@ListDelete,"Negativo carnal",Toast.LENGTH_SHORT).show()

        }
        return miAlerta.create()
    }



}
