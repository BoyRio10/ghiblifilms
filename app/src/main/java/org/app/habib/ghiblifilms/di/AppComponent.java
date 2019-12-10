package org.app.habib.ghiblifilms.di;

import org.app.habib.ghiblifilms.MainActivity;
import org.app.habib.ghiblifilms.rest.NetworkClient;
import org.app.habib.ghiblifilms.view.fragment.CharacterListFragmentList;
import org.app.habib.ghiblifilms.view.fragment.FilmListFragmentList;
import org.app.habib.ghiblifilms.view.fragment.LocationListFragmentList;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = NetworkClient.class)
public interface AppComponent {
    void inject(FilmListFragmentList fragment);
    void inject(CharacterListFragmentList fragment);
    void inject(LocationListFragmentList fragment);
    void inject(MainActivity activity);
}
