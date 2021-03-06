package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Model.Person;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class InfoParentUniController {

    private ScrapHandler scrapHandler;
    public InfoParentUniController(ScrapHandler scrapHandler)
    {
        this.scrapHandler= scrapHandler;
    }

    //For saving new University
    @PostMapping(value = "/saveNewUni")
    public void savenewUniversity(@RequestBody NewUniversity dummyModel)
    {
       // NewUniversity dummyModel = new NewUniversity(UUID.randomUUID().toString(),"karachi university","http://www.uok.edu.pk/","Karachi");
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

