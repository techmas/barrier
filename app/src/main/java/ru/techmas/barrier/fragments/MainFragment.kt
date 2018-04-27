package ru.techmas.barrier.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.techmas.barrier.R
import ru.techmas.barrier.presenters.MainFragmentPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_main.*
import ru.techmas.barrier.Const
import ru.techmas.barrier.adapters.BarriersAdapter
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.interfaces.views.MainFragmentView
import ru.techmas.barrier.models.Photos
import ru.techmas.barrier.utils.Injector
import java.util.concurrent.TimeUnit


class MainFragment : BaseFragment(), MainFragmentView {

    private var adapter: BarriersAdapter? = null
    private lateinit var barrier: Barrier

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(LAYOUT, container, false)
        return rootView
    }

    override fun setupUI() {
    }

    override fun setupUX() {
    }

    override fun showData(barriers: Barriers, photos: Photos) {
        adapter = BarriersAdapter(context!!, barriers, photos)
        adapter!!.onBarrierClickListener = mainFragmentPresenter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    override fun showCamera(item: Barrier) {
        AlertDialog.Builder(activity)
                .setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.alert_message))
                .setPositiveButton("OK") {dialog, which -> dialog.dismiss()}
                .create().show()
    }

    override fun onResume() {
        super.onResume()
        mainFragmentPresenter.getBarriers()
    }

    //    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    override fun updateData(barrier: Barrier) {
        this.barrier = barrier
        adapter?.notifyDataSetChanged()
        Observable.interval(Const.Time.OPEN_DELAY, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach { this.barrier.opened = false }
                .takeWhile { this.barrier.opened }
                .doOnComplete { releaseOpenButton() }
                .subscribe()
    }

    private fun releaseOpenButton() {
        adapter?.notifyDataSetChanged()
    }
//        super.onActivityResult(requestCode, resultCode, data)
//        mainFragmentPresenter.onActivityResult(requestCode, resultCode, data)
//    }

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
