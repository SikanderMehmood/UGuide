package Main.Scrap.Handler;

import Main.Scrap.Model.Information;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Service.SaveOnePageScrapService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveOnePageScrapHandler {

    private SaveOnePageScrapService saveOnePageScrapService;

    public SaveOnePageScrapHandler(SaveOnePageScrapService saveOnePageScrapService) {
        this.saveOnePageScrapService = saveOnePageScrapService;
    }

    //Getting all links from the database to be passed to function that will scrap each link one by one
    private List<LinksModel> getAllLinksForOneUniversityToScrap() {
        return saveOnePageScrapService.findAllLinksForOneUniversity();
    }

    //This function will get one link and will scrap it
    private void scrapOnePage(LinksModel link) {
        try {
            Document doc = Jsoup.connect(link.getLinkUrl()).get();
            String uniName = getUniNamebaseonitsUrl(link.getBaseurl());
            Whitelist whitelist = Whitelist.simpleText();
            whitelist.addTags("div", "a", "li", "ol", "ul", "table", "address", "area", "article", "aside", "audio", "base", "bdi", "bdo", "blockquote", "br", "button", "canvas", "caption", "cite", "col", "colgroup", "data", "datalist");
            String clean = Jsoup.clean(doc.html(), whitelist);
            Information info = new Information(uniName, link.getBaseurl(), clean,link.getUniqueString());
            saveOnePageScrapService.saveOnePageDetail(info);

        } catch (Exception ex) {

        }

    }

    //Function to get university name from NewUniversity collection
    private String getUniNamebaseonitsUrl(String baseurl) {
        return saveOnePageScrapService.findUniName(baseurl);
    }

    //Get all links from above "getAllLinksForOneUniversityToScrap" function and scrap each one by one
    public void saveSinglePageScrappedTextHandler() {
        List<LinksModel> allSingleUniRawLinks = getAllLinksForOneUniversityToScrap();
        for (LinksModel allSingleUniRawLink : allSingleUniRawLinks) {
            scrapOnePage(allSingleUniRawLink);
        }
    }
}
