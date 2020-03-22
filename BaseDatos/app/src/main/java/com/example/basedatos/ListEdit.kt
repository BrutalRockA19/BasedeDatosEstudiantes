package com.example.basedatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.basedatos.Data.StudentsDb
import kotlinx.android.synthetic.main.activity_list_students.*

class ListEdit : AppCompatActivity() {
    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_edit)
        if( studentsDb.studentsGetAllString().size >0){

            val miAdaptador = ArrayAdapter<String> (this@ListEdit, android.R.layout.simple_list_item_1,studentsDb.studentsGetAllString())

            ltvMisEstudiantes.adapter=miAdaptador

            ltvMisEstudiantes.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)
                /*Toast.makeText(
                    this@SurveysActivity,
                    "$position $id $itemSeleccionado",
                    Toast.LENGTH_SHORT
                ).show()*/
                // var index= listStringIndex[position]
                val idStudent =  StudentsDb.listStringIDS[id.toInt()]
                Toast.makeText(
                    this@ListEdit,

                    "Seleccionaste el $idStudent ",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@ListEdit, EditActivity::class.java)
                intent.putExtra("ID", idStudent.trim())
                startActivity(intent)
            }

        }


    }
}
