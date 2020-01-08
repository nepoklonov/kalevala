package kalevala.common.models.participants

import kalevala.common.*
import kotlinx.serialization.Serializable

@Serializable
data class EthnoTourParticipant(
    @SaveToTable
    @Display(3, "Номинация", displayType = DisplayType.Select, validation = Validation.Select)
    val nomination: Nomination,

    @SaveToTable
    @Display(4, "Название организации/проекта")
    val organization: String,

    @Display(5, "Данные о руководителе организации/кураторе проекта:",
        displayType = DisplayType.Label, validation = Validation.Any)
    val supervisor: Unit,

    @SaveToTable
    @Display(107, "Отчество", width = 30)
    var patronymic: String,

    @SaveToTable
    @Display(210, "Название подаваемого на конкурс объекта/проекта/маршрута", width = 45)
    val title: String,

    @SaveToTable
    @Display(211, "Загрузите описание подаваемого на конкурс объекта/проекта/маршрута",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    val fileId: Int,

    override val id: Int, override val time: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String, override val city: String
) : Participant, NotOrganizer, OnlyName {
    enum class Nomination(override val title: String) : Titled {
        EthnoObject("«ЭТНОобъект» — лучший туристический объект"),
        EthnoProject("«ЭТНОпроект» — лучший авторский проект в сфере туриндустрии"),
        EthnoRoute("«ЭТНОмаршрут» — лучший туристический маршрут")
    }
}
