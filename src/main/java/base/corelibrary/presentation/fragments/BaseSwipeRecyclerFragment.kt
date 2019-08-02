package base.corelibrary.presentation.fragments

import base.corelibrary.R
import base.corelibrary.databinding.FragmentSwipeRecyclerBinding
import base.corelibrary.presentation._baseclasses.BaseBindingFragment

abstract class BaseSwipeRecyclerFragment : BaseBindingFragment<FragmentSwipeRecyclerBinding>() {
    override fun getLayout(): Int {
        return R.layout.fragment_swipe_recycler
    }
}