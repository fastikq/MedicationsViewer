package dudukov.andrii.medicationsviewer.mainActivity.presenter;

public interface IPresenterMainActivity {

    void searchInListMedicine(String name);
    void getMedicineList();
    void onDestroy();
}
