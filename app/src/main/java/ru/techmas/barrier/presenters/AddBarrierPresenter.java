package ru.techmas.barrier.presenters;


import ru.techmas.barrier.interfaces.views.AddBarrierView;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;


@InjectViewState
public class AddBarrierPresenter extends BasePresenter<AddBarrierView> {

    @Inject
    public AddBarrierPresenter() {

    }

}