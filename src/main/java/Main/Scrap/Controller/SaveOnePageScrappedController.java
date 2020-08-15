package Main.Scrap.Controller;

import Main.Scrap.Handler.SaveOnePageScrapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class SaveOnePageScrappedController {

    @Autowired
    private SaveOnePageScrapHandler saveOnePageScrapHandler;

    //This function will get all the links scraped from the index.html page already stored in  database of a specific university
    // and then scrap each page to get all the text map to model class Information and store in the database
    //Steps
    //1)Give ParentUrl of university whose all sublinks will be obtained from the database
    //1) Get all the links from the database
    //Scrap each link and store text in database
    @PostMapping(value = "/scrapsavepage")
    public void saveSinglePageScrappedTextController() {
        saveOnePageScrapHandler.saveSinglePageScrappedTextHandler();
    }


}
