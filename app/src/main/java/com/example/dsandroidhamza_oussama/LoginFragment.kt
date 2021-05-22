package com.example.dsandroidhamza_oussama;

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dsandroidhamza_oussama.databinding.FragmentLoginBinding
import com.example.dsandroidhamza_oussama.models.User

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater , container , false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            myListener.loginUser(User(  email =  binding.etEmail.text.toString(), password = binding.etPassword.text.toString() ))
        }
    }

    lateinit var myListener: MyListener
    fun setOnButtonClickedListener(myListener : MyListener) {
        this.myListener = myListener
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}