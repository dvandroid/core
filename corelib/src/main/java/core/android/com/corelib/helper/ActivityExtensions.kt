package core.android.com.corelib.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Extension function to AppCompatActivity which adds fragment
 *
 * sample call inside AppCompatActivity class
 *     addFragment(fragment, R.id.container)
 */
 fun AppCompatActivity.addFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.inTransaction { add(containerId, fragment) }
}

/**
 * Extension function to AppCompatActivity which replaces fragment
 *
 * sample call inside AppCompatActivity class
 *     replaceFragment(fragment, R.id.container)
 */
 fun AppCompatActivity.replaceFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.inTransaction { replace(containerId, fragment) }
}

/**
 * Extension function to FragmentManager which accepts a Lambda with Receiver as its argument
 */
private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
