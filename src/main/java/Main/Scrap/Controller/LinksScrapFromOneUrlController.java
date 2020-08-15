package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Repository.ScrapRepository;
import Main.Scrap.Service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")


public class LinksScrapFromOneUrlController {

    @Autowired
    private ScrapHandler scrapHandler;
    private ScrapRepository scrapRepository;

    //This method collect all the relevant links that need to be scraped from the whole website.
    // Now each link will be passed in the jsoup.connect() method and the relevant html will be retrived.
    @RequestMapping("/alllinks")
    public void scrapAllLinksFromThatUniversityParentUrl() {
        scrapHandler.deleteAllTheBabyLinksOfTheParentUrlForAllUniversitiesAndThenScrapNewLinksForAllUniversities();
    }

    @GetMapping(value = "/singleUni/{id}")
    public List<LinksModel> getAllLinksForOneSingleUni(@PathVariable String id)
    {
        NewUniversity newUniversity =  scrapHandler.getUniversity(id);
          return  scrapHandler.findlinksFromDBForSpecificURL(newUniversity.getUrl());

    }

}
