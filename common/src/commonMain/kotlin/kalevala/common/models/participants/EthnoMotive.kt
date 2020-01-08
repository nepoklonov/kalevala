package kalevala.common.models.participants

import kalevala.common.*
import kotlinx.serialization.Serializable

@Serializable
data class EthnoMotiveParticipant(
    @SaveToTable
    @Display(3, "Номинация", displayType = DisplayType.Select, validation = Validation.Select)
    val nomination: Nomination,

    @SaveToTable
    @Display(106, "Отчество", width = 30)
    @Belongs(OwnType.BySelect, ["hasGroup", "0"])
    var patronymic: String,

    @SaveToTable
    @Display(107, "Возраст", validation = Validation.Number, width = 45)
    @Belongs(OwnType.BySelect, ["hasGroup", "0"])
    val age: String,

    @SaveToTable
    @Display(151, "Образовательное учреждение (если есть)", width = 45, validation = Validation.Any)
    val organization: String,

    @SaveToTable
    @Display(211, "Название работы", width = 45)
    val title: String,

    @SaveToTable
    @Display(212, "Загрузите работу",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    val imageFileId: Int,

    override val id: Int, override val time: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String, override val city: String, override val hasGroup: HowMuch, override val group: String

) : Participant, NotOrganizer, NameOrGroup {

    enum class Nomination(override val title: String) : Titled {
        Suits("Дизайн костюма и аксессуаров»"),
        Graphic("Графический дизайн"),
        ArtsAndCrafts("Декоративно-прикладное искусство")
    }
}
