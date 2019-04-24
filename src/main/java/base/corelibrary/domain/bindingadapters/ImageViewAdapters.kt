package base.corelibrary.domain.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.icarohs7.unoxandroidarch.extensions.load
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy

@BindingAdapter("app:url_src")
fun ImageView.loadUrlImage(path: String) {
    load(path) {
        memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
    }
}