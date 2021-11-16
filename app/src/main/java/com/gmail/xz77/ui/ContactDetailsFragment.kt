package com.gmail.xz77.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.xz77.ContactModel
import com.gmail.xz77.ContactService
import com.gmail.xz77.IContactService
import com.gmail.xz77.R
import com.gmail.xz77.databinding.FragmentContactDetailsBinding
import java.lang.ref.WeakReference

class ContactDetailsFragment : Fragment() {
    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var service: ContactService? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        service = (context as? IContactService)?.getService()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)
            ?.supportActionBar
            ?.setTitle(R.string.title_contact_details)

        service?.getContactDetails(WeakReference(this),0)
    }

    fun setContactDetailsData(data: ContactModel) {
        requireActivity().runOnUiThread {
            binding.root.apply {
                binding.contactDetailsName.text = data.contactName
                binding.contactDetailsPhone1.text = data.firstPhoneNumber
                binding.contactDetailsPhone2.text = data.secondPhoneNumber
                binding.contactDetailsEmail1.text = data.firstEmail
                binding.contactDetailsEmail2.text = data.secondEmail
                binding.contactDetailsDescription.text = data.contactDescription
                binding.contactDetailsPhoto.setImageResource(data.photoResId)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        service = null
        super.onDetach()
    }

    companion object {
        private const val ARG_CONTACT_ID = "id"

        fun newInstance(id: String): ContactDetailsFragment {
            return ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CONTACT_ID, id)
                }
            }
        }
    }
}