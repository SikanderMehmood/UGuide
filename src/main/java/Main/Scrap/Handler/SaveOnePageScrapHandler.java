package Main.Scrap.Handler;

import Main.Scrap.Model.Information;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Service.SaveOnePageScrapService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveOnePageScrapHandler {

    private SaveOnePageScrapService saveOnePageScrapService;

    public SaveOnePageScrapHandler(SaveOnePageScrapService saveOnePageScrapService) {
        this.saveOnePageScrapService = saveOnePageScrapService;
    }

    //Getting all links from the database to be passed to function that will scrap each link one by one
    public List<LinksModel> getAllLinksForOneUniversityToScrap(String uniname) {

        return saveOnePageScrapService.findAllLinksForOneUniversity(uniname);

    }

    //This function will get one link and will scrap it
    private void scrapOnePage(LinksModel link) {
        try {
            Document doc = Jsoup.connect(link.getLinkUrl()).get();
            String uniName = getUniNamebaseonitsUrl(link.getBaseurl());
            Information info = new Information(uniName, link.getBaseurl(), doc.baseUri());
            saveOnePageScrapService.saveOnePageDetail(info);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    //Function to get university name from NewUniversity collection
    private String getUniNamebaseonitsUrl(String baseurl) {
        return saveOnePageScrapService.findUniName(baseurl);
    }

    //Get all links from above "getAllLinksForOneUniversityToScrap" function and scrap each one by one
    public void saveSinglePageScrappedTextHandler(String uniname) {
        List<LinksModel> allSingleUniRawLinks = getAllLinksForOneUniversityToScrap(uniname);
        for (int i = 0; i < allSingleUniRawLinks.size(); i++) {
            scrapOnePage(allSingleUniRawLinks.get(i));
        }
    }
}
