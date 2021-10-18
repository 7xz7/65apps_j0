package com.gmail.xz77

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.xz77.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ContactInterface {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            onContactListRequested()
        }
    }

    private fun onContactListRequested() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container,ContactListFragment())
            .commit()
    }

    override fun onContactDetailRequested(id: String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,ContactDetailsFragment())
            .addToBackStack(null)
            .commit()
    }
}