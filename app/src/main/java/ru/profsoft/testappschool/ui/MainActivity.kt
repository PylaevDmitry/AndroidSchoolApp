package ru.profsoft.testappschool.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.main_activity.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.selectDestination
import ru.profsoft.testappschool.ui.base.BaseActivity
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.viewModel.MainViewModelFactory
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.NavigationCommand
import ru.profsoft.testappschool.viewModel.base.Notify
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    init {
        App.INSTANCE.appComponent.inject(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.authFragment,
                R.id.authMainFragment
            )
        )

//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        bottom_navigate.setOnNavigationItemSelectedListener {
//            if (it.itemId != bottom_navigate.selectedItemId) {
//                viewModel.navigate(NavigationCommand.To(it.itemId))
//            }
//            true
//        }
//
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            bottom_navigate.selectDestination(destination)
//        }
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    public override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
    }

    override val layout: Int = R.layout.main_activity

    override fun subscribeOnState(state: IViewModelState) {
        //TODO("Not yet implemented")
    }

    override fun renderNotification(notify: Notify) {
        //TODO set values for dialog
        when (notify) {
            is Notify.ActionMessage -> {
                val builder = AlertDialog.Builder(this)
                builder.apply {
                    setTitle(notify.actionLabel)
                    setMessage(notify.message)
                    setPositiveButton(
                        "Хорошо"
                    ) { dialog, id ->
                        notify.actionHandler.invoke()
                        dialog.dismiss()
                    }
                }
                builder.show()
            }
            is Notify.TextMessage -> {
                Toast.makeText(this, notify.message, Toast.LENGTH_SHORT).show()
            }
            is Notify.ErrorMessage -> {
                Toast.makeText(this, notify.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}