package com.example.basedatos.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class StudentsDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    fun  studentAdd(student:StudentsEntity): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(MOTHERSLASTNAME,student.motherLastName)
        values.put(GENDER,student.gender)
        values.put(ACADEMICLEVEL,student.academicLevel)
        values.put(PREVIOUSSCHOOL,student.previousSchool)
        values.put(PHONE,student.phone)
        values.put(EMAIL,student.email)
        values.put(BIRTHDAY,student.birthday)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_STUDENTS,null,values)
    }

    fun  studentEdit(student:StudentsEntity): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(MOTHERSLASTNAME,student.motherLastName)
        values.put(GENDER,student.gender)
        values.put(ACADEMICLEVEL,student.academicLevel)
        values.put(PREVIOUSSCHOOL,student.previousSchool)
        values.put(PHONE,student.phone)
        values.put(EMAIL,student.email)
        values.put(BIRTHDAY,student.birthday)
        var selection = "Id=?"
        var args = arrayOf(student.id.toString())

        return sqliteDataBase.update(ConnectionDb.TABLE_NAME_STUDENTS,values,selection,args)

    }

    fun  studentDelete(idStudent:Int): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        sqliteDataBase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)
        return sqliteDataBase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)

    }

    fun studentsGetAll(){

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME,MOTHERSLASTNAME, GENDER, ACADEMICLEVEL,
            PREVIOUSSCHOOL, PHONE, EMAIL, BIRTHDAY)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {

                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} ${cursor.getString(4)}  ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")

            }while (cursor.moveToNext())

        }


    }

    fun studentsGetAllString(): Array<String>{
        listString.clear()
        listStringIDS.clear()
        //Agregando de una vez el listString
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                listString.add("${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(2)} ${cursor.getString(4)}")
                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
                listStringIDS.add("${cursor.getInt(0)} ")
            }while (cursor.moveToNext())

        }
        val elems = listString
        return elems.toTypedArray()

    }


    fun studentsGetOne(idStudent:Int):StudentsEntity{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME,MOTHERSLASTNAME, GENDER, ACADEMICLEVEL,
            PREVIOUSSCHOOL, PHONE, EMAIL, BIRTHDAY)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,selection,args,null,null,null)
        var student = StudentsEntity()
        if(cursor.moveToFirst()){
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var lastname = cursor.getString(2)
            var mothersLastname = cursor.getString(3)
            var gender = cursor.getInt(4)
            var phone = cursor.getString(7)
            var correo = cursor.getString(8)
            var nacimiento = cursor.getString(9)
            student = StudentsEntity(cursor.getInt(0),"${name}","$lastname",mothersLastname,gender,cursor.getInt(5),cursor.getInt(6),phone,correo,nacimiento)

            Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}  ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")
        }

        return student
    }

    companion object {
        const val ID = "id"
        const val NAME = "Name"
        const val LASTNAME = "Lastname"
        const val MOTHERSLASTNAME = "MothersLastName"
        const val GENDER = "Gender"
        const val ACADEMICLEVEL = "AcademicLevel"
        const val PREVIOUSSCHOOL ="PreviousSchool"
        const val PHONE ="Phone"
        const val EMAIL ="Email"
        const val BIRTHDAY = "Birthday"
        //Lista para el list view
        private  val listString = arrayListOf<String>()
        //lista ids
        val listStringIDS = arrayListOf<String>()
    }
}