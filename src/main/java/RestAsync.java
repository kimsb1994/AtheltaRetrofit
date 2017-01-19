
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RestAsync {


    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:80")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AthletaService atletaService = retrofit.create(AthletaService.class);

        // GET ALL Athletas
        Call<List<Athleta>> callGetAll = AthletaService.getAllAthleta();
        callGetAll.enqueue(new Callback<List<Athleta>>() {
            @Override
            public void onResponse(Call<List<Athleta>> call, Response<List<Athleta>> response) {
                System.out.println("GET ALL ATHLETA \n " +
                        "Code: " + response.code() + "\n"
                        + response.body());
            }

            @Override
            public void onFailure(Call<List<Athleta>> call, Throwable t) {
                System.out.println("GET ALL ATHLETA --> ERROR \n" + t.getMessage());
            }
        });

        // GET PLAYER id
        Call<Athleta> callGetID = AthletaService.getAthletaID(1L);
        callGetID.enqueue(new Callback<Athleta>() {
            @Override
            public void onResponse(Call<Athleta> call, Response<Athleta> response) {
                System.out.println("GET ATHLETA ID \n" +
                        "Code: " + response.code() + "\n"
                        + response.body());
            }

            @Override
            public void onFailure(Call<Athleta> call, Throwable t) {
                System.out.println("GET ALL ATHLETA --> ERROR \n" + t.getMessage());
            }
        });

        // POST
        Athleta athleta = new Athleta();
        Call<Athleta> callCreatePlayer = atletaService.createAthleta(Athleta);
        callCreateAthleta.enqueue(new Callback<Athleta>() {
            @Override
            public void onResponse(Call<Athleta> call, Response<Athleta> response) {
                System.out.println("CREATE ATHLETA \n" +
                        "Code: " + response.code() + "\n"
                        + response.body());
            }

            @Override
            public void onFailure(Call<Athleta> call, Throwable t) {
                System.out.println("CREATE ATHLETA --> ERROR \n" + t.getMessage());
            }
        });

        // DELETE
        Call<Void> callDeletePlayer = AthletaService.deleteAthletaID(6L); //grace, recien creada
        callDeleteAthleta.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("DELETE ATHLETA \n " +
                        "Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("DELETE ATHLETA --> ERROR \n" + t.getMessage());
            }
        });

        // PUT
        // Creo un jugador para no editar ninguno
        Athleta a = new Athleta();
        AthletaService.createAthleta(a);
        a.setName("Modificado");
        Call<Athleta> callUpdatePlayer = AthletaService.updateAthleta(a);
        callUpdateAthleta.enqueue(new Callback<Athleta>() {
            @Override
            public void onResponse(Call<Athleta> call, Response<Athleta> response) {
                System.out.println("PUT ATHLETA \n" +
                        "Code: " + response.code() + "\n"
                        + response.body());
            }

            @Override
            public void onFailure(Call<Athleta> call, Throwable t) {
                System.out.println("PUT --> ERROR\n" + t.getMessage());
            }
        });

    }}