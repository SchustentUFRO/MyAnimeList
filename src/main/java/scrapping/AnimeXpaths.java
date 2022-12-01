package scrapping;

public enum AnimeXpaths {
    mediaRowInTop("/html/body/div[1]/div[2]/div[3]/div[2]/div[4]/table/tbody/tr[@class=\"ranking-list\"]"),
    relTitleAnimeInTop("td[2]/div/div[2]"),
    relHrefToAnimeInTop("td[2]/div/div[2]/h3/a"),
    relRankingNumberAnimeInTop("td[1]/span"),
    relScoreNumberAnimeInTop("td[3]/div/span"),
    relEmissionDetailsAnimeInTop("td[2]/div/div[3]"),
    relPreviewCoverImageInTop("td[2]/a/img"),
    animeDetailsBase("/html/body"),
    relAnimeDetailsTitle("div[1]/div[2]/div[3]/div[1]/div/div[1]/div/h1/strong"),
    relAnimeDetailsBroadcasters("//div[@class=\"pb16 broadcasts\"]/*"),
    relAnimeDetailsRowBroadcast("//div[@class=\"broadcast\"]/*"),
    relAnimeDetailsRank("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/strong"),
    relAnimeDetailsScore("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[1]/div"),
    relAnimeDetailsPopularity("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[1]/td/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]/strong"),
    relAnimeDetailsOpeningsTable("//div[@class=\"theme-songs js-theme-songs opnening\"]"),
    relAnimeDetailsOpeningsRows("table/tbody/*"),
    relAnimeDetailsEndingsTable("//div[@class=\"theme-songs js-theme-songs ending\"]"),
    relAnimeDetailsEndingsRows("table/tbody/*"),
    relAnimeDetailsRelatedMediaTable("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/table[@class=\"anime_detail_related_anime\"]/tbody/tr"),

    relAnimeDetailsGetAllRelatedMediaTable("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/table[@class=\"anime_detail_related_anime\"]/*"),
    relAnimeDetailsRelatedMediaHeader("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[3]/td/div[1]/h2"),
    relAnimeDetailsGeneralInfo("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/*"),
    relAnimeImportantGeneralInfoDetails("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/h2[2]"),
    relAnimeEndofImportantGeneralInfoDetails("div[1]/div[2]/div[3]/div[2]/table/tbody/tr/td[1]/div/h2[3]"),
    relAnimeSearchResultRows("/html/body/div[1]/div[2]/div[3]/div[2]/div[6]/table/tbody/tr"),
    relAnimeSearchTitle("td[2]/div[1]"),
    relAnimeSearchHref("td[2]/div[1]/a"),
    relAnimeSearchEmissionType("td[3]"),
    relAnimeSearchScore("td[5]");

    final String xpath;

    AnimeXpaths(String xpath) {
        this.xpath = xpath;
    }
}
