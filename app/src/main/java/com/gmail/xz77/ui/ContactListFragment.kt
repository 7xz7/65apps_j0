package com.gmail.xz77.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.xz77.*
import com.gmail.xz77.databinding.FragmentContactListBinding
import java.lang.ref.WeakReference


class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding get() = _binding!!
    private var contactInterface: ContactInterface? = null
    private var service: ContactService? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactInterface = context as? ContactInterface
        service = (context as? IContactService)?.getService()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)
            ?.supportActionBar
            ?.setTitle(R.string.title_contact_list)

        service?.getContacts(WeakReference(this))
        binding.contactList.setOnClickListener { contactInterface?.onContactDetailRequested("0") }
    }

    fun setContactData(data: List<ContactModel>) {
        requireActivity().runOnUiThread {
            binding.contactList.apply {
                binding.contactName.text = data.first().contactName
                binding.contactPhone1.text = data.first().firstPhoneNumber
                binding.contactPhoto.setImageResource(data.first().photoResId)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        contactInterface = null
        service = null
        super.onDetach()
    }

}