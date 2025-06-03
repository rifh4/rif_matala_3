package com.idz.colman24class2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class NewStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val nameEditText = view.findViewById<EditText>(R.id.add_student_activity_name_edit_text)
        val idEditText = view.findViewById<EditText>(R.id.add_student_activity_id_edit_text)
        val phoneEditText = view.findViewById<EditText>(R.id.editTextPhone)
        val addressEditText = view.findViewById<EditText>(R.id.editTextTextPostalAddress)
        val birthDateEditText = view.findViewById<EditText>(R.id.editTextBirthDate)
        val birthTimeEditText = view.findViewById<EditText>(R.id.editTextBirthTime)
        val saveButton = view.findViewById<Button>(R.id.add_student_activity_save_button)
        val cancelButton = view.findViewById<Button>(R.id.add_student_activity_cancel_button)
        val messageTextView = view.findViewById<TextView>(R.id.add_student_activity_save_message_text_view)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val birthDate = birthDateEditText.text.toString().trim()
            val birthTime = birthTimeEditText.text.toString().trim()

            if (name.isEmpty() || id.isEmpty()) {
                messageTextView.text = "Name and ID cannot be empty"
                return@setOnClickListener
            }

            Model.shared.students.add(Student(name, id, phone, address, false, birthDate, birthTime))

            messageTextView.text = "Saved $name ($id)"
            Toast.makeText(requireContext(), "Student saved!", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }

        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}
