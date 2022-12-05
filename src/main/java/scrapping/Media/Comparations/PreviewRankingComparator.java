package scrapping.Media.Comparations;

import scrapping.Media.Preview.Preview;

import java.util.Comparator;

public class PreviewRankingComparator implements Comparator<Preview> {
    @Override
    public int compare(Preview preview, Preview o) {
        if (preview.getPosicionRanking()>o.getPosicionRanking()){
            return 1;
        } else if (preview.getPosicionRanking()<o.getPosicionRanking()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
