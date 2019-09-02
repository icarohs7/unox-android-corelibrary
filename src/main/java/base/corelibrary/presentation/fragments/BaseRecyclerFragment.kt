package base.corelibrary.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import base.corelibrary.databinding.FragmentBaseRecyclerBinding
import base.corelibrary.presentation._baseclasses.BaseBindingFragment
import com.umutbey.stateviews.StateView

/**
 * Extension of [BaseBindingFragment] using a layout
 * containing a fullscreen [StateView] with a [RecyclerView]
 * inside
 */
abstract class BaseRecyclerFragment : BaseBindingFragment<FragmentBaseRecyclerBinding>() {
    override fun onBindingCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        super.onBindingCreated(inflater, container, savedInstanceState)
        binding.stateView.hideStates()
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Display a state with the given tag
     * on the state view of the layout
     */
    fun displayState(stateTag: String) {
        binding.stateView.displayState(stateTag)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_base_recycler
    }
}