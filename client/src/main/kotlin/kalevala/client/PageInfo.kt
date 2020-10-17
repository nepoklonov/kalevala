package kalevala.client

import kalevala.client.pages.*
import kalevala.client.pages.about.Conception
import kalevala.client.pages.about.History
import kalevala.client.pages.about.PhotosComponent
import kalevala.client.pages.about.VideoGallery
import kalevala.client.pages.karelia.*
import kalevala.client.stucture.PageProps
import kalevala.common.interpretation.*
import kalevala.client.pages.NewsComponent
import kalevala.client.pages.kalevala.CommonwealthComponent
import kalevala.client.pages.kalevala.Read
import react.RComponent
import react.RState
import kotlin.reflect.KClass

typealias PageRef = DirRef
typealias PageClass = KClass<out RComponent<PageProps, out RState>>


data class PageInfo(val ref: PageRef, val title: String, val pageClass: PageClass) {
    val url
        get() = ref.path
}

class Section(val self: PageInfo, val pages: List<PageInfo>) {
    val url
        get() = self.url
    val title
        get() = self.title

    constructor(ref: PageRef, title: String, pageClass: PageClass, vararg pagesArray: PageInfo) :
        this(PageInfo(ref, title, pageClass), pagesArray.asList())

    companion object {
        val Main = Section(Pages.main, "Главная", MainComponent::class)
        val About = Section(Pages.about, "О фестивале", AboutComponent::class,
            PageInfo(Pages.About.conception, "Концепция фестиваля", Conception::class),
            PageInfo(Pages.About.news, "Новости", NewsComponent::class),
            PageInfo(Pages.About.partners, "Организаторы и партнёры", PartnersComponent::class),
            PageInfo(Pages.About.team, "Команда проекта", TeamComponent::class),
            PageInfo(Pages.About.history, "История фестиваля", History::class),
            PageInfo(Pages.About.photos, "Фотогалерея", PhotosComponent::class),
            PageInfo(Pages.About.videos, "Видеогалерея", VideoGallery::class),
            PageInfo(Pages.About.greetings, "Приветствия, отзывы", Greetings::class),
            PageInfo(Pages.About.symbols, "Награды, символика", SymbolsComponent::class)
        )
        val Join = Section(Pages.join, "Принять участие", JoinComponent::class,
            PageInfo(Pages.Join.ethnoTour, "Конкурс представителей туриндустрии «ЭТНОтур»", JoinComponent::class),
            PageInfo(Pages.Join.faces, "Фотоконкурс «Лики земли Карельской»", JoinComponent::class),
            PageInfo(Pages.Join.sunCountry, "Конкурс детского творчества «Калевала — страна солнца»", JoinComponent::class),
            PageInfo(Pages.Join.ethnoMotive, "Конкурс дизайна «ЭТНОмотив»", JoinComponent::class),
            PageInfo(Pages.Join.scientific, "Научно-деловая программа", JoinComponent::class),
            PageInfo(Pages.Join.organize, "Организовать площадку этнофестиваля", JoinComponent::class)
        )
        val Gallery = Section(Pages.gallery, "Участники", GalleryComponent::class,
            PageInfo(Pages.Gallery.sunCountry, "Конкурс детского творчества «Калевала — страна солнца»", GalleryComponent::class),
            PageInfo(Pages.Gallery.faces, "Фотоконкурс «Лики земли Карельской»", GalleryComponent::class),
            PageInfo(Pages.Gallery.ethnoMotive, "Конкурс дизайна «ЭТНОмотив»", GalleryComponent::class),
            PageInfo(Pages.Gallery.ethnoTour, "Конкурс представителей туриндустрии «ЭТНОтур»", AntiGallery::class),
            PageInfo(Pages.Gallery.scientific, "Научно-деловая программа", AntiGallery::class),
            PageInfo(Pages.Gallery.organize, "Организация площадок этнофестиваля", AntiGallery::class)
        )
        val Karelia = Section(Pages.karelia, "О Карелии", AboutComponent::class,
            PageInfo(Pages.Karelia.common, "Общие сведения", CommonComponent::class),
            PageInfo(Pages.Karelia.symbols, "Государственные символы", kalevala.client.pages.karelia.Symbols::class),
            PageInfo(Pages.Karelia.history, "История", kalevala.client.pages.karelia.History::class),
            PageInfo(Pages.Karelia.tourism, "Туризм", Tourism::class),
            PageInfo(Pages.Karelia.literature, "Литература о Карелии", Literature::class),
            PageInfo(Pages.Karelia.culturalInstitutions, "Учреждения культуры", Culture::class),
            PageInfo(Pages.Karelia.onlineResources, "Интернет-ресурсы", InternetSources::class)
        )
        val Kalevala = Section(Pages.kalevala, "О Калевале", Read::class)
        val Admin = Section(Pages.admin, "Админка", AdminComponent::class)
        val Commonwealth = Section(Pages.commonwealth, "Карельское Содружество", CommonwealthComponent::class)
    }
}