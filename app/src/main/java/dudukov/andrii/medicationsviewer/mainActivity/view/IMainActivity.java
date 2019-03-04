package dudukov.andrii.medicationsviewer.mainActivity.view;

import java.util.List;

import dudukov.andrii.medicationsviewer.api.models.Medicine;

public interface IMainActivity {
    void fillMedicineList(List<Medicine> medicines);
}
