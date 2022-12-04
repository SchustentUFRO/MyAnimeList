package scrapping.Media;

import scrapping.Media.DetailedMedia.AnimeMedia;
import scrapping.Media.DetailedMedia.MangaMedia;
import scrapping.Media.DetailedMedia.Media;
import scrapping.Media.Preview.AnimePreviewSearch;
import scrapping.Media.Preview.AnimePreview;
import scrapping.Media.Preview.MangaPreviewSearch;
import scrapping.Media.Preview.MangaPreview;

import java.util.ArrayList;
import java.util.List;

public class MediaManager {
    protected static List<AnimePreview> animePreviewsTopList =new ArrayList<>();
    protected static List<MangaPreview> mangaPreviewsTopList=new ArrayList<>();
    protected static List<AnimePreview> animePreviewsSearchList =new ArrayList<>();
    protected static List<MangaPreview> mangaPreviewsSearchList=new ArrayList<>();

    protected static  List<AnimeMedia> animeDetailsList=new ArrayList<>();
    protected static List<MangaMedia> mangaDetailsList=new ArrayList<>();
    public static void agregarAnimePreviewTopALista(AnimePreview preview){
        animePreviewsTopList.add(preview);
    }

    public static void agregarAnimePreviewTopALista(MangaPreview preview){
        mangaPreviewsTopList.add(preview);
    }

    public static void agregarAnimeDetalle(AnimeMedia anime){
        animeDetailsList.add(anime);
    }
    public static void agregarAnimeDetalle(MangaMedia anime){
        mangaDetailsList.add(anime);
    }

}
