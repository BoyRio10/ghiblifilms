package org.app.habib.ghiblifilms.rest;

import org.app.habib.ghiblifilms.model.Character;
import org.app.habib.ghiblifilms.model.Film;
import org.app.habib.ghiblifilms.model.Location;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;



public interface NetworkApi {

    @GET("films")
    Observable<List<Film>> getAllFilms();

    @GET("people")
    Observable<List<Character>> getAllCharacters();

    @GET("locations")
    Observable<List<Location>> getAllLocations();
}
