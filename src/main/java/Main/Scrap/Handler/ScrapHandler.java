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
import java.util.UUID;
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
            Pattern p = Pattern.compile("/Admissions(/|$)|/admission(/|$)|/fee(/|$)|/apply(/|$)|/feestructure(/|$)|/newsannouncement(/|$)|/admissioninfo(/|$)|/scholerships(/|$)|/Scholerships(/|$)");
            Pattern p1 = Pattern.compile("/downloads");
            Pattern p2 = Pattern.compile("/faqs");
            Pattern p3 = Pattern.compile("/students-societies");
            Pattern p4 = Pattern.compile("/elearning");
            Pattern p5 = Pattern.compile("/scholarships");
            Pattern p6 = Pattern.compile("/iub-general-notices");
            Pattern p7 = Pattern.compile("/iub-results");
            Pattern p8 = Pattern.compile("/news-update");
            Pattern p9 = Pattern.compile("/prospective-students");
            Pattern p10 = Pattern.compile("/admissions");
            Pattern p11 = Pattern.compile("/faculty-staff");
            Pattern p12 = Pattern.compile("/directorate-of-affiliation");
            Pattern p13 = Pattern.compile("/admissions-open-livestock-assistant-diploma-2-years");
            Pattern p14 = Pattern.compile("/schedule-iub-online-admission-test-for-engineering");
            Pattern p15 = Pattern.compile("/student-support");
            Pattern p16 = Pattern.compile("/controller-examination");
            Pattern p27 = Pattern.compile("/student-life");
            Pattern p38 = Pattern.compile("/election-schedule-for-registered-graduate-as-members-university-senate");
            Pattern p48 = Pattern.compile("/treasurer-office");
            Matcher m = p.matcher(urltocheck);
            Matcher m1 = p1.matcher(urltocheck);
            Matcher m2 = p2.matcher(urltocheck);
            Matcher m3 = p3.matcher(urltocheck);
            Matcher m4 = p4.matcher(urltocheck);
            Matcher m5 = p5.matcher(urltocheck);
            Matcher m6 = p6.matcher(urltocheck);
            Matcher m7 = p7.matcher(urltocheck);
            Matcher m8 = p8.matcher(urltocheck);
            Matcher m9 = p9.matcher(urltocheck);
            Matcher m10 = p10.matcher(urltocheck);
            Matcher m11 = p11.matcher(urltocheck);
            Matcher m12 = p12.matcher(urltocheck);
            Matcher m13 = p13.matcher(urltocheck);
            Matcher m14 = p14.matcher(urltocheck);
            Matcher m15 = p15.matcher(urltocheck);
            Matcher m16 = p16.matcher(urltocheck);
            Matcher m17 = p27.matcher(urltocheck);
            Matcher m18 = p38.matcher(urltocheck);
            Matcher m19 = p48.matcher(urltocheck);

            if (m.find() || m1.find() || m2.find() || m3.find() || m4.find() || m5.find()|| m6.find()|| m7.find()|| m8.find()|| m9.find()|| m10.find()|| m11.find()|| m12.find()|| m13.find()|| m14.find()|| m15.find()|| m16.find() || m17.find() || m18.find() || m19.find()){
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
                LinksModel model = new LinksModel(UUID.randomUUID().toString(),newLink.attr("abs:href"), trim(newLink.text(), 35), newLink.baseUri(),UUID.randomUUID().toString());
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
