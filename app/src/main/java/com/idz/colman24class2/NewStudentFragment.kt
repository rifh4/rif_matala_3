package com.idz.colman24class2

<<<<<<< HEAD
import android.app.DatePickerDialog
import android.app.TimePickerDialog
=======
>>>>>>> 91e5cd40ecc7070be9853ece5bb7aab29b192f74
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student
import java.util.Calendar

class NewStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var birthDateAutoComplete: AutoCompleteTextView
    private lateinit var birthTimeAutoComplete: AutoCompleteTextView

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

=======
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class NewStudentFragment : Fragment() {

>>>>>>> 91e5cd40ecc7070be9853ece5bb7aab29b192f74
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

<<<<<<< HEAD
        nameEditText = view.findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = view.findViewById(R.id.add_student_activity_id_edit_text)
        phoneEditText = view.findViewById(R.id.editTextPhone)
        addressEditText = view.findViewById(R.id.editTextTextPostalAddress)
        birthDateAutoComplete = view.findViewById(R.id.editTextBirthDate)
        birthTimeAutoComplete = view.findViewById(R.id.editTextBirthTime)

        saveButton = view.findViewById(R.id.add_student_activity_save_button)
        cancelButton = view.findViewById(R.id.add_student_activity_cancel_button)

        birthDateAutoComplete.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(requireContext(),
                { _, year, month, dayOfMonth ->
                    val formattedDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
                    birthDateAutoComplete.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

        birthTimeAutoComplete.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = TimePickerDialog(requireContext(),
                { _, hourOfDay, minute ->
                    val formattedTime = "%02d:%02d".format(hourOfDay, minute)
                    birthTimeAutoComplete.setText(formattedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            dialog.show()
        }
=======
        val nameEditText = view.findViewById<EditText>(R.id.add_student_activity_name_edit_text)
        val idEditText = view.findViewById<EditText>(R.id.add_student_activity_id_edit_text)
        val phoneEditText = view.findViewById<EditText>(R.id.editTextPhone)
        val addressEditText = view.findViewById<EditText>(R.id.editTextTextPostalAddress)
        val birthDateEditText = view.findViewById<EditText>(R.id.editTextBirthDate)
        val birthTimeEditText = view.findViewById<EditText>(R.id.editTextBirthTime)
        val saveButton = view.findViewById<Button>(R.id.add_student_activity_save_button)
        val cancelButton = view.findViewById<Button>(R.id.add_student_activity_cancel_button)
        val messageTextView = view.findViewById<TextView>(R.id.add_student_activity_save_message_text_view)
>>>>>>> 91e5cd40ecc7070be9853ece5bb7aab29b192f74

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
<<<<<<< HEAD
            val birthDate = birthDateAutoComplete.text.toString().trim()
            val birthTime = birthTimeAutoComplete.text.toString().trim()


            val newStudent = Student(
                name = name,
                id = id,
                phone = phone,
                address = address,
                isChecked = false,
                birthDate = birthDate,
                birthTime = birthTime
            )

            Model.shared.students.add(newStudent)

            AlertDialog.Builder(requireContext())
                .setTitle("Success")
                .setMessage("Student added successfully.")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    requireActivity().onBackPressed()
                }
                .show()
        }

        cancelButton.setOnClickListener {
            requireActivity().onBackPressed()
=======
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
>>>>>>> 91e5cd40ecc7070be9853ece5bb7aab29b192f74
        }

        return view
    }
}
