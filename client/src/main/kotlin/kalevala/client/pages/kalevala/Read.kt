package kalevala.client.pages.kalevala

import kalevala.client.stucture.PageProps
import kalevala.client.stucture.StaticPage
import react.dom.a
import react.dom.p
import styled.StyledDOMBuilder

class Read(pageProps: PageProps) : StaticPage(pageProps) {
    override fun StyledDOMBuilder<*>.page() {
        a(href = "http://fulr.karelia.ru/Resursy/Elektronnye_kollekcii_na_finno-ugorskih_jazykah/Izdanija_karelo-finskogo_eposa_Kalevala/", target = "_blank") {
            +"Читать «Калевалу»"
        }
        p {
            +"«Ка́левала» (карел. и фин. Kalevala) — карело-финский поэтический эпос. Состоит из 50 рун (песен)."
        }
        p {
            +"В основу «Калевалы» легли карельские народные эпические песни. Обработкой исходного фольклорного материала занимался финский языковед и врач Элиас Лённрот (1802—1884), который сюжетно связал отдельные народные эпические песни, произвёл определённый отбор вариантов этих песен, сгладил некоторые неровности[1]. Обработка была произведена Лённротом дважды: в 1835 году вышло первое издание «Калевалы», в 1849 году — второе[2]. Полный перевод поэмы на русский язык был выполнен Л. П. Бельским и опубликован в журнале «Пантеон литературы» в 1888 году, отдельным изданием вышел в 1889 году. «Калевала» — важный источник сведений о дохристианских религиозных представлениях финнов и карел. Название «Калевала», данное поэме Лённротом, — это эпическое имя страны, в которой живут и действуют карельские народные герои. Суффикс la означает место жительства, так что Kalevala — это место жительства Калева, мифологического родоначальника богатырей Вяйнямёйнена, Ильмаринена, Лемминкяйнена, называемых иногда его сыновьями.[2]"
        }
        p {
            +"Материалом для сложения обширной поэмы из 50 песен (рун) послужили Лённроту отдельные народные песни, частью эпического, частью лирического, частью магического характера, записанные со слов карельских и финских крестьян самим Лённротом и предшествовавшими ему собирателями. Лучше всего помнили старинные руны (песни) в российской Карелии, в Архангельской (приход Вуоккиниеми — Вокнаволок) и Олонецкой губерниях — в Реполе (Реболы) и Химоле (Гимолы), а также в некоторых местах финляндской Карелии и на западных берегах Ладожского озера, до Ингрии."
        }
        p {
            +"1 руна Калевалы повествует о начале мира, когда не было ни солнца, ни животных, ни птиц, ни деревьев. Была лишь вода и одинокая (yksin) дочь воздуха Ильматар, которая в скорби от воды зачинает Вяйнямёйнена. Параллельно к Ильматар прилетает утка (sotka: чернети), которая откладывает ей на колено 7 яиц: шесть золотых и одно железное. Разбившись, яйца порождают землю (maa), небо (taivas), солнце (päivyt), луну (kuu) и звезды (tähiksi). Ильматар оформляет рельеф, создавая острова, бухты и мысы. Вяйнямёйнен родился взрослым (его постоянный эпитет vanha — старый) и несколько лет плавал в воде, пока не достиг страны Калевалы. Во 2 руне Вяйнямёйнен при помощи Сампсу Пеллервойнена засевает землю растениями. Однако внезапно один волшебный дуб сильно разрастается и заслоняет солнце. На борьбу с ним выходит морской карлик с медным топором (vaskikirves). Вяйнямейнен оказывается в стране Калевала и сеет там ячмень (ohra). 3 руна повествует о противостоянии с лапландцем Йоукахайненом, который выкупает свою жизнь обещанием отдать в жены Вяйнямёйнену свою сестру Айно. Однако в 4 руне Айно (Aino) не желает выходить замуж за старика и кончает жизнь самоубийством, утопившись в море."
        }
        p {
            +"В конце 5 руны Вяйнямёйнен слышит совет своей матери, что искать невесту себе нужно не в Лапландии, а в Похьёле. Вяйнямейнен отправляется туда, однако из-за стрелы Йоукахайнена он оказывается в море и лишь благодаря орлу оказывается в Похьеле. Местная колдунья требует, чтобы Вяйнямейнен изготовил Сампо, обещая выдать за него замуж свою дочь. В 8 руне герой знакомится с девушкой, но во время испытания он ранит себя топором, не может унять кровотечения и идет к старому колдуну (loitsija), которому в 9 руне рассказывает предание о происхождении железа (rauta). Возвратившись домой,"
        }
        p {
            +"Вяйнямёйнен поднимает заклинаниями ветер и переносит кузнеца Ильмаринена в Похьёлу, где тот, согласно обещанию, данному Вяйнямёйненом, выковывает для хозяйки Севера таинственный предмет, дающий богатство и счастье — мельницу Сампо. С 11 по 15 руну описывают похождениях героя Лемминкяйнена, воинственного чародея и соблазнителя женщин, который ездит в Похьёлу, но во время одного из испытаний гибнет, но возвращается к жизни благодаря молитве матери к Укко. Далее рассказ возвращается к Вяйнямёйнену, который задумал построить лодку, однако необходимость поиска магических слов приводит его в Маналу. Также описывается его пребывание в утробе великана Випунена (Antero Vipunen), добытие им от последнего магических слов, отплытие героя в Похьёлу с целью получить руку северной девы. Однако в 18 руне дочь хозяйки Похьёлы выбирает себе в мужья кузнеца Ильмаринена, за которого выходит замуж, причём подробно описывается свадьба и приводятся свадебные песни, излагающие обязанности жены (уважать родственников мужа, поддерживать огонь в очаге и чистоту в доме, кормить скотину) и мужа. 26-30 руны снова рассказывают о похождениях неугомонного Лемминкяйнена в Похьёле, куда он прибывает незваным гостем на свадьбу Ильмаринена, убивает в ссоре хозяина Похьелы и бежит от возмездия на далекий остров. В 31-36 рунах повествуется о печальной судьбе богатыря Куллерво, соблазнившего по неведению родную сестру, вследствие чего оба, брат и сестра, кончают жизнь самоубийством, принадлежит по глубине чувства, достигающего иногда истинного пафоса, к лучшим частям всей поэмы. Руны о богатыре Куллерво были записаны помощником Лённрота фольклористом Даниэлем Европеусом."
        }
        p {
            +"Дальнейшие руны содержат пространный рассказ об общем предприятии трёх карельских героев — о том, как были добыты сокровища Сампо из Похьёлы, как Вяйнямёйнен изготовил кантеле из щуки и игрой на нём очаровал всю природу и усыпил население Похьёлы, как Сампо был увезён героями. Рассказывается о преследовании героев колдуньей-хозяйкой Севера при помощи Утутар, Укко и Ику-Турсо, о падении Сампо в море, о благодеяниях, оказанных Вяйнямёйненом родной стране посредством осколков Сампо, о борьбе его с разными бедствиями и чудищами, насланными хозяйкой Похьёлы на Калевалу, о дивной игре героя на новом кантеле, созданном им из березы, когда первое упало в море. После того как Лоухи похитила солнце, небесный бог Укко создает новое, которое падает в озеро Алуэ. Получив сильные ожоги от огня Укко, Ильмаринен кует еще одно солнце, но настоящее все же спрятано в Похьеле у Лоухи. И герои в 49 руне отправляются за солнцем и Лоухи идет на попятную."
        }
        p {
            +"Последняя руна содержит народно-апокрифическую легенду о рождении чудесного ребёнка девой Марьяттой (Marjatta). Вяйнямёйнен дает совет его убить, так как ему суждено превзойти могуществом карельского героя, но двухнедельный младенец осыпает Вяйнямёйнена упрёками в несправедливости, и пристыженный герой, спев в последний раз дивную песнь, уезжает навеки в челноке, уступая место младенцу Марьятты, признанному властителю Карелии. Сама по себе Калевала является страной, где живет главный герой Вяйнямёйнен. Калевале традиционно противостоит Похъёла, куда герои Калевалы добираются по морю. Совсем за пределами обитаемой земли находится Манала. Тем не менее, упомянуты и совсем привычные страны: Эстония (Viro), Ингрия (Inkeri), Карелия, Суоми, Саво, Россия (Venäjä), Швеция (Ruotsi), Лапландия (Lappi), Вьена (Viena, Беломорье) и Германия(Саксония, Saksa). По соседству с Лапландией и Похьелой упоминается страна Турья (Turja). Из рек упоминается Вуокса c водопадом Иматра и Нева . Из городов упомянут только Таллин (Tanikan linna). Ежегодно 28 февраля празднуется «День народного эпоса Калевалы» — официальный день финской и карельской культуры, этот же день посвящён финскому флагу. Каждый год в Карелии и Финляндии проходит «Калевальский карнавал», в форме уличного костюмированного шествия, а также театрализованных представлений по сюжету эпоса. Ежегодно, с 2006 года в Санкт-Петербурге проводится Международный этнофестиваль \"Земля Калевалы\", объединяющий ведущих экспертов, художников, дизайнеров и творческие коллективы из России и Финляндии."
        }
    }
}