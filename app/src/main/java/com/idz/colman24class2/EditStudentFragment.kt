package com.idz.colman24class2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class EditStudentFragment : Fragment() {

    private lateinit var student: Student
    private var studentIndex: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        studentIndex = arguments?.getInt("student_index") ?: -1
        if (studentIndex == -1) {
            findNavController().popBackStack()
            return view
        }

        student = Model.shared.students[studentIndex]

        val nameEditText = view.findViewById<EditText>(R.id.EditStudentName)
        val idEditText = view.findViewById<EditText>(R.id.EditStudentID)
        val phoneEditText = view.findViewById<EditText>(R.id.EditStudentPhone)
        val addressEditText = view.findViewById<EditText>(R.id.EditStudentAddress)
        val birthDateEditText = view.findViewById<EditText>(R.id.EditStudentBirthDate)
        val birthTimeEditText = view.findViewById<EditText>(R.id.EditStudentBirthTime)

        val saveButton = view.findViewById<Button>(R.id.EditStudentSaveButton)
        val cancelButton = view.findViewById<Button>(R.id.EditStudentCancelButton)
        val deleteButton = view.findViewById<Button>(R.id.EditStudentDeleteButton)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        birthDateEditText.setText(student.birthDate)
        birthTimeEditText.setText(student.birthTime)

        saveButton.setOnClickListener {
            student.name = nameEditText.text.toString().trim()
            student.id = idEditText.text.toString().trim()
            student.phone = phoneEditText.text.toString().trim()
            student.address = addressEditText.text.toString().trim()
            student.birthDate = birthDateEditText.text.toString().trim()
            student.birthTime = birthTimeEditText.text.toString().trim()

            findNavController().popBackStack()
        }

        deleteButton.setOnClickListener {
            Model.shared.students.removeAt(studentIndex)
            findNavController().navigate(R.id.studentsListFragment)
        }

        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}
