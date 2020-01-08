package kalevala.common.models.participants

import kalevala.common.*
import kotlinx.serialization.Serializable


@Serializable
data class SunCountryParticipant(
    @SaveToTable
    @Display(3,"Номинация", displayType = DisplayType.Select, validation = Validation.Select)
    val nomination: Nomination,

    @SaveToTable
    @Display(105,"Возраст", validation = Validation.Number, width = 30)
    @Belongs(OwnType.BySelect, ["hasGroup", "0"])
    val age: Int,

    @SaveToTable
    @Display(151,"Образовательное учреждение (если есть)", validation = Validation.Any, width = 45)
    val organization: String,

    @SaveToTable
    @Display(211,"Название работы", width = 45)
    val title: String,

    @SaveToTable
    @Display(212,"Загрузите работу",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    @Belongs(OwnType.BySelect, ["nomination", "0"])
    val imageFileId: Int,

    @SaveToTable
    @Display(212,"Загрузите работу",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    @Belongs(OwnType.BySelect, ["nomination", "1"])
    val mediaFileId: Int,

    override val id: Int, override val time: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String, override val city: String, override val hasGroup: HowMuch, override val group: String
) : Participant, NameOrGroup, NotOrganizer {

    enum class Nomination(override val title: String): Titled {
        Pictures("Художественное творчество"),
        Other("Музыка, танец, театральное искусство и декламация")
    }
}
