package core.android.com.corelib.camera


/**
 * Create a new immutable Size instance.
 *
 * @param width  The width of the size, in pixels
 * @param height The height of the size, in pixels
 */


class Size(width: Int, height: Int) : Comparable<Size> {
    private var mWidth = width
    private var mHeight = height


    fun getWidth(): Int {
        return mWidth
    }

    fun getHeight(): Int {
        return mHeight
    }

    override fun equals(o: Any?): Boolean {
        if (o == null) {
            return false
        }
        if (this === o) {
            return true
        }
        if (o is Size) {
            val size = o as Size?
            return mWidth == size!!.mWidth && mHeight == size.mHeight
        }
        return false
    }

    override fun toString(): String {
        return mWidth.toString() + "x" + mHeight
    }

    override fun hashCode(): Int {
        return mHeight xor (mWidth shl Integer.SIZE / 2 or mWidth.ushr(Integer.SIZE / 2))
    }

    override fun compareTo(other: Size): Int {
        return mWidth * mHeight - other.mWidth * other.mHeight

    }

}

