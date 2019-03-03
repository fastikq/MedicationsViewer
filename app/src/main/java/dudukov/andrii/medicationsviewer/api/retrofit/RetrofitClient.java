package dudukov.andrii.medicationsviewer.api.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit ourInstance;

    private RetrofitClient() {
    }

    public static Retrofit getInstance() {
        if (ourInstance == null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://api.pills-prod.sdh.com.ua/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }
}
