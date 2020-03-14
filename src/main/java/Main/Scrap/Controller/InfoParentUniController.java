package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/save")
public class InfoParentUniController {

    private ScrapHandler scrapHandler;
    public InfoParentUniController(ScrapHandler scrapHandler)
    {
        this.scrapHandler= scrapHandler;
    }

//Information in here will be passed in the requestBody that will be recieved from the fornt end
    @PutMapping(value = "/info")
    public void saveNewUniInformation()
    {
        //Dummy data to save for now
        NewUniversity dummyModel = new NewUniversity("GIKI","https://www.giki.edu.pk/","Swabi,Topi");
        scrapHandler.savetheUniinformation(dummyModel);
    }

    //This uni url will be fetched from database based on which univresity site will be scrapped
    //it will give all urls to some other calling function which then will perform scrapping on that page.
    @GetMapping(value = "/getlinks")
    public List<LinksModel> getAllLinksFromDB(String val)
    {

      List<LinksModel> allLinks=  scrapHandler.findlinksFromDBForSpecificURL("http://nu.edu.pk");
      return  allLinks;
    }

}

