package com.example.basedatos.Data

data class StudentsEntity (
    var id:Int,
    var name:String,
    var lastName:String,
    var motherLastName:String,
    var gender:Int,
    var academicLevel:Int,
    var previousSchool:Int,
    var phone:String,
    var email:String,
    var birthday:String

){constructor() : this(0,"","","",0,0,0,"","","") }

