package Main.Scrap.Controller;

import Main.Scrap.Handler.ScrapHandler;
import Main.Scrap.Model.LinksModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/information")
public class LinksScrapFromOneUrlController {

    private ScrapHandler scrapHandler;
    List<LinksModel> allFilteredlinkswithtext = new ArrayList<>();
    public LinksScrapFromOneUrlController(ScrapHandler scrapHandler)
    {
        this.scrapHandler= scrapHandler;
    }




//This method collect all the relevant links that need to be scraped from the whole website. Now each link will be passed in the jsoup.connect() method and the relevant html will be retrived.
    @RequestMapping("/alllinks")
    public List<LinksModel> getAllLinksOfThePage()
    {
        try {
            Document doc = Jsoup.connect("https://www.giki.edu.pk/").get();
            Elements links = doc.select("a[href]");
            List<Element> newLinks=  scrapHandler.filterLinks(links);
            for(int i=0;i<newLinks.size();i++)
            {
                LinksModel model = new LinksModel(newLinks.get(i).attr("abs:href"),trim(newLinks.get(i).text(),35),newLinks.get(i).baseUri());
                allFilteredlinkswithtext.add(model);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        scrapHandler.saveTheLinksThatNeedToBeParsedLater(allFilteredlinkswithtext);
        return  allFilteredlinkswithtext;
    }


    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


}
