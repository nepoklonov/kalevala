package kalevala.client

import kalevala.common.interpretation.Pages
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.html.DIV
import kotlinx.html.H3
import kotlinx.html.SPAN
import react.RBuilder
import styled.*
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KProperty

val kalevalaRedColor = rgb(136, 78, 86)
val gray20Color = Color("#ddd")
val gray50Color = Color("#aaa")
val gray70Color = Color("#555")

fun getPageColor(pageRef: PageRef, isDark: Boolean): RGBA {
    val alpha = 0.9
    return when (pageRef) {
//        Pages.about -> if (isDark) rgb(188, 143, 58) else rgba(255, 240, 240, alpha)
//        Pages.join -> if (isDark) rgb(140, 77, 85) else rgba(255, 255, 240, alpha)
//        Pages.gallery -> if (isDark) rgb(115, 132, 142) else rgba(240, 240, 255, alpha)
//        Pages.karelia -> if (isDark) rgb(97, 97, 97) else rgba(240, 240, 240, alpha)
//        Pages.kalevala -> if (isDark) rgb(160, 164, 173) else rgba(230, 230, 230, alpha)

        Pages.about -> if (isDark) RGBA(140, 77, 85) else RGBA(249, 249, 249, alpha)
        Pages.join -> if (isDark) RGBA(188, 143, 58) else RGBA(249, 249, 249, alpha)
        Pages.gallery -> if (isDark) RGBA(115, 132, 142) else RGBA(249, 249, 249, alpha)
        Pages.karelia -> if (isDark) RGBA(97, 97, 97) else RGBA(249, 249, 249, alpha)
        Pages.kalevala -> if (isDark) RGBA(188, 143, 58) else RGBA(249, 249, 249, alpha)
        else -> if (isDark) RGBA(0, 0, 0) else RGBA(255, 255, 255)
    }
}


object MainStyles : StyleSheet("main") {
    val highlightedText by css {
        color = kalevalaRedColor
    }
    val normalA by css {
        textDecoration = TextDecoration.none
        color = Color.black
        hover {
            color = Color.darkSlateBlue
        }
        active {
            position = Position.relative
            top = (-1).px
        }
    }
    val current by css {
        children("a") {
            color = Color.skyBlue
        }
    }
    val indented by css {
        margin(5.px, 0.px)
        before {
            color = kalevalaRedColor
            content = QuotedString("\\25C6")
            marginRight = 5.px
        }
    }
}


fun RBuilder.highlightedSpan(block: StyledDOMBuilder<SPAN>.() -> Unit) = styledSpan {
    css {
        +MainStyles.highlightedText
    }
    block()
}

fun RBuilder.indentedDiv(block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    css {
        +MainStyles.indented
    }
    block()
}

fun RBuilder.redH3(block: StyledDOMBuilder<H3>.() -> Unit) = styledH3 {
    css {
        +MainStyles.highlightedText
    }
    block()
}


fun CSSBuilder.gridTemplateAreas(vararg s: String) {
    gridTemplateAreas = GridTemplateAreas(s.joinToString("") { "\"$it\" " })
}

@Suppress("UNCHECKED_CAST")
private class CSSProperty<T>(private val default: (() -> T)? = null) {
    operator fun getValue(thisRef: StyledElement, property: KProperty<*>): T {
        default?.let { default ->
            if (!thisRef.declarations.containsKey(property.name)) {
                thisRef.declarations[property.name] = default() as Any
            }
        }
        return thisRef.declarations[property.name] as T
    }

    operator fun setValue(thisRef: StyledElement, property: KProperty<*>, value: T) {
        thisRef.declarations[property.name] = value as Any
    }
}


typealias JustifyItems = JustifyContent
typealias JustifySelf = JustifyContent

var StyledElement.gridArea: String by CSSProperty()
var StyledElement.justifySelf: JustifySelf by CSSProperty()
var StyledElement.justifyItems: JustifyItems by CSSProperty()

class RGBA(var r: Double, var g: Double, var b: Double, var a: Double = 1.0) {
    constructor(r: Number, g: Number, b: Number, a: Double = 1.0) :
        this(r.toDouble(), g.toDouble(), b.toDouble(), a)

    init {
        r = min(max(r, 0.0), 255.0)
        g = min(max(g, 0.0), 255.0)
        b = min(max(b, 0.0), 255.0)
        a = min(max(a, 0.0), 1.0)
    }

    private operator fun plus(other: RGBA) = RGBA(r + other.r, g + other.g, b + other.b, a + other.a)
    private operator fun times(c: Double) = RGBA(r * c, g * c, b * c, a * c)

    fun mix(other: RGBA, proportion: Double) = this * proportion + other * (1 - proportion)
    fun toColor() = rgba(r.toInt(), g.toInt(), b.toInt(), a)
}

