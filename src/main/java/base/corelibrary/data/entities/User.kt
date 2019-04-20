package base.corelibrary.data.entities

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref

object User : KotprefModel() {
    var id: Int by intPref(0)
    var pin: String by stringPref("")
    var hash: String by stringPref("")
    var name: String by stringPref("")
    var email: String by stringPref("")
    var isRegistered: Boolean by booleanPref(false)
    var extraProperties: Map<String, Any?> by gsonPref(emptyMap())
}