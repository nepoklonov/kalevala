package kalevala.server

import kalevala.common.models.participants.*
import java.awt.Color
import java.awt.Font
import java.io.File
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.imageio.ImageIO
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import kotlin.reflect.KClass

class MailBuilder {
    companion object {
        fun a(href: String, text: String) = "<a href='$href'>$text</a>"
        const val br = "<br/>"
        fun little(text: String) = "<span style='fontSize: 12pt'>$text</span>"
    }
}

fun sendCertificate(m: Participant, mClass: KClass<out AnyForm>) {
    val fio = when (m) {
        is OnlyName -> m.surname + " " + m.name
        is NameOrGroup -> if (m.group != "") m.group else m.surname + " " + m.name
        else -> error("must be name")
    }

    val email = m.email
    val hash = m.time


    val mailProps = Properties()
    mailProps["mail.smtp.host"] = "smtp.yandex.ru"
    mailProps["mail.smtp.auth"] = "true"
    mailProps["mail.smtp.port"] = "465"
    mailProps["mail.smtp.socketFactory.port"] = "465"
    mailProps["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"

    val mailSession = Session.getInstance(mailProps, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication("kalevfest", "kalevala")
        }
    })

    val message = MimeMessage(mailSession)
    message.setFrom(InternetAddress("kalevfest@yandex.ru"))
    message.setRecipients(Message.RecipientType.TO, email.trim().toLowerCase())
    message.setSubject("Сертификат участника", "UTF-8")
    val mp = MimeMultipart()

    val mbpText = MimeBodyPart()
    mbpText.setContent(MailBuilder.run {
        when (mClass) {
            ScientificParticipant::class -> "Здравствуйте! Вы успешно прошли регистрацию на участие в научно-деловой программе XIV Международного этнофестиваля «Земля Калевалы-2020». Актуальная информация о программе, площадках и секциях будет размещаться на ${a("http://kalevala-fest.ru", "официальном сайте Этнофестиваля")}. Со спикерами и авторами статей в сборник Конференции дополнительно свяжутся кураторы секций." +
                "${br}${br}До встречи на XIV Международном этнофестивале «Земля Калевалы-2020»!" +
                "${br}${br}С уважением,${br}кураторы научно-деловой программы:" +
                "${br}${br}${little("Площадки в Санкт-Петербурге: Юшко Антон Алексеевич, председатель комиссии экоэффективного туризма Ленинградского областного отделения Русского Географического Общества, e-mail: yushko.an@gmail.com, м.т.: +7 (921) 900 96 56")}" +
                "${br}${br}${little("Площадки в Москве: Жукова Галина Евгеньевна, к.п.н., доцент Московского Городского Педагогического Университета, Почетный работник образования РФ, член Экспертного совета по дошкольному образованию Комитета по образованию Государственной Думы Российской Федерации, e-mail: footballgirl@mail.ru, м.т.: +7 (916) 661 85 20")}" +
                "${br}${br}${little("Работа с зарубежными участниками: Кононова Мария Юрьевна, д.т.н., профессор, действительный член МАН ВШ и АН ВШ РФ, эксперт и член Наблюдательного совета института Северных измерений по Рабочей группе Экология и энергетика., e-mail: maria.kononova.2013@gmail.com, м.т.: +7 (911) 133 02 59;")}" +
                "${br}${br}${little("Автор и руководитель Международного этнофестиваля «Земля Калевалы» Головачев Владимир Сергеевич, e-mail: vladgolovachev@yandex.ru, м.т.: +7 (916) 557 12 55.")}"
            OrganizeParticipant::class -> "Здравствуйте!$br" +
                "Ваша заявка на включение мероприятия в официальную программу Международного этнофестиваля «Земля Калевалы» успешно принята Организационным комитетом.$br" +
                "Информация о мероприятии будет размещена на официальных ресурсах Этнофестиваля.$br" +
                "Благодарим Вас за сотрудничество и рекомендуем использовать при подготовке к мероприятию официальную символику и информацию об Этнофестивале (раздел ${a("http://kalevala-fest.ru/about", "«О фестивале»")} официального сайта Этнофестиваля$br$br" +
                "Актуальная информация о программе Этнофестиваля будет размещаться на ${a("http://kalevala-fest.ru/", "официальном сайте")}.$br" +
                "До встречи на XIV Международном этнофестивале «Земля Калевалы-2020»!$br$br" +
                "С уважением,$br" +
                "Организационный комитет"
            ArticleParticipant::class -> "Здравствуйте!$br" +
                "Вы подали материал в сборник тезисов научно-деловой программы XIV Международного этнофестиваля «Земля Калевалы-2020».$br$br" +
                "Ваш материал будет рассмотрен редакционной коллегией. В ближайшее время представители организационного комитета свяжутся с Вами.$br$br" +
                "Актуальная информация о программе, площадках и секциях Конференции будет размещаться на ${a("http://kalevala-fest.ru", "официальном сайте")} Этнофестиваля.$br$br" +
                "До встречи на XIV Международном этнофестивале «Земля Калевалы-2020»!$br$br" +
                "С уважением,$br" +
                "кураторы научно-деловой программы:" +
                "${br}${br}${little("Площадки в Санкт-Петербурге: Юшко Антон Алексеевич, председатель комиссии экоэффективного туризма Ленинградского областного отделения Русского Географического Общества, e-mail: yushko.an@gmail.com, м.т.: +7 (921) 900 96 56")}" +
                "${br}${br}${little("Площадки в Москве: Жукова Галина Евгеньевна, к.п.н., доцент Московского Городского Педагогического Университета, Почетный работник образования РФ, член Экспертного совета по дошкольному образованию Комитета по образованию Государственной Думы Российской Федерации, e-mail: footballgirl@mail.ru, м.т.: +7 (916) 661 85 20")}" +
                "${br}${br}${little("Работа с зарубежными участниками: Кононова Мария Юрьевна, д.т.н., профессор, действительный член МАН ВШ и АН ВШ РФ, эксперт и член Наблюдательного совета института Северных измерений по Рабочей группе Экология и энергетика., e-mail: maria.kononova.2013@gmail.com, м.т.: +7 (911) 133 02 59;")}" +
                "${br}${br}${little("Автор и руководитель Международного этнофестиваля «Земля Калевалы» Головачев Владимир Сергеевич, e-mail: vladgolovachev@yandex.ru, м.т.: +7 (916) 557 12 55.")}"


            else -> "Здравствуйте! Ваша работа успешно загружена. Спасибо вам за участие в конкурсе!"
        }
    }, "text/html; charset=utf-8")
    mp.addBodyPart(mbpText)

    when (mClass) {
        ScientificParticipant::class, ArticleParticipant::class, OrganizeParticipant::class -> {
        }
        else -> {


            val bufferedImage = ImageIO.read(File("images/design/cert.jpg"))
            val graphics = bufferedImage.graphics
            graphics.color = Color.black

            val fioList = mutableListOf<String>()

            var s = ""
            fio.split(' ').forEach { word ->
                graphics.font = Font("Arial Black", Font.BOLD, 60)
                val fm = graphics.fontMetrics
                if (fm.stringWidth(s + word) > 1300) {
                    fioList += s.trim()
                    s = ""
                }
                s += "$word "
            }
            fioList += s


//    val supervisorFioList = mutableListOf<String>()
//    if (supervisorFIO != null) {
//        graphics.font = Font("Arial Black", Font.BOLD, 40)
//        val fm = graphics.fontMetrics
//        s = ""
//        ("(Куратор: $supervisorFIO)").split(' ').forEach { word ->
//            if (fm.stringWidth(s + word) > 1300) {
//                supervisorFioList += s.trim()
//                s = ""
//            }
//            s += "$word "
//        }
//        supervisorFioList += s
//    }


            fioList.forEachIndexed { index, str ->
                graphics.font = Font("Arial Black", Font.BOLD, 60)
                val fm = graphics.fontMetrics
//        val otherHeight = supervisorFioList.size * 40
                val otherHeight = 0
                val fioWidth = fm.stringWidth(str)
                graphics.drawString(str, 827 - fioWidth / 2,
                    1040 - 30 * (fioList.size - 1) + 60 * index - otherHeight / 2)
            }

//    supervisorFioList.forEachIndexed { index, str ->
//        graphics.font = Font("Arial Black", Font.PLAIN, 40)
//        val fm = graphics.fontMetrics
//        val otherHeight = fioList.size * 60
//        val sFioWidth = fm.stringWidth(str)
//        graphics.drawString(str, 827 - sFioWidth / 2,
//            900 - 20 * (supervisorFioList.size - 1) + 40 * index + otherHeight / 2)
//    }

//    graphics.font = Font("Arial Black", Font.BOLD, 31)
//    graphics.drawString(number, 1313, 490)
            val fileC = File("images/certs/cert-$hash.jpg")
            ImageIO.write(bufferedImage, "jpg", fileC)
            println("Image Created")


            val mbpImage = MimeBodyPart()
            val fds = FileDataSource(fileC)
            mbpImage.dataHandler = DataHandler(fds)
            mbpImage.fileName = fds.name
            mp.addBodyPart(mbpImage)
        }
    }

    message.setContent(mp)
    message.sentDate = Date()
    message.saveChanges()
    Transport.send(message)
    println("The EmailListener says: ```, that the letter have been sent to the email called the $email```")
}