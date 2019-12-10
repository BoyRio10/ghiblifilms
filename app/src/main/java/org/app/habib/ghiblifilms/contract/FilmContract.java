package org.app.habib.ghiblifilms.contract;

import org.app.habib.ghiblifilms.model.Film;

import java.util.List;


public interface FilmContract {

    interface View {
        void onDataStarted();
        void onDataCompleted();
        void showData(List<Film> films);
        void onDataError(Throwable e);
    }

    interface Presenter {
        void loadData();
        void onStop();
    }
}