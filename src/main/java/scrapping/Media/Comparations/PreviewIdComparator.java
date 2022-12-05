package scrapping.Media.Comparations;

import scrapping.Media.Preview.Preview;

import java.util.Comparator;

public class PreviewIdComparator implements Comparator<Preview> {
    @Override
    public int compare(Preview preview, Preview o) {
        if (preview.getId()>o.getId()){
            return 1;
        } else if (preview.getId()<o.getId()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
