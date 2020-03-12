package Main.Scrap.Handler;

import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Service.ScrapService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScrapHandler {
    ScrapService scrapService;

   public  ScrapHandler (ScrapService scrapService)
   {
       this.scrapService =scrapService;
   }

    List<Element> filteredListOfLinks = new ArrayList<>();


    public List<Element> filterLinks(Elements links) {

        for(int i=0;i<links.size();i++) {
           String  urltocheck= links.get(i).attr("abs:href");
            Pattern p = Pattern.compile("/Admissions(/|$)");
            Matcher m = p.matcher(urltocheck);
            if(m.find())
            {
                filteredListOfLinks.add(links.get(i));
            }

        }
        return filteredListOfLinks;
    }

    public void scrapEachLinktoGetHtmlDataToParse(List<LinksModel> allFilteredlinkswithtext) {
        //allFilteredlinkswithtext.get()
    }

    public void saveTheLinksThatNeedToBeParsedLater(List<LinksModel> allFilteredlinkswithtext) {

        scrapService.saveTheLinksThatNeedToBeParsedLaterServiceMethod(allFilteredlinkswithtext);

    }


    public  void getTableData()
    {
        try {
            //Document doc = Jsoup.connect("http://nu.edu.pk/Degree-Programs").get();
            Document doc = Jsoup.connect("http://nu.edu.pk/Admissions/Schedule").get();
            Element table = doc.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                System.out.println(cols.text());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void savetheUniinformation(NewUniversity model) {

        scrapService.saveinformationfromservice(model);
    }

    public  void getRandomText()
    {
        try {
            Document doc = Jsoup.connect("http://nu.edu.pk/Admissions/HowToApply").get();
            Elements table = doc.select("div");
            System.out.println(table.text());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<LinksModel> findlinksFromDBForSpecificURL(String value) {

        return  scrapService.findingAllLinks(value);
    }
}
