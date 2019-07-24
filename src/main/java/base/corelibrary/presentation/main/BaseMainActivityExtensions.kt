package base.corelibrary.presentation.main

import androidx.core.view.isGone
import arrow.core.Try
import arrow.core.orNull
import com.google.android.material.badge.BadgeDrawable

fun BaseMainActivity.toggleLoading(isLoading: Boolean) {
    binding.progressBar.isGone = !isLoading
}

fun BaseMainActivity.bottomNavBadge(menuItemId: Int): BadgeDrawable {
    return binding.bottomNav.getOrCreateBadge(menuItemId).apply { isVisible = true }
}

fun BaseMainActivity.setBottomNavBadgeIntNoZero(menuItemId: Int, number: Int): BadgeDrawable? {
    return Try {
        if (number > 0) bottomNavBadge(menuItemId).apply { this.number = number }
        else null.apply { binding.bottomNav.removeBadge(menuItemId) }
    }.orNull()
}