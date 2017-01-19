import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class RestSync {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:80")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AthletaService athletaService = retrofit.create(AthletaService.class);

        // GET ALL ATHLETAS
        Call<List<Athleta>> callGetAll = AthletaService.getAllAthleta();
        Response<List<Athleta>> responseGet = callGetAll.execute();

        if (responseGet.isSuccessful()) {
            List<Athleta> athleta = responseGet.body();
            System.out.println("GET ALL \n " +
                    "Code: " + responseGet.code() + "\n" + athleta);
        } else {
            System.out.println("GET ALL --> ERROR\n " +
                    "Code: " + responseGet.code() + "\n" +
                    "ERROR: " + responseGet.errorBody());
        }

        // POST ATHLETA
        Athleta player = new Athleta();

        Call<Athleta> callCreate = AthletaService.createAthleta(player);
        Response<Athleta> responseCreate = callCreate.execute();

        if (responseCreate.isSuccessful()) {
            Athleta athletaCreated = responseCreate.body();
            System.out.println("POST \n " +
                    "Code: " + responseCreate.code() + "\n" + athletaCreated);

            // DELETE ATHLETA (id)
            Call<Void> callDelete = AthletaService.deleteAthletaID(athletaCreated.getId());
            Response<Void> responseDelete = callDelete.execute();

            System.out.println("DELETE \n Code: " + responseDelete.code());

            // GET ALL PARA VER SI TODO HA FUNCIONADO
            Call<List<Athleta>> getAll = AthletaService.getAllAthleta();
            Response<List<Athleta>> responseGetAll = getAll.execute();

            if (responseGet.isSuccessful()) {
                List<Athleta> athleta = responseGetAll.body();
                System.out.println("GET ALL \n " +
                        "Code: " + responseGetAll.code() + "\n" + athleta);
            }
        }else {
            System.out.println("POST --> ERROR \n " +
                    "Code: " + responseCreate.code() + "\n" +
                    "ERROR: " + responseCreate.errorBody());
        }

        // GET ATHLETA (id)
        Call<Athleta> callGetID = AthletaService.getAthletaID(1L);
        Response<Athleta> responseGetID = callGetID.execute();
        Athleta athletaID = responseGetID.body();

        if(responseGetID.isSuccessful()){
            System.out.println("GET (id) \n " +
                    "Code: " + responseGetID.code() + "\n" + responseGetID.body());
        }

        // PUT --> player get with id
        athletaID.setBaskets(100000);
        Call<Athleta> callChange = AthletaService.updateAthleta(athletaID);
        Response<Athleta> responseChange= callChange.execute();

        System.out.println("PUT (id) \n " +
                "Code: " + responseChange.code() + "\n" + responseChange.body());



    }
}