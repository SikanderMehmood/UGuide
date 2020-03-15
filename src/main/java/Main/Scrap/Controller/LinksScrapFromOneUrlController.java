package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")


public class LinksScrapFromOneUrlController {

    @Autowired
    private ScrapHandler scrapHandler;

    //This method collect all the relevant links that need to be scraped from the whole website.
    // Now each link will be passed in the jsoup.connect() method and the relevant html will be retrived.
    @RequestMapping("/alllinks")
    public void scrapAllLinksFromThatUniversityParentUrl() {
        scrapHandler.deleteAllTheBabyLinksOfTheParentUrlForAllUniversitiesAndThenScrapNewLinksForAllUniversities();
    }
}
