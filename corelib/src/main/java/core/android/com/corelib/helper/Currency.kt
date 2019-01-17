package core.android.com.corelib.helper

sealed class Currency {
  /*  PHP("Php"),
    PESO("U+20B1"),
    DOLLAR("U+0024")*/

    object Php : Currency()
    object Peso : Currency()
    object Dollar: Currency()
}