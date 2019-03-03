package dudukov.andrii.medicationsviewer.api.response;

import java.util.List;
import dudukov.andrii.medicationsviewer.api.models.Medicine;

public class MedicineList {

    private String count;

    private String next;

    private String previous;

    private List<Medicine> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Medicine> getResults() {
        return results;
    }

    public void setResults(List<Medicine> results) {
        this.results = results;
    }
}
