package dudukov.andrii.medicationsviewer.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import dudukov.andrii.medicationsviewer.R;
import dudukov.andrii.medicationsviewer.api.models.Medicine;
import dudukov.andrii.medicationsviewer.descriptionActivity.DescriptionActivity;
import dudukov.andrii.medicationsviewer.mainActivity.adapter.MedicineListAdapter;
import dudukov.andrii.medicationsviewer.mainActivity.presenter.PresenterMainActivity;
import dudukov.andrii.medicationsviewer.mainActivity.view.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity, OnQueryTextListener, View.OnAttachStateChangeListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private SearchView searchView;
    private TextView tvConnectionError;
    private ProgressBar processBar;
    private MaterialButton btnRetry;
    private ListView listMedicine;
    private PresenterMainActivity presenter;
    private MedicineListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMedicine = findViewById(R.id.medicine_list);
        processBar = findViewById(R.id.progress_circular);
        tvConnectionError = findViewById(R.id.tv_connection_error);
        btnRetry = findViewById(R.id.button_retry);
        processBar.setVisibility(View.VISIBLE);
        btnRetry.setOnClickListener(this);
        listMedicine.setOnItemClickListener(this);

        presenter = new PresenterMainActivity(this);

       showMedicineList();
    }

    private void showMedicineList() {
        if(isNetworkAvailable()){
            networkAvailable();
            presenter.getMedicineList();
        }
        else {
            networkNotAvailable();
        }
    }

    private void networkAvailable() {
        listMedicine.setVisibility(View.VISIBLE);
        processBar.setVisibility(View.VISIBLE);
        tvConnectionError.setVisibility(View.INVISIBLE);
        btnRetry.setVisibility(View.INVISIBLE);
    }

    private void networkNotAvailable(){
        listMedicine.setVisibility(View.INVISIBLE);
        processBar.setVisibility(View.INVISIBLE);
        tvConnectionError.setVisibility(View.VISIBLE);
        btnRetry.setVisibility(View.VISIBLE);
    }

    private void startDescriptionActivity(Medicine selectedMedicine) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("id", selectedMedicine.getId());
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.addOnAttachStateChangeListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void fillMedicineList(List<Medicine> medicines) {
        adapter = new MedicineListAdapter(this.getBaseContext(), medicines);
        listMedicine.setAdapter(adapter);
        processBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if(isNetworkAvailable()) {
            networkAvailable();
            presenter.searchInListMedicine(query);
        }
        else {
            networkNotAvailable();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.trim().length() == 0){
            if(isNetworkAvailable()) {
                networkAvailable();
                presenter.searchInListMedicine(newText);
            }
            else {
                networkNotAvailable();
            }
            return false;
        }

        return false;
    }

    @Override
    public void onViewAttachedToWindow(View v) {
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        if(v.getId() == R.id.app_bar_search){
            presenter.getMedicineList();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_retry:
                showMedicineList();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Medicine selectedMedicine = (Medicine) parent.getItemAtPosition(position);
        startDescriptionActivity(selectedMedicine);
    }
}
