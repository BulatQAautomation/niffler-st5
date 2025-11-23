package guru.qa.niffler.api;

import guru.qa.niffler.model.CategoryJson;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CategoryApi {
    @POST("/internal/categories/add")
    Call<CategoryJson> createCategory(@Body CategoryJson categoryJson);

    @GET("/internal/categories/all")
    Call<List<CategoryJson>> getCategories(@Query("username") String username);
}
