package base.corelibrary.domain.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

val Fragment.viewScope: LifecycleCoroutineScope get() = viewLifecycleOwner.lifecycleScope