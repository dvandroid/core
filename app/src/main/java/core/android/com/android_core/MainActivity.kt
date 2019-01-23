package core.android.com.android_core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import core.android.com.corelib.helper.Validation
import dagger.Component
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var validation: Validation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

@Component
interface MainActivityComponent{
    fun inject(app : MainActivity)
}
