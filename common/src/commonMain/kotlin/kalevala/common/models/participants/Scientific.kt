package kalevala.common.models.participants

import kalevala.common.*
import kotlinx.serialization.Serializable

@Serializable
data class ScientificParticipant(
    @SaveToTable
    @Display(105, "Отчество", width = 30)
    var patronymic: String,

    @SaveToTable
    @Display(106, "Организация", width = 45)
    val organization: String,

    @SaveToTable
    @Display(107, "Должность", width = 45)
    val job: String,

    @Display(210, "Выберите секции", displayType = DisplayType.Label, validation = Validation.Any)
    val sections: Unit,

    //Make Multi!
    @SaveToTable
    @Display(211, "Пленарное заседание «Геокультурное пространство Карелии: традиции, современность, перспективы» (Санкт-Петербург)", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionFirst: Boolean,

    @SaveToTable
    @Display(212, "Секция 1: «Земля Калевалы. Туристические проекты и развитие территорий» – круглый стол (Санкт-Петербург)", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionSecond: Boolean,

    @SaveToTable
    @Display(213, "Секция 2: «Образ Калевалы в культуре, искусстве, образовании» – круглый стол (Москва, Санкт-Петербург)", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionThird: Boolean,

    @SaveToTable
    @Display(214, "Итоговый круглый стол «По земле Калевалы» (Москва)", displayType = DisplayType.CheckBox, validation = Validation.AnyCheckBox)
    val sectionFourth: Boolean,

    @SaveToTable
    @Display(215, "Форма участия", displayType = DisplayType.Select, width = 30, validation = Validation.Select)
    val form: ScientificForm,

    @SaveToTable
    @Display(216, "Название доклада", width = 40)
    @Belongs(OwnType.BySelect, ["form", "1"])
    val title: String,

    override val id: Int, override val time: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String, override val city: String
) : Participant, NotOrganizer, OnlyName {

    enum class ScientificSection(override val title: String) : Titled {
        First("Пленарное заседание «Геокультурное пространство Карелии: традиции, современность, перспективы» (Санкт-Петербург)"),
        Second("Секция 1: «Земля Калевалы. Туристические проекты и развитие территорий» – круглый стол (Санкт-Петербург)"),
        Third("Секция 2: «Образ Калевалы в культуре, искусстве, образовании» – круглый стол (Москва, Санкт-Петербург)"),
        Fourth("Итоговый круглый стол «По земле Калевалы» (Москва)")
    }

    enum class ScientificForm(override val title: String) : Titled {
        Listener("Слушатель"),
        Speaker("Спикер")
    }
}
