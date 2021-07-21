package ru.profsoft.testappschool.ui.base

import android.os.Bundle
import android.view.*
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import ru.profsoft.testappschool.ui.MainActivity
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.Loading

abstract class BaseFragment<T : BaseViewModel<out IViewModelState>> : Fragment() {
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    val main: MainActivity
        get() = activity as MainActivity
    open val binding: Binding? = null
    protected abstract val viewModel: T
    protected abstract val layout: Int

    open val prepareBottombar: (BottombarBuilder.() -> Unit)? = null

    abstract fun setupViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //restore state
        viewModel.restoreState()
        binding?.restoreUi(savedInstanceState)

        //owner it is view
        viewModel.observeState(viewLifecycleOwner) { binding?.bind(it) }
        //bind default values if viewmodel not loaded data
        if(binding?.isInflated == false) binding?.onFinishInflate()

        viewModel.observeNotifications(viewLifecycleOwner) { main.renderNotification(it) }
        viewModel.observeNavigation(viewLifecycleOwner) { main.viewModel.navigate(it) }
        viewModel.observeLoading(viewLifecycleOwner){ renderLoading(it) }

        setupViews()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        main.bottombarBuilder
            .invalidate()
            .prepare(prepareBottombar)
            .build(main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        binding?.saveUi(outState)
        super.onSaveInstanceState(outState)
    }

    open fun renderLoading(loadingState: Loading){
        main.renderLoading(loadingState)
    }
}