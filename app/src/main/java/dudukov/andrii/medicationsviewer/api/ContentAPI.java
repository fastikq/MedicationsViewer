package dudukov.andrii.medicationsviewer.api;

import dudukov.andrii.medicationsviewer.api.response.MedicineList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContentAPI {

    @GET("v1/medicine")
    Observable<MedicineList> getMedicineList();

    @GET("v1/medicine")
    Observable<MedicineList> getResultSearch(@Query("search") String name);

}
