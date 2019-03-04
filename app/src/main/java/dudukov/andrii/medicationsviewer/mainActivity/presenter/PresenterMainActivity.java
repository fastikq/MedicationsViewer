package dudukov.andrii.medicationsviewer.mainActivity.presenter;

import dudukov.andrii.medicationsviewer.api.ContentAPI;
import dudukov.andrii.medicationsviewer.api.response.MedicineList;
import dudukov.andrii.medicationsviewer.api.retrofit.RetrofitClient;
import dudukov.andrii.medicationsviewer.mainActivity.view.IMainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PresenterMainActivity implements IPresenterMainActivity {

    private Retrofit retrofit;
    private ContentAPI contentAPI;
    private CompositeDisposable compositeDisposable;
    private IMainActivity view;

    public PresenterMainActivity(IMainActivity view) {
        retrofit = RetrofitClient.getInstance();
        contentAPI = retrofit.create(ContentAPI.class);
        compositeDisposable = new CompositeDisposable();
        this.view = view;
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
    public void getMedicineList() {

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
