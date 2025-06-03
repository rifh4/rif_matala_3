package com.idz.colman24class2

import com.idz.colman24class2.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

//please see the report for the existence of this file
