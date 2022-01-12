package fr.lernejo.travelsite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface PredictionEngineClient {
    @GET
    Call<TemperatureByCountry> getTemperatureByCountry(@Query("country") String country);

    @GET
    Call<List<Travel>> getTravels(@Query("userName") String userName);

    @POST
    Call<Void> createInscription(@Body Inscription inscription);
}
