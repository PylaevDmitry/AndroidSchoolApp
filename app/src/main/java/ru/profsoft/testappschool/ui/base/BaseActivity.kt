package ru.profsoft.testappschool.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelState

abstract class BaseActivity<T : BaseViewModel<out IViewModelState>> : AppCompatActivity() {
    protected abstract val viewModel: T
    protected abstract val layout: Int

    abstract fun subscribeOnState(state: IViewModelState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        viewModel.observeState(this) { subscribeOnState(it) }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restoreState()
    }
}