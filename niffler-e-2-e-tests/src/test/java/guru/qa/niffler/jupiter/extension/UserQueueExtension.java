package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.extension.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

// Любой тест проходит через него
public class UserQueueExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {

    // обеспечивает атомарную работу, другой случайно не сможет достать юзера если другой поток уже занял его
    private static final Queue<UserJson> USERS = new ConcurrentLinkedDeque<>();

    static {
        //todo сюда добавить еще юзеров для очереди
        USERS.add(UserJson.simpleUser("BULAT", "12345"));
    }


    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        UserJson userForTest = null;
        while (userForTest == null) {
            userForTest = USERS.poll();
        }
        // todo
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {

    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }
}
