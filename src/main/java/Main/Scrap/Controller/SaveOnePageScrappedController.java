package Main.Scrap.Controller;

import Main.Scrap.Handler.SaveOnePageScrapHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scrapSinglePage")
public class SaveOnePageScrappedController {

    private SaveOnePageScrapHandler saveOnePageScrapHandler;
    //Constructor
    public SaveOnePageScrappedController(SaveOnePageScrapHandler saveOnePageScrapHandler) {
        this.saveOnePageScrapHandler = saveOnePageScrapHandler;
    }

    //This function will get all the links scraped from the index.html page already stored in  database of a specific university
    // and then scrap each page to get all the text map to model class Information and store in the database
    //Steps
    //1)Give ParentUrl of university whose all sublinks will be obtained from the database
    //1) Get all the links from the database
    //Scrap each link and store text in database
    @RequestMapping(value = "/savepage")
    public void saveSinglePageScrappedTextController() {
        saveOnePageScrapHandler.saveSinglePageScrappedTextHandler("http://nu.edu.pk");
    }


}
