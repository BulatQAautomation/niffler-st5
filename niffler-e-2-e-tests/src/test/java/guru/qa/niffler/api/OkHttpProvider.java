package guru.qa.niffler.api;

import okhttp3.OkHttpClient;

public final class OkHttpProvider {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .build();

    public static OkHttpClient client() {
        return CLIENT;
    }
}


