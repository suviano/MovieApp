package marcos.movieapp.data.source.remote;

import marcos.movieapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiMoviesSearch {
    // public static String BASE_URL = "http://www.omdbapi.com";

    private Retrofit provideRetrofit(String BASE_URL, OkHttpClient client) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    private OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();
    }

    OMDBApiService provideService() {
        String BASE_URL;
        if (BuildConfig.API_HOST.isEmpty()) {
            BASE_URL = "http://www.omdbapi.com";
        } else {
            BASE_URL = BuildConfig.API_HOST;
        }
        return provideRetrofit(BASE_URL, provideClient())
            .create(OMDBApiService.class);
    }
}
