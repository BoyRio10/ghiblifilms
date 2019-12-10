package org.app.habib.ghiblifilms.contract;

import org.app.habib.ghiblifilms.model.Character;

import java.util.List;



public interface CharacterContract {

    interface View {
        void onDataStarted();
        void onDataCompleted();
        void showData(List<Character> characters);
        void onDataError(Throwable e);
    }

    interface Presenter {
        void loadData();
        void onStop();
    }
}