package dudukov.andrii.medicationsviewer.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dudukov.andrii.medicationsviewer.R;
import dudukov.andrii.medicationsviewer.api.models.Medicine;

public class MedicineListAdapter extends BaseAdapter {

    private Context context;
    private List<Medicine> medicines;

    public MedicineListAdapter(Context context, List<Medicine> medicines) {
        this.context = context;
        this.medicines = medicines;
    }

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Override
    public Object getItem(int position) {
        return medicines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater view;
            view = LayoutInflater.from(context);
            convertView = view.inflate(R.layout.medicine_list_item, parent, false);
        }

        Medicine medicine = getMedicine(position);

        TextView tradeLabel = convertView.findViewById(R.id.trade_label);
        TextView manufacturerName = convertView.findViewById(R.id.manufacturer_name);


        tradeLabel.setText(medicine.getTradeLabel().getName());
        try {
            manufacturerName.setText(medicine.getManufacturer().getName());
        }
        catch (NullPointerException e){
            manufacturerName.setText(R.string.not_specified);
        }
        return convertView;
    }

    private Medicine getMedicine(int position) {
        return (Medicine) getItem(position);
    }
}
