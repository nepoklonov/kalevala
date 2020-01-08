package kalevala.common.models
//
import kalevala.common.*
import kotlinx.serialization.Serializable

@Serializable
data class InputField(
    val order: Int,
    val name: String,
    val title: String,
    val validation: Validation,
    val type: DisplayType,
    val ownType: OwnType = OwnType.None,
    val ownParams: List<String> = listOf(),
    val width: Int = 100,
    val options: List<String> = listOf()
) {
    var value: String = if (type == DisplayType.CheckBox) "false" else ""
}
