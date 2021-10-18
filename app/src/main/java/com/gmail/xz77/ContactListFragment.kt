package com.gmail.xz77

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.gmail.xz77.databinding.FragmentContactListBinding


class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding get() = _binding!!
    private var contactInterface:ContactInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactInterface = context as? ContactInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)
            ?.supportActionBar
            ?.setTitle(R.string.title_contact_list)
        binding.contactList.setOnClickListener { contactInterface?.onContactDetailRequested("1") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        contactInterface = null
        super.onDetach()
    }

}