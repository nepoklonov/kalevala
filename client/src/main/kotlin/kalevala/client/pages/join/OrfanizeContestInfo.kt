package kalevala.client.pages.join

import kalevala.client.indentedDiv
import kalevala.client.redH3
import react.RBuilder
import react.dom.h3
import react.dom.p

fun RBuilder.getOrganizeContestInfo() {
    p {
        +"Оргкомитет Международного этнофестиваля «Земля Калевалы» ведет прием заявок на включение в официальную программу Этнофестиваля мероприятий, посвященных карело-финскому эпосу «Калевала», культурному, природному, историческому и туристическому потенциалу Карелии. Заявки принимаются от учреждений и физических лиц."
    }

    redH3 {
        +"Преимущества включения события в программу «Земли Калевалы»:"
    }

    indentedDiv {
        +"статус официальной площадки Международного этнофестиваля;"
    }

    indentedDiv {
        +"публикация анонса мероприятия в официальной программе этнофестиваля;"
    }
    indentedDiv {
        +"информационная поддержка (публикация на официальных информационных ресурсах этнофестиваля, информационная рассылка);"
    }
    indentedDiv {
        +"благодарственное письмо этнофестиваля на учреждение и куратора площадки"
    }
}