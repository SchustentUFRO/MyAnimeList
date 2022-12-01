package scrapping;

public enum MangaXpaths {
    mediaRowInTop("/html/body/div[1]/div[2]/div[3]/div[2]/div[4]/table/tbody/tr[@class=\"ranking-list\"]"),

    relTitleMangaInTop("td[2]/div/h3"),
    relHrefToMangaInTop("td[2]/div/h3/a"),
    relRankingNumberMangaInTop("td[1]/span"),
    relScoreNumberMangaInTop("td[3]/div/span"),
    relFormatDetailsMangaInTop("td[2]/div/div[2]"),
    relPreviewCoverImageInTop("td[2]/a/img"),
    mangaDetailsBase("/html/body"),
    relMangaDetailsTitle("div[1]/div[2]/div[3]/div[1]/div/div[1]/div/h1/strong"),
    relMangaDetailsRank("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/strong"),
    relMangaDetailsScore("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[1]/div"),
    relMangaDetailsPopularity("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]/strong"),
    relMangaDetailsRelatedMediaTable("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/table[@class=\"anime_detail_related_anime\"]/tbody/tr"),

    relMangaDetailsGetAllRelatedMediaTable("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/table[@class=\"anime_detail_related_anime\"]/*"),
    relMangaDetailsRelatedMediaHeader("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/div[1]/h2"),
    relMangaDetailsGeneralInfo("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/*"),
    relMangaImportantGeneralInfoDetails("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/h2[2]"),
    relMangaEndofImportantGeneralInfoDetails("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/h2[3]");

    final String xpath;

    MangaXpaths(String xpath) {
        this.xpath = xpath;
    }
}
