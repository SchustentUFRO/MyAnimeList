package scrapping.Media;

import scrapping.Media.DetailedMedia.Media;
import scrapping.Media.Preview.AnimePreviewSearch;
import scrapping.Media.Preview.AnimePreviewTop;
import scrapping.Media.Preview.MangaPreviewSearch;
import scrapping.Media.Preview.MangaPreviewTop;

import java.util.ArrayList;
import java.util.List;

public class MediaManager {
    protected static List<AnimePreviewTop> animePreviewsTopList =new ArrayList<>();
    protected static List<MangaPreviewTop> mangaPreviewsTopList=new ArrayList<>();
    protected static List<AnimePreviewSearch> animePreviewsSearchList =new ArrayList<>();
    protected static List<MangaPreviewSearch> mangaPreviewsSearchList=new ArrayList<>();

    protected static  List<Media> animeDetailsList=new ArrayList<>();
    protected static List<Media> mangaDetailsList=new ArrayList<>();
    public static void agregarAnimePreviewTopALista(AnimePreviewTop preview){
        animePreviewsTopList.add(preview);
    }

    public static void agregarAnimePreviewTopALista(MangaPreviewTop preview){
        mangaPreviewsTopList.add(preview);
    }
    public static void agregarAnimePreviewTopALista(AnimePreviewSearch preview){
        animePreviewsSearchList.add(preview);
    }
    public static void agregarAnimePreviewTopALista(MangaPreviewSearch preview){
        mangaPreviewsSearchList.add(preview);
    }

}
