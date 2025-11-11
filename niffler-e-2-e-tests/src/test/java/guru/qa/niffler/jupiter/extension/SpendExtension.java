package guru.qa.niffler.jupiter.extension;

import org.junit.jupiter.api.extension.*;

public class SpendExtension implements BeforeEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {

    }


//    благодаря параметрам ниже мы можем в сам тест прокинуть нужные нам параметры
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }
}
