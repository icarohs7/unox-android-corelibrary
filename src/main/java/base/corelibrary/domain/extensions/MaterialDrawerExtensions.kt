package base.corelibrary.domain.extensions

import base.corelibrary.databinding.NavHeaderBinding
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.draweritems.badgeable.PrimaryDrawerItemKt

var PrimaryDrawerItemKt.id: Int
    get() = this.identifier.toInt()
    set(value) {
        identifier = value.toLong()
    }

fun DrawerBuilderKt.defaultHeader() {
    headerView = NavHeaderBinding.inflate(activity.layoutInflater).root
}