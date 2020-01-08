package kalevala.client.pages.join

import kalevala.client.indentedDiv
import kalevala.client.pages.ContestA
import kalevala.client.redH3
import react.RBuilder
import react.dom.b
import react.dom.h3
import react.dom.p

fun RBuilder.getEthnoTourContestInfo(aOfficial: ContestA, aJoin: ContestA) {
    redH3 {
        +"О конкурсе"
    }
    p {
        +"Конкурс проводится с целью выявления, поощрения и популяризации лучших представителей туристической индустрии Северо-Запада России, активно использующих в своей профессиональной деятельности экологическую направленность, инновационный и клиентоориентированный подход, принципы «умного» и экоэффективного туризма."
    }

    redH3 {
        +"Задачи конкурса"
    }

    indentedDiv {
        +"презентация инновационных подходов к организации индустрии туризма;"
    }
    indentedDiv {
        +"выявление и поощрение лучших представителей организаций и индивидуальных предпринимателей, осуществляющих туристскую деятельность на территории Северо-Запада России;"
    }
    indentedDiv {
        +"представление новых интересных и привлекательных мест для активного отдыха, путешествий и туризма;"
    }
    indentedDiv {
        +"продвижение инновационного опыта в сфере въездного и внутреннего туризма;"
    }
    indentedDiv {
        +"развитие новых форм сельского, экологического, «умного» туризма;"
    }
    indentedDiv {
        +"поощрение и популяризация представителей организаций и индивидуальных предпринимателей, осуществляющих туристскую деятельность к систематическому повышению качества услуг и эффективности использования туристского потенциала памятников истории и природы;"
    }
    indentedDiv {
        +"укрепление внутренних добрососедских связей и межрегионального сотрудничества посредствам развития туриндустрии."
    }

    redH3 {
        +"Участники"
    }
    indentedDiv {
        +"юридические и физические лица – представители туриндустрии Северо-Запада России."
    }
    redH3 {
        +"Номинации"
    }
    indentedDiv {
        +"«ЭТНОобъект» – лучший туристический объект;"
    }
    indentedDiv {
        +"«ЭТНОпроект» – лучший авторский проект в сфере туриндустрии;"
    }
    indentedDiv {
        +"«ЭТНОмаршрут – лучший туристический маршрут."
    }

    redH3 {
        +"Принять участие легко!"
    }

    p {
        +"Для участия в Конкурсе необходимо:"
    }

    indentedDiv {
        +"ознакомиться с "
        aOfficial ("положением") {}
        +" и выбрать номинацию;"
    }
    indentedDiv {
        +"подготовить конкурсную работу;"
    }
    indentedDiv {
        +"загрузить работу, "
        aJoin ("заполнив анкету") {}
        +" на сайте Этнофестиваля."
    }

    p {
        +"При успешной загрузке, участнику придет подтверждение на указанный адрес электронной почты и сертификат участника Конкурса."
    }

    redH3 {
        +"Есть вопрос?"
    }

    indentedDiv {
        +"Куратор Конкурса: "
        b {
            +"Юшко Антон Алексеевич"
        }
        +", председатель комиссии экоэффективного туризма Ленинградского областного отделения Русского Географического Общества, e-mail: yushko.an@gmail.com, м.т.: +7-921-900-96-56"
    }

    indentedDiv {
        +"Автор и руководитель Международного этнофестиваля «Земля Калевалы»"
        b {
            +"Головачев Владимир Сергеевич"
        }
        +", e-mail: vladgolovachev@yandex.ru, м.т.: +7-916-557-12-55"
    }
}