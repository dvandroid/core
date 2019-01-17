package core.android.com.android_core

import android.Manifest
import android.os.Bundle
import core.android.com.corelib.helper.Validation
import dagger.Component
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var validation: Validation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.create().inject(this)
    }
}

@Component
interface MainActivityComponent{
    fun inject(app : MainActivity)
}
