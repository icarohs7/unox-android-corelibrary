package base.corelibrary.domain.extensions

import base.corelibrary.databinding.NavHeaderBinding
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.draweritems.badgeable.PrimaryDrawerItemKt
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

var PrimaryDrawerItemKt.id: Int
    get() = this.identifier.toInt()
    set(value) {
        identifier = value.toLong()
    }

operator fun Drawer.get(itemId: Int): IDrawerItem<*, *>? {
    return getDrawerItem(itemId.toLong())
}

fun Drawer.updateBadge(itemId: Int, badgeText: String) {
    updateBadge(itemId.toLong(), StringHolder(badgeText))
}

fun DrawerBuilderKt.defaultHeader() {
    headerView = NavHeaderBinding.inflate(activity.layoutInflater).root
}