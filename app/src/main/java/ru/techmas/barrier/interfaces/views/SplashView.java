package ru.techmas.barrier.interfaces.views;

import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView;

public interface SplashView extends BaseView, NavigatorActivityView {
    void showErrorConnection(boolean show);
}
