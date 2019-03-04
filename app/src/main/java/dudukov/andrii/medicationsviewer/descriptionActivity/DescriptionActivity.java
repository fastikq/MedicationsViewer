package dudukov.andrii.medicationsviewer.descriptionActivity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dudukov.andrii.medicationsviewer.R;
import dudukov.andrii.medicationsviewer.api.models.Medicine;
import dudukov.andrii.medicationsviewer.databinding.ActivityDescriptionBinding;
import dudukov.andrii.medicationsviewer.descriptionActivity.presenter.PresenterDescriptionActivity;
import dudukov.andrii.medicationsviewer.descriptionActivity.view.IDescriptionActivity;

public class DescriptionActivity extends AppCompatActivity implements IDescriptionActivity {

    private PresenterDescriptionActivity presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.description_activity_title);

        presenter = new PresenterDescriptionActivity(this);

        showDescriptionOfMedicine();
    }

    private void showDescriptionOfMedicine() {
        int id = getIntent().getIntExtra("id", 0);
        presenter.getMedicine(id);
    }

    @Override
    public void fillDescriptionOfMedicine(Medicine medicine) {

        ActivityDescriptionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_description);
        binding.setMedicine(medicine);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
