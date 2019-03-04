package dudukov.andrii.medicationsviewer.descriptionActivity.presenter;

import dudukov.andrii.medicationsviewer.api.ContentAPI;
import dudukov.andrii.medicationsviewer.api.models.Medicine;
import dudukov.andrii.medicationsviewer.api.retrofit.RetrofitClient;
import dudukov.andrii.medicationsviewer.descriptionActivity.view.IDescriptionActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PresenterDescriptionActivity implements IPresenterDescriptionActivity {

    private Retrofit retrofit;
    private ContentAPI contentAPI;
    private CompositeDisposable compositeDisposable;
    private IDescriptionActivity view;

    public PresenterDescriptionActivity(IDescriptionActivity view) {
        retrofit = RetrofitClient.getInstance();
        contentAPI = retrofit.create(ContentAPI.class);
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    @Override
    public void getMedicine(int id) {
        compositeDisposable.add(contentAPI.getMedicine(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Medicine>() {
                    @Override
                    public void accept(Medicine medicine) {
                        view.fillDescriptionOfMedicine(medicine);
                    }
                }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
