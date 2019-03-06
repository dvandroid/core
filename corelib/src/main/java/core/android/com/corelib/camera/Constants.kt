package core.android.com.corelib.camera


/**
 * Constants for Camera2 and QR Reader
 */

val DEFAULT_ASPECT_RATIO = AspectRatio.of(1, 1)
const val FACING_BACK = 0
const val FACING_FRONT = 1
const val FLASH_OFF = 0
const val FLASH_ON = 1
const val FLASH_AUTO = 3
const val REQUEST_CAMERA_PERMISSION = 1111
const val REQUEST_CAMERA_INTENT = 1112

const val PORTRAIT_WIDTH_RATIO = 6f / 8
const val PORTRAIT_WIDTH_HEIGHT_RATIO = 0.75f

const val LANDSCAPE_HEIGHT_RATIO = 4f / 8
const val LANDSCAPE_WIDTH_HEIGHT_RATIO = 1.2f
const val MIN_DIMENSION_DIFF = 40
const val SQUARE_DIMENSION_RATIO = 4f / 8

const val IMAGE_URI = "image_uri"

/**
 * Max preview width that is guaranteed by Camera2 API
 */
const val MAX_PREVIEW_WIDTH = 1920

/**
 * Max preview height that is guaranteed by Camera2 API
 */
const val MAX_PREVIEW_HEIGHT = 1080


//////////////////////////////////////////////////
/**
 * Constants for Material Button
 */

/**
 * Button appearance
 */
const val SOLID = 0
const val SOLID_STROKE = 1
const val STROKE_ONLY = 2
const val TRANSPARENT = 3

/**
 * Button types
 */
const val RECTANGLE = 0
const val SQUIRCLE = 1
const val ROUND = 2

const val SQUIRCLE_RADIUS = 16F
const val ROUND_RADIUS = 64F


