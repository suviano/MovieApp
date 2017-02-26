package marcos.movieapp.data.source.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiMoviesSearch {

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

    public OMDBApiService provideService() {
        String BASE_URL = "http://www.omdbapi.com";
        return provideRetrofit(BASE_URL, provideClient())
            .create(OMDBApiService.class);
    }
}
