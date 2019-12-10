package org.app.habib.ghiblifilms.contract;

import org.app.habib.ghiblifilms.model.Location;

import java.util.List;


public interface LocationContract {

    interface View {
        void onDataStarted();
        void onDataCompleted();
        void showData(List<Location> characters);
        void onDataError(Throwable e);
    }

    interface Presenter {
        void loadData();
        void onStop();
    }
}