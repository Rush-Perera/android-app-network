package lk.rush.network.service;

import com.google.gson.JsonObject;

import java.util.List;

import lk.rush.network.model.AuthRequest;
import lk.rush.network.model.AuthResponse;
import lk.rush.network.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EShopService {
    @POST("auth/login")
    Call<JsonObject> auth(@Body AuthRequest authRequest);

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id, @Header("Authorization")String token);
}
