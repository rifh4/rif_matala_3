package com.idz.colman24class2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student
import java.util.Calendar

class EditStudentFragment : Fragment() {

    private lateinit var student: Student
    private var studentIndex: Int = -1

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var birthDateAutoComplete: AutoCompleteTextView
    private lateinit var birthTimeAutoComplete: AutoCompleteTextView

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var deleteButton: Button

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

        nameEditText = view.findViewById(R.id.EditStudentName)
        idEditText = view.findViewById(R.id.EditStudentID)
        phoneEditText = view.findViewById(R.id.EditStudentPhone)
        addressEditText = view.findViewById(R.id.EditStudentAddress)
        birthDateAutoComplete = view.findViewById(R.id.EditStudentBirthDate)
        birthTimeAutoComplete = view.findViewById(R.id.EditStudentBirthTime)

        saveButton = view.findViewById(R.id.EditStudentSaveButton)
        cancelButton = view.findViewById(R.id.EditStudentCancelButton)
        deleteButton = view.findViewById(R.id.EditStudentDeleteButton)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        birthDateAutoComplete.setText(student.birthDate)
        birthTimeAutoComplete.setText(student.birthTime)

        birthDateAutoComplete.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, y, m, d ->
                val formattedDate = String.format("%04d-%02d-%02d", y, m + 1, d)
                birthDateAutoComplete.setText(formattedDate)
            }, year, month, day).show()
        }

        birthTimeAutoComplete.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, h, m ->
                val formattedTime = String.format("%02d:%02d", h, m)
                birthTimeAutoComplete.setText(formattedTime)
            }, hour, minute, true).show()
        }

        saveButton.setOnClickListener {
            student.name = nameEditText.text.toString().trim()
            student.id = idEditText.text.toString().trim()
            student.phone = phoneEditText.text.toString().trim()
            student.address = addressEditText.text.toString().trim()
            student.birthDate = birthDateAutoComplete.text.toString().trim()
            student.birthTime = birthTimeAutoComplete.text.toString().trim()

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
