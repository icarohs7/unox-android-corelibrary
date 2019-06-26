package base.corelibrary.presentation.fragments

import base.corelibrary.R
import base.corelibrary.databinding.FragmentExtFabRecyclerBinding
import base.corelibrary.databinding.FragmentSwipeRecyclerBinding
import com.github.icarohs7.unoxandroidarch.presentation.fragments.BaseBindingFragment

abstract class BaseExtFabRecyclerFragment : BaseBindingFragment<FragmentExtFabRecyclerBinding>() {
    override fun getLayout(): Int {
        return R.layout.fragment_ext_fab_recycler
    }
}