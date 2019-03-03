package dudukov.andrii.medicationsviewer.mainactivity.presenter;

import dudukov.andrii.medicationsviewer.api.ContentAPI;
import dudukov.andrii.medicationsviewer.api.response.MedicineList;
import dudukov.andrii.medicationsviewer.api.retrofit.RetrofitClient;
import dudukov.andrii.medicationsviewer.mainactivity.view.IMainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PresenterMainActivity implements IPresenterMainActivity {

    private static PresenterMainActivity ourInstance;
    private Retrofit retrofit;
    private ContentAPI contentAPI;
    private CompositeDisposable compositeDisposable;
    private IMainActivity view;

    private PresenterMainActivity(IMainActivity view) {
        retrofit = RetrofitClient.getInstance();
        contentAPI = retrofit.create(ContentAPI.class);
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    public static PresenterMainActivity getInstance(IMainActivity view) {
        if (ourInstance == null) {

            ourInstance = new PresenterMainActivity(view);
            return ourInstance;
        }
        return ourInstance;
    }

    @Override
    public void searchInListMedicine(String name) {
        compositeDisposable.add(contentAPI.getResultSearch(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MedicineList>() {
                    @Override
                    public void accept(MedicineList containerModels) {
                        view.fillMedicineList(containerModels.getResults());
                    }
                }));
    }

    @Override
    public void onActivityCreated() {

        compositeDisposable.add(contentAPI.getMedicineList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MedicineList>() {
                    @Override
                    public void accept(MedicineList containerModels) {
                        view.fillMedicineList(containerModels.getResults());
                    }
                }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
