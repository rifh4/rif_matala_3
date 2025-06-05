package com.idz.colman24class2.model

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false,
    var birthDate: String = "",
    var birthTime: String = ""
)


