package com.example.basedatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsEntity

class MainActivity : AppCompatActivity() {

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        //son pruebas
       var values = StudentsEntity(-1,"Andres","Chavarria","Chavez",1,2,2,"5513107596","andrew_f19@hotmail.com","1994/08/31")
        var id = studentsDb.studentAdd(values)
        Log.d("UDELP","El elemento guardado es $id")

               values = StudentsEntity(-1,"Angel","Estrada",0,"1989/11/16")
               id = studentsDb.studentAdd(values)
               Log.d("UDELP","El elemento guardado es $id")

               values = StudentsEntity(-1,"Charlene","Aleman",0,"1990/11/16")
               id = studentsDb.studentAdd(values)
               Log.d("UDELP","El elemento guardado es $id")

               studentsDb.studentsGetAll()

       */


        studentsDb.studentsGetAll()
/*
        var values = StudentsEntity(3,"Xochitl","Monfarte",0,"1986/11/16")
        studentsDb.studentEdit(values)
        //studentsDb.studentsGetOne(3)
        //studentsDb.studentDelete(2)
        //Log.d("UDELP","Despues de eliminar")

 */
        Log.d("UDELP","Despues de editar")
        studentsDb.studentsGetAll()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmAltaEstudiante->{
            Log.d("UdelP","registro")
            val intent = Intent(this@MainActivity,AltaEstudiante :: class.java)
            startActivity(intent)
        }
            R.id.itmMisEstudiante->{
                Log.d("UdelP","registro")
                val intent = Intent(this@MainActivity,ListStudentsActivity :: class.java)
                startActivity(intent)
            }
            R.id.itmEliminarEstudiante->{
                Log.d("UdelP","registro")
                val intent = Intent(this@MainActivity,ListDelete :: class.java)
                startActivity(intent)
            }
            R.id.itmEditarEstudiante->{
                Log.d("UdelP","registro")
                val intent = Intent(this@MainActivity,ListEdit :: class.java)
                startActivity(intent)
            }

        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

}
