package com.example.dsandroidhamza_oussama;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dsandroidhamza_oussama.databinding.FragmentRegisterBinding
import com.example.dsandroidhamza_oussama.models.User

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater , container , false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            myListener.registerNewUser(User(name = binding.etName.text.toString(), email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString()) , binding.etRepassword.text.toString())
        }
    }

    lateinit var myListener: MyListener
    fun setOnButtonClickedListener(myListener : MyListener) {
        this.myListener = myListener
    }
}