package ru.techmas.barrier.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.techmas.barrier.R
import ru.techmas.barrier.presenters.MainFragmentPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.techmas.barrier.adapters.BarriersAdapter
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.interfaces.views.MainFragmentView
import ru.techmas.barrier.utils.Injector


class MainFragment : BaseFragment(), MainFragmentView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(LAYOUT, container, false)
        return rootView
    }

    override fun setupUI() {
    }

    override fun setupUX() {
    }

    override fun showData(barriers: Barriers) {
        val adapter = BarriersAdapter(context!!, barriers)
        adapter.onBarrierClickListener = mainFragmentPresenter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter

    @ProvidePresenter
    internal fun provideMainPresenter() = Injector.presenterComponent!!.mainFragmentPresenter

    companion object {

        private val LAYOUT = R.layout.fragment_main

        fun newInstance(): MainFragment {
            val fragment = MainFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}
