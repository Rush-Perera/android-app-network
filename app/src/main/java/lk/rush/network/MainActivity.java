package lk.rush.network;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;

import java.util.List;

import lk.rush.network.interceptor.RequestInterceptor;
import lk.rush.network.model.AuthRequest;
import lk.rush.network.model.AuthResponse;
import lk.rush.network.model.Product;
import lk.rush.network.model.Repo;
import lk.rush.network.service.EShopService;
import lk.rush.network.service.GitHubService;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jiat.online/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();


//        EShopService eShopService = retrofit.create((EShopService.class));
//        Call<AuthResponse> auth = eShopService.auth(new AuthRequest("admin@eshop.com", "password"));
//
//        auth.enqueue(new Callback<AuthResponse>() {
//            @Override
//            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
//                Log.i(TAG,"Token: "+response.body().getToken());
//                if (response.isSuccessful()) {
//                    getPreferences(MODE_PRIVATE).edit().putString("token", response.body().getToken()).commit();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                Log.e(TAG,"Error: "+ t.getMessage());
//            }
//        });
//
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String token = getPreferences(MODE_PRIVATE).getString("token","");
//                String accessToken = "Bearer " + token;
//
//                Call<List<Product>> productsCall = eShopService.getAllProducts(accessToken);
//                Request request = productsCall.request();
//                Headers headers = request.headers();
//                Log.v(TAG,headers.toString());
//
//                productsCall.enqueue(new Callback<List<Product>>() {
//                    @Override
//                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                        if (response.isSuccessful()) {
//                            Log.d(TAG,"Success");
//                            List<Product> products = response.body();
//                            products.forEach(p->{
//                                Log.i(TAG,"Product: "+p.getTitle());
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Product>> call, Throwable t) {
//                        Log.e(TAG,"Error: "+ t.getMessage());
//                    }
//
//                });
//            }
//        });

        String token = getPreferences(MODE_PRIVATE).getString("token","");
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new RequestInterceptor(token)).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jiat.online/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        EShopService eShopService = retrofit.create((EShopService.class));
        Call<JsonObject> auth = eShopService.auth(new AuthRequest("admin@eshop.com", "password"));

        auth.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    getPreferences(MODE_PRIVATE).edit().putString("token", response.body().get("token").getAsString()).commit();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG,"Error: "+ t.getMessage());
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<List<Product>> productsCall = eShopService.getAllProducts();
                Request request = productsCall.request();
                Headers headers = request.headers();
                Log.v(TAG,headers.toString());

                productsCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG,"Success");
                            List<Product> products = response.body();
                            products.forEach(p->{
                                Log.i(TAG,"Product: "+p.getTitle());
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.e(TAG,"Error: "+ t.getMessage());
                    }

                });
            }
        });

    }
}