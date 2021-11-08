package com.gmail.xz77

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.gmail.xz77.ui.ContactDetailsFragment
import com.gmail.xz77.ui.ContactListFragment
import java.lang.ref.WeakReference

class ContactService : Service() {

    private val binder = ContactBinder()


    fun getContacts(contactListFragment: WeakReference<ContactListFragment>) {
        Thread {
            contactListFragment.get()?.setContactData(contacts)
        }.start()
    }

    fun getContactDetails(contactDetailsFragment: WeakReference <ContactDetailsFragment>, id: Int) {
        Thread {
            contactDetailsFragment.get()?.setContactDetailsData(contacts[id])
        }.start()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }

}