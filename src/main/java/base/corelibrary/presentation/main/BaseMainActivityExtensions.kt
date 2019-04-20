package base.corelibrary.presentation.main

import android.view.View
import androidx.core.view.isGone
import arrow.core.Try
import base.corelibrary.domain.extensions.viewAnimate
import com.github.florent37.viewanimator.AnimationBuilder
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import timber.log.Timber

fun BaseMainActivity.toggleLoading(isLoading: Boolean) {
    binding.progressBar.isGone = !isLoading
}

fun BaseMainActivity.addBadgeToBottomNavIcon(iconIndex: Int, badgeView: View) {
    Try {
        val menuView = binding.bottomNav.getChildAt(0) as BottomNavigationMenuView
        val itemView = menuView.getChildAt(iconIndex) as BottomNavigationItemView
        itemView.addView(badgeView)
    }.fold(Timber::e) {}
}

fun BaseMainActivity.Companion.hideToolbar(animate: Boolean = false): Unit = this {
    binding.layoutToolbar.root.animateToggleVisibility(false, animate) {
        dp().translationY(-500f)
    }
}

fun BaseMainActivity.Companion.showToolbar(animate: Boolean = false): Unit = this {
    binding.layoutToolbar.root.animateToggleVisibility(true, animate) {
        dp().translationY(0f)
    }
}

fun BaseMainActivity.Companion.hideBottomBar(animate: Boolean = false): Unit = this {
    binding.bottomNav.animateToggleVisibility(false, animate) {
        dp().translationY(500f)
    }
}

fun BaseMainActivity.Companion.showBottomBar(animate: Boolean = false): Unit = this {
    binding.bottomNav.animateToggleVisibility(true, animate) {
        dp().translationY(0f)
    }
}

private fun View.animateToggleVisibility(isVisible: Boolean, animate: Boolean, block: AnimationBuilder.() -> Unit) {
    val animation = viewAnimate()
    block(animation)

    when {
        animate -> {
            animation
                    .let { if (isVisible) it.onStart { isGone = false } else it.onStop { isGone = true } }
                    .duration(500)
                    .start()
        }
        else -> isGone = !isVisible
    }
}