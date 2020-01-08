package kalevala.common.models

import kalevala.common.Display
import kalevala.common.models.participants.AnyForm
import kotlinx.serialization.Serializable

@Serializable
data class AdminLogin(
    @Display(101, "Секретные слова")
    val password: String,

    override val id: Int, override val time: String, override val submit: Unit
) : AnyForm