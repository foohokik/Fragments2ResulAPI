package com.example.fragments2.showlistfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragments2.R
import com.example.fragments2.data.User
import com.example.fragments2.databinding.FragmentShowListBinding
import com.example.fragments2.showlistfragment.adapter.UserAdapter


class ShowListFragment : Fragment() {

    private var _binding: FragmentShowListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: UserAdapter

    private val viewModel: ShowListFragmentViewModel by viewModels() {ShowListFragmentViewModel.Factory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("rqst") { key, bundle ->
            Log.d("MyLog", "before    "+bundle)
            val user = bundle.getSerializable("bundle") as? User

            Log.d("MyLog", "afterserializable"+user)

            val position = bundle.getInt("bundle1")

            Log.d("MyLog", "aftergetInt     "+position)
            user?.let { viewModel.updateValue(it, position) }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        observe()
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(viewModel)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

    }

    private fun observe() {

        val nameObserver = Observer<List<User>> { items ->
            adapter.setItems(items)
        }

        val onClickObserver = Observer<Pair<User, Int>> {value ->
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentsContainer, ShowDetailFragment.newInstance(value.first, value.second))
                .addToBackStack(null)
                .commit()

        }

        viewModel.usersLiveData.observe(viewLifecycleOwner, nameObserver)
        viewModel.onClickLiveData.observe(viewLifecycleOwner, onClickObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}