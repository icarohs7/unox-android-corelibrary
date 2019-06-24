package base.corelibrary.presentation.fragments

import base.corelibrary.R
import base.corelibrary.databinding.FragmentSwipeRecyclerBinding
import com.github.icarohs7.unoxandroidarch.presentation.fragments.BaseBindingFragment

abstract class BaseSwipeRecyclerFragment : BaseBindingFragment<FragmentSwipeRecyclerBinding>() {
    override fun getLayout(): Int {
        return R.layout.fragment_swipe_recycler
    }
}