package com.example.fragments2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.example.fragments2.data.User
import com.example.fragments2.databinding.FragmentEditBinding
import com.example.fragments2.databinding.FragmentShowDetailBinding
import com.example.fragments2.showlistfragment.ShowDetailFragment
import com.example.fragments2.showlistfragment.ShowListFragment


class EditFragment : Fragment() {

	private var _binding: FragmentEditBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentEditBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val user = arguments?.getSerializable(USER_ARG) as User
		val position = arguments?.getInt(POSITION_ARG)
		with(binding) {
			editName.setText(user.name)
			editSurname.setText(user.surname)
			editPhonenumber.setText(user.phoneNumber)
			if (user.photo.isNotBlank()) {
				Glide.with(ivAvatar.context)
					.load(user.photo)
					.circleCrop()
					.placeholder(R.drawable.baseline_person_pin_24).into(ivAvatar)
			} else {
				ivAvatar.setImageResource(R.drawable.baseline_person_pin_24)
			}




			btnSave.setOnClickListener {


				//val result = Bundle().apply {
				//position?.let { it1 -> putInt("bundle1", it1) }
				// putSerializable("bundle", user)
				//}
				//val result = position


				//  requireActivity().supportFragmentManager
				val newUser = User(
					id = user.id,
					name = editName.text.toString(),
					surname = editSurname.text.toString(),
					phoneNumber = editPhonenumber.text.toString(),
					photo = user.photo
				)
				setFragmentResult("rqst", bundleOf("bundle" to newUser, "bundle1" to position))
				parentFragmentManager.beginTransaction()
					.replace(R.id.fragmentsContainer, ShowListFragment())
					.commit()
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
			EditFragment().apply {
				arguments = Bundle().apply {
					putSerializable(USER_ARG, user)
					putInt(POSITION_ARG, position)
				}
			}
	}


}