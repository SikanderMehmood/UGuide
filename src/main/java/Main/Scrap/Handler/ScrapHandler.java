package Main.Scrap.Handler;

import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Service.ScrapService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScrapHandler {
    private ScrapService scrapService;
    @Autowired
    private DataHandler dataHandler;

    public ScrapHandler(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    List<Element> filteredListOfLinks = new ArrayList<>();

    public List<Element> filterLinks(Elements links) {

        for (Element link : links) {
            String urltocheck = link.attr("abs:href");
            Pattern p = Pattern.compile("/Admissions(/|$)|/admission(/|$)|/fee(/|$)|/apply(/|$)|/feestructure(/|$)|/newsannouncement(/|$)|/admissioninfo(/|$)");
            Matcher m = p.matcher(urltocheck);
            if (m.find()) {
                filteredListOfLinks.add(link);
            }

        }
        return filteredListOfLinks;
    }


    public void saveTheLinksThatNeedToBeParsedLater(List<LinksModel> allFilteredlinkswithtext) {

        scrapService.saveTheLinksThatNeedToBeParsedLaterServiceMethod(allFilteredlinkswithtext);

    }

    public void savetheUniinformation(NewUniversity model) {
        scrapService.saveinformationfromservice(model);
    }

    public List<LinksModel> findlinksFromDBForSpecificURL(String value) {
        return scrapService.findingAllLinks(value);
    }

    public void doScrapShit() {

    }

    public void deleteAllTheBabyLinksOfTheParentUrlForAllUniversitiesAndThenScrapNewLinksForAllUniversities() {
        //Get list of All the universities form the database
        //Delete all the subsequent links for one university
        //Scrap all the new links for that one university
        //Repeat the process for all the universities
        List<NewUniversity> allParentUniversities = scrapService.findAllExistingUniversities();
        for (NewUniversity university : allParentUniversities) {
            scrapService.deleteAllTheBabyLinksForThatParentUrl(university.getUrl());
            scrapAllLinksFromThatUniversityParentUrl(university.getUrl());
        }

    }

    public void scrapAllLinksFromThatUniversityParentUrl(String universityParentUrl) {
        List<LinksModel> allFilteredlinkswithtext = new ArrayList<>();
        try {
            Elements links = Jsoup.connect(universityParentUrl).userAgent("Mozilla/5.0").get().select("a[href]");
            List<Element> newLinks = filterLinks(links);
            for (Element newLink : newLinks) {
                LinksModel model = new LinksModel(newLink.attr("abs:href"), trim(newLink.text(), 35), newLink.baseUri());
                allFilteredlinkswithtext.add(model);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        saveTheLinksThatNeedToBeParsedLater(allFilteredlinkswithtext);
    }


    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

    public void getRandomText() {
        try {
            Document doc = Jsoup.connect("http://nu.edu.pk/Admissions/HowToApply").get();
            Elements table = doc.select("div");
            System.out.println(table.text());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getTableData() {
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

    public NewUniversity getUniversity(String id) {
       return dataHandler.getOneUni(id);
    }
}
