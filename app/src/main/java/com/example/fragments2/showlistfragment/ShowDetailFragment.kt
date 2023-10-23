package com.example.fragments2.showlistfragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.fragments2.EditFragment
import com.example.fragments2.R
import com.example.fragments2.data.User
import com.example.fragments2.databinding.FragmentShowDetailBinding
import com.example.fragments2.databinding.FragmentShowListBinding
import java.io.Serializable


class ShowDetailFragment : Fragment() {

    private var _binding: FragmentShowDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val user =arguments?.getSerializable(USER_ARG) as User
        val position = arguments?.getInt(POSITION_ARG)
        with(binding) {
            tvUserName.text = user.name
            tvSurname.text= user.surname
            tvPhoneNumber.text= user.phoneNumber
            if (user.photo.isNotBlank()) {
            Glide.with(ivAvatar.context)
                .load(user.photo)
                .circleCrop()
                .placeholder(R.drawable.baseline_person_pin_24).into(ivAvatar)
              } else { ivAvatar.setImageResource(R.drawable.baseline_person_pin_24)}

            btnEdit.setOnClickListener {
                 position?.let {
                     parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentsContainer, EditFragment.newInstance(user, it))
                .addToBackStack(null)
                .commit()
                 }

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val USER_ARG = "USER_ARG"
        const val POSITION_ARG = "POSITION_ARG"

        @JvmStatic
        fun newInstance(user: User, position: Int) =
            ShowDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER_ARG, user)
                    putInt(POSITION_ARG, position)
                }
            }
    }


}