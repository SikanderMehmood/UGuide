package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/save")
public class InfoParentUniController {

    private ScrapHandler scrapHandler;
    public InfoParentUniController(ScrapHandler scrapHandler)
    {
        this.scrapHandler= scrapHandler;
    }

    //For saving new University
    @PostMapping(value = "/info")
    public void savenewUniversity()
    {
        NewUniversity dummyModel = new NewUniversity(UUID.randomUUID().toString(),"Fast","https://www.nu.edu.pk/","Lahore");
        scrapHandler.savetheUniinformation(dummyModel);
    }

    //This uni url will be fetched from database based on which univresity site will be scrapped
    //it will give all urls to some other calling function which then will perform scrapping on that page.
    @GetMapping(value = "/getlinks")
    public List<LinksModel> getAllLinksFromDB(String ParentUrl)
    {
      List<LinksModel> allLinks=  scrapHandler.findlinksFromDBForSpecificURL("https://www.giki.edu.pk/");
      return  allLinks;
    }

}

