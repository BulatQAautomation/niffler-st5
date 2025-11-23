package guru.qa.niffler.api;

import guru.qa.niffler.model.SpendJson;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface SpendApi {
    @POST("/internal/spends/add")
    Call<SpendJson> createSpend(@Body SpendJson spendJson);
}