package kalevala.common.interpretation

import kotlinx.serialization.enumMembers
import kalevala.common.interpretation.ScaleType.*
import kotlinx.serialization.Serializable

@Serializable
data class PlanarSize(val width: Int, val height: Int) {
    constructor(w: Number, h: Number) : this(w.toInt(), h.toInt())

    override fun toString(): String {
        return "${width}x$height"
    }

    operator fun compareTo(scale: Scale): Int {
        return if ((scale.type == INSIDE && (width > scale.size.width || height > scale.size.height)) ||
            (scale.type == OUTSIDE && (width > scale.size.width && height > scale.size.height))) 1 else
        if ((scale.type == INSIDE && (width < scale.size.width && height < scale.size.height)) ||
            (scale.type == OUTSIDE && (width < scale.size.width || height < scale.size.height))) -1 else 0
    }

    operator fun compareTo(other: PlanarSize): Int {
        return if (width > other.width && height > other.height) 1 else
            if (width < other.width && height < other.height) -1 else 0
    }
}

enum class ScaleType(val flag: Boolean) {
    INSIDE(false),
    OUTSIDE(true)
}

infix fun Number.x(other: Number) = PlanarSize(this, other)

fun scaleOutsideByWidth(width: Number) = width.toInt() x 1 put OUTSIDE
fun scaleOutsideByHeight(height: Number) = 1 x height.toInt() put OUTSIDE
fun scaleInsideByWidth(width: Number) = width.toInt() x Int.MAX_VALUE put INSIDE
fun scaleInsideByHeight(height: Number) = Int.MAX_VALUE x height.toInt() put INSIDE

@Serializable
class Scale(val size: PlanarSize, val type: ScaleType)

infix fun PlanarSize.put(type: ScaleType) = Scale(this, type)

interface ScaleContainer {
    val scale: Scale
}

enum class ParticipantScale(override val scale: Scale) : ScaleContainer {
    Big(500 x 500 put OUTSIDE),
    Normal(250 x 250 put OUTSIDE),
    VerySmall(100 x 100 put OUTSIDE)
}

@Serializable
class SingleScale(override val scale: Scale) : ScaleContainer

@Serializable
data class Image<out T : ScaleContainer> internal constructor (val original: FileRef, val scales: List<T>) {
    companion object {
        internal operator fun invoke(originalPath: FileRef, vararg scales: Scale): Image<SingleScale> {
            return Image(originalPath, scales.map(::SingleScale))
        }

        internal inline operator fun <reified T> invoke(originalPath: FileRef): Image<T> where T : ScaleContainer, T : Enum<T> {
            return Image(originalPath, T::class.enumMembers().asList())
        }
    }
}

fun getSectionIcon(name: String): Image<SingleScale> =
    Image(ImageDirs.design / "pages" file name dot "png", 100 x 70 put INSIDE)
