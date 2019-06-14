package base.corelibrary.domain.extensions

import base.corelibrary.R
import base.corelibrary.databinding.NavHeaderBinding
import co.zsmb.materialdrawerkt.builders.Builder
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.draweritems.badge
import co.zsmb.materialdrawerkt.draweritems.badgeable.BadgeableKt
import co.zsmb.materialdrawerkt.draweritems.badgeable.PrimaryDrawerItemKt
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.base.AbstractDrawerItemKt
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

var AbstractDrawerItemKt<*>.id: Int
    get() = this.identifier.toInt()
    set(value) {
        identifier = value.toLong()
    }

operator fun Drawer.get(itemId: Int): IDrawerItem<*, *>? {
    return getDrawerItem(itemId.toLong())
}

fun Drawer.updateBadge(itemId: Int, badgeText: String?) {
    val strHolder = if (badgeText == null) null else StringHolder(badgeText)
    updateBadge(itemId.toLong(), strHolder)
}

fun Drawer.updateIntBadgeNoZero(itemId: Int, number: Int) {
    if (number > 0) updateBadge(itemId, "$number")
    else updateBadge(itemId, null)
}

fun DrawerBuilderKt.defaultHeader() {
    headerView = NavHeaderBinding.inflate(activity.layoutInflater).root
}

fun BadgeableKt.defaultNumberBadge(initialValue: Number) {
    badge("$initialValue") {
        colorRes = R.color.colorError
        textColorRes = R.color.white
    }
}

fun Builder.disconnectButton(extraBuilder: PrimaryDrawerItemKt.() -> Unit = {}): PrimaryDrawerItem {
    return primaryItem(R.string.desconectar) {
        id = R.id.menu_logout
        iicon = GoogleMaterial.Icon.gmd_exit_to_app
        selectable = false
        extraBuilder()
    }
}