package base.corelibrary.presentation.fragments

import base.corelibrary.R
import base.corelibrary.databinding.FragmentExtFabRecyclerBinding
import base.corelibrary.presentation._baseclasses.BaseBindingFragment

abstract class BaseExtFabRecyclerFragment : BaseBindingFragment<FragmentExtFabRecyclerBinding>() {
    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.stateView.hideStates()
    }

    fun displayState(stateTag: String) {
        binding.stateView.displayState(stateTag)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_ext_fab_recycler
    }
}