package base.corelibrary.presentation.main

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.luminance
import arrow.core.Try
import arrow.core.Tuple2
import base.corelibrary.R
import base.corelibrary.data.entities.User
import base.corelibrary.domain.extensions.defaultHeader
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import com.github.icarohs7.unoxcore.extensions.capitalizeWords
import com.mikepenz.materialdrawer.Drawer
import splitties.resources.color

open class BaseMainDrawerConfig(private val builder: DrawerBuilderKt.(BaseMainActivity) -> Unit = {}) {
    fun setup(activity: BaseMainActivity): Drawer = with(activity) {
        val navDrawer = drawer {
            defaultSettings(this)
            builder(this, activity)
            setupDrawer(this)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Try { navDrawer.setSelection(destination.id.toLong(), false) }
        }

        return navDrawer
    }


    open fun BaseMainActivity.defaultSettings(builder: DrawerBuilderKt): Unit = with(builder) {
        val (name, email) = Tuple2(User.name, User.email)
        if (name.isNotBlank() || email.isNotBlank()) accountHeader {
            selectionListEnabled = false
            onlyMainProfileImageVisible = true
            background = R.drawable.bg_drawer_header

            val luminance = ColorUtils.calculateLuminance(color(R.color.colorPrimaryDark))
            textColor = when {
                luminance < 0.5 -> Color.WHITE.toLong()
                else -> Color.BLACK.toLong()
            }

            profile(name.capitalizeWords(), email.ifBlank { null }) {
                icon = R.drawable.ic_default_profile_128dp
                val userPicture = User.extraProperties["picture"]
                if (userPicture != null && "$userPicture".isNotBlank())
                    iconUrl = "$userPicture"
            }
        } else {
            defaultHeader()
        }

        translucentStatusBar = false
        onItemClick { _, _, item ->
            drawer?.closeDrawer()
            onMenuItemSelect(item.identifier.toInt())
            false
        }
    }

    open fun BaseMainActivity.setupDrawer(builder: DrawerBuilderKt) {}
}