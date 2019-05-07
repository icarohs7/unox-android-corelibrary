package base.corelibrary.domain.extensions

import base.corelibrary.databinding.NavHeaderBinding
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.draweritems.base.AbstractDrawerItemKt
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

var AbstractDrawerItemKt<*>.id: Int
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