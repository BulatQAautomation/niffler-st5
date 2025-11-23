package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.CategoryApi;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.api.OkHttpProvider;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.platform.commons.support.AnnotationSupport;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class CategoryExtension implements BeforeEachCallback, ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);

    private final Retrofit retrofit = new Retrofit.Builder()
            .client(OkHttpProvider.client())
            .baseUrl("http://127.0.0.1:8093/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final CategoryApi categoryApi = retrofit.create(CategoryApi.class);

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                Category.class).ifPresent(
                category -> {
                    CategoryJson categoryJson = new CategoryJson(
                            null,
                            category.userName(),
                            category.category()
                    );
                    try {
                        Response<List<CategoryJson>> responseGetCategories = categoryApi.getCategories(category.userName()).execute();
                        List<CategoryJson> existing = responseGetCategories.body();
                        boolean hasCategory = existing != null && existing.stream()
                                .anyMatch(cat -> category.category().equals(cat.category()));
                        if (!hasCategory) {
                            Response<CategoryJson> response = categoryApi.createCategory(categoryJson).execute();
                            CategoryJson result = response.body() != null ? response.body() : categoryJson;
                            extensionContext.getStore(NAMESPACE).put("category", result);
                            if (!response.isSuccessful()) {
                                throw new IllegalStateException("Couldn't create category: " + response.code() + " " + response.message());
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    //    благодаря параметрам ниже мы можем в сам тест прокинуть нужные нам параметры
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get("category");
    }

}
