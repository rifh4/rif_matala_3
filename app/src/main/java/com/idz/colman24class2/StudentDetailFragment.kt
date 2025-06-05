package com.idz.colman24class2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class StudentDetailFragment : Fragment() {

    private lateinit var student: Student
    private var studentIndex: Int = -1

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var birthTimeEditText: EditText
    private lateinit var checkedBox: CheckBox
    private lateinit var editButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)

        studentIndex = arguments?.getInt("student_index") ?: -1
        if (studentIndex == -1) {
            Toast.makeText(requireContext(), "Invalid student", Toast.LENGTH_SHORT).show()
            activity?.onBackPressedDispatcher?.onBackPressed()
            return view
        }

        student = Model.shared.students[studentIndex]

        nameEditText = view.findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = view.findViewById(R.id.add_student_activity_id_edit_text)
        phoneEditText = view.findViewById(R.id.editTextPhone)
        addressEditText = view.findViewById(R.id.editTextTextPostalAddress)
        birthDateEditText = view.findViewById(R.id.editTextBirthDate)
        birthTimeEditText = view.findViewById(R.id.editTextBirthTime)
        checkedBox = view.findViewById(R.id.student_details_checked)
        editButton = view.findViewById(R.id.buttonEditStudent)

        loadStudentData()

        checkedBox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        editButton.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("student_index", studentIndex)
            }
            findNavController().navigate(R.id.action_studentDetailFragment_to_editStudentFragment, bundle)
        }

        return view
    }

    private fun loadStudentData() {
        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        birthDateEditText.setText(student.birthDate)
        birthTimeEditText.setText(student.birthTime)
        checkedBox.isChecked = student.isChecked

        val fields = listOf(
            nameEditText, idEditText, phoneEditText,
            addressEditText, birthDateEditText, birthTimeEditText
        )
        fields.forEach {
            it.isFocusable = false
            it.isClickable = false
            it.isCursorVisible = false
            it.background = null
        }
    }

    override fun onResume() {
        super.onResume()
        student = Model.shared.students[studentIndex]
        loadStudentData()
    }

}
