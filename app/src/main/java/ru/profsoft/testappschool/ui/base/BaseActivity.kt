package ru.profsoft.testappschool.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_activity.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.viewModel.base.*

abstract class BaseActivity<T : BaseViewModel<out IViewModelState>> : AppCompatActivity() {
    protected abstract val viewModel: T
    protected abstract val layout: Int
    lateinit var navController: NavController

    val bottombarBuilder = BottombarBuilder()

    abstract fun subscribeOnState(state: IViewModelState)

    abstract fun renderNotification(notify: Notify)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)
        viewModel.observeState(this) { subscribeOnState(it) }
        viewModel.observeNotifications(this) { renderNotification(it) }
        viewModel.observeNavigation(this) { subscribeOnNavigation(it) }
        viewModel.observeLoading(this) { renderLoading(it) }

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restoreState()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    open fun renderLoading(loadingState: Loading) {
        when (loadingState) {
            Loading.SHOW_LOADING -> progress.visibility = View.VISIBLE
            Loading.SHOW_BLOCKING_LOADING -> {
                progress.visibility = View.VISIBLE
                //TODO block interact with UI
            }
            Loading.HIDE_LOADING -> progress.visibility = View.GONE
        }
    }

    private fun subscribeOnNavigation(command: NavigationCommand) {
        if (command is NavigationCommand.To) {
            navController.navigate(
                command.destination,
                command.args,
                command.options,
                command.extras
            )
        }
        if (command is NavigationCommand.Back) {
            navController.popBackStack()
        }
    }
}

data class MenuItemHolder(
    val title: String,
    val menuId: Int,
    var icon: Int? = null,
    val actionViewLayout: Int? = null,
    var visible:Boolean = true,
    val clickListener: ((MenuItem) -> Unit)? = null
)

class BottombarBuilder {
    var visibility: Boolean = true
    private val views = mutableListOf<Int>()
    private val tempViews = mutableListOf<Int>()

    fun addView(layoutId: Int) = apply { views.add(layoutId) }
    fun setVisibility(isVisible: Boolean) = apply { this.visibility = isVisible }
    fun prepare(prepareFn: (BottombarBuilder.() -> Unit)?) = apply { prepareFn?.invoke(this) }

    fun invalidate() = apply {
        visibility = true
        views.clear()
    }

    fun build(context: FragmentActivity) {
        context.bottom_navigate.isVisible = this@BottombarBuilder.visibility

        if (tempViews.isNotEmpty()) {
            tempViews.forEach {
                val view = context.container.findViewById<View>(it)
                context.container.removeView(view)
            }
            tempViews.clear()
        }

        if (views.isNotEmpty()) {
            val inflater = LayoutInflater.from(context)
            views.forEach {
                val view = inflater.inflate(it, context.container, false)
                context.container.addView(view)
                tempViews.add(view.id)
            }
        }
    }
}