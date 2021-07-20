package ru.profsoft.testappschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import ru.profsoft.testappschool.ui.base.BaseActivity
import ru.profsoft.testappschool.ui.main.MainFragment
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")

    /*@Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
    }*/

    override val layout: Int = R.layout.main_activity

    override fun subscribeOnState(state: IViewModelState) {
        TODO("Not yet implemented")
    }


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }*/
}