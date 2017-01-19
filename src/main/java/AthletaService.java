import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;


public interface AthletaService {

    // GET ALL ATHLETA
    @GET("/Athleta")
    Call<List<Athleta>> getAllAthleta();

    // GET ATHLETA BY ID
    @GET("/Athleta/{id}")
    Call<Athleta> getAthletaID(@Path("id") Long id);

    // POST ATHLETA
    @POST("/Athleta")
    Call<Athleta> createAthleta(@Body Athleta athleta);

    // PUT ATHLETA
    @PUT("/Athleta")
    Call<Athleta> updateAthleta(@Body Athleta athleta);

    // DELETE ATHLETA BY ID
    @DELETE("/Athleta/{id}")
    Call<Void> deleteAthletaID(@Path("id") Long id);
}