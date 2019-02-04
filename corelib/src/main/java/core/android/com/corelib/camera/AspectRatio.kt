package core.android.com.corelib.camera

import android.support.v4.util.SparseArrayCompat


class AspectRatio private constructor(val x: Int, val y: Int) : Comparable<AspectRatio> {

    fun matches(size: Size): Boolean {
        val gcd = gcd(size.getWidth(), size.getHeight())
        val x = size.getWidth() / gcd
        val y = size.getHeight() / gcd
        return this.x == x && this.y == y
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (this === other) {
            return true
        }
        if (other is AspectRatio) {
            val ratio = other as AspectRatio?
            return x == ratio!!.x && y == ratio.y
        }
        return false
    }

    override fun toString(): String {
        return x.toString() + ":" + y
    }

    fun toFloat(): Float {
        return x.toFloat() / y
    }

    override fun hashCode(): Int {
        // assuming most sizes are <2^16, doing a rotate will give us perfect hashing
        return y xor (x shl Integer.SIZE / 2 or x.ushr(Integer.SIZE / 2))
    }

    override fun compareTo(other: AspectRatio): Int {
        if (equals(other)) {
            return 0
        } else if (toFloat() - other.toFloat() > 0) {
            return 1
        }
        return -1
    }

    /**
     * @return The inverse of this [AspectRatio].
     */
    fun inverse(): AspectRatio {

        return AspectRatio.of(y, x)
    }

    companion object {

        private val sCache = SparseArrayCompat<SparseArrayCompat<AspectRatio>>(16)

        /**
         * Returns an instance of [AspectRatio] specified by `x` and `y` values.
         * The values `x` and `` will be reduced by their greatest common divider.
         *
         * @param x The width
         * @param y The height
         * @return An instance of [AspectRatio]
         */
        fun of(x: Int, y: Int): AspectRatio {
            var x = x
            var y = y
            val gcd = gcd(x, y)
            x /= gcd
            y /= gcd
            var arrayX = sCache.get(x)
            return if (arrayX == null) {
                val ratio = AspectRatio(x, y)
                arrayX = SparseArrayCompat()
                arrayX.put(y, ratio)
                sCache.put(x, arrayX)
                ratio
            } else {
                var ratio = arrayX.get(y)
                if (ratio == null) {
                    ratio = AspectRatio(x, y)
                    arrayX.put(y, ratio)
                }
                ratio
            }
        }

        private fun gcd(a: Int, b: Int): Int {
            var a = a
            var b = b
            while (b != 0) {
                val c = b
                b = a % b
                a = c
            }
            return a
        }
    }

}
