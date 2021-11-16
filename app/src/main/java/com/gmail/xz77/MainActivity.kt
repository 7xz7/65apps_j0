package com.gmail.xz77

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.gmail.xz77.databinding.ActivityMainBinding
import com.gmail.xz77.ui.ContactDetailsFragment
import com.gmail.xz77.ui.ContactListFragment

class MainActivity : AppCompatActivity(), ContactInterface, IContactService {
    private lateinit var binding: ActivityMainBinding
    private var mService: ContactService? = null
    private var isServiceStarted: Boolean = false
    private var isCreateActivity = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as ContactService.ContactBinder
            mService = binder.getService()
            isServiceStarted = true
            if(isCreateActivity) {
                onContactListRequested()
            }
        }
        override fun onServiceDisconnected(className: ComponentName?) {
            isServiceStarted = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isCreateActivity = savedInstanceState  == null

        Intent(this, ContactService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        if (isServiceStarted){
            isServiceStarted = false
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }

    private fun onContactListRequested() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, ContactListFragment())
            .commit()
    }

    override fun onContactDetailRequested(id: String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ContactDetailsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun getService() = mService
}