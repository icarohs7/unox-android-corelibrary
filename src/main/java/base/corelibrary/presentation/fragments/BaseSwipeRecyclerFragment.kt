package base.corelibrary.presentation.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import base.corelibrary.R
import base.corelibrary.databinding.FragmentSwipeRecyclerBinding
import base.corelibrary.presentation._baseclasses.BaseBindingFragment

abstract class BaseSwipeRecyclerFragment : BaseBindingFragment<FragmentSwipeRecyclerBinding>() {
    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.stateView.hideStates()
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    fun displayState(stateTag: String) {
        binding.stateView.displayState(stateTag)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_swipe_recycler
    }
}