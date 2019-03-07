package core.android.com.corelib.helper

sealed class Currency {
    object Php : Currency()
    object Peso : Currency()
    object Dollar: Currency()
}