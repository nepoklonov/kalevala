package kalevala.common.models.participants

import kalevala.common.*
import kotlinx.serialization.Serializable

@Serializable
data class ArticleParticipant(
    @SaveToTable
    @Display(105, "Отчество", width = 30)
    val patronymic: String,

    @SaveToTable
    @Display(106, "Организация", width = 45)
    val organization: String,

    @SaveToTable
    @Display(107, "Должность", width = 45)
    val job: String,

    @Display(210, "Выберите разделы", displayType = DisplayType.Label, validation = Validation.Any)
    val sections: Unit,

        //Make Multi!
    @SaveToTable
    @Display(211, "Раздел 1 «Земля Калевалы. Туристические объекты и развитие территорий»", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionFirst: Boolean,

    @SaveToTable
    @Display(212, "Раздел 2 «Земля Калевалы. Историко-культурный потенциал территорий»", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionSecond: Boolean,

    @SaveToTable
    @Display(213, "Раздел 3 «Земля Калевалы. Образ Карелии в искусстве, науке и образовании»", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionThird: Boolean,

    @SaveToTable
    @Display(214, "Приложить файл с текстом статьи",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 50)
    val fileId: Int,

    override val id: Int, override val time: String, override val surname: String, override var name: String, override val email: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val city: String
) : Participant, NotOrganizer, OnlyName