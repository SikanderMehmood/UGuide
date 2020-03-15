package Main.Scrap.Service;

import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Repository.NewUniInfo;
import Main.Scrap.Repository.ScrapRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScrapService {

    ScrapRepository scrapRepository;
    NewUniInfo newUniInfo;
    MongoTemplate mongoTemplate;

    public ScrapService(ScrapRepository scrapRepository, NewUniInfo newUniInfo, MongoTemplate mongoTemplate) {
        this.scrapRepository = scrapRepository;
        this.newUniInfo = newUniInfo;
        this.mongoTemplate = mongoTemplate;
    }


    public void saveTheLinksThatNeedToBeParsedLaterServiceMethod(List<LinksModel> allFilteredlinkswithtext) {

        for (int i = 0; i < allFilteredlinkswithtext.size(); i++) {
            if (!checkSpecificUrlExistInDB(allFilteredlinkswithtext.get(i).getLinkUrl())) {
                LinksModel saveNewLink = allFilteredlinkswithtext.get(i);
                scrapRepository.save(saveNewLink);
            }
        }


    }

    public List<NewUniversity> findAllExistingUniversities() {
      return   newUniInfo.findAll();
    }

    public void saveinformationfromservice(NewUniversity model) {
        newUniInfo.save(model);
    }

    //This function will check if a link from a University website (Ex : http://nu.edu.pk/) already exist in the database. Like if a link (Ex: http://nu.edu.pk/Admissions/HowToApply) already exist in the database
    public Boolean checkSpecificUrlExistInDB(String url) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(("linkUrl")).is(url));
            LinksModel result = mongoTemplate.findOne(query, LinksModel.class);
            if (result == null)
                return false;

        } catch (Exception ex) {
            System.out.println(ex);

        }
        return true;
    }

    //This function will get all the single university links based on university Parent url(baseUrl)
    public List<LinksModel> findingAllLinks(String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("baseurl").is(value));
        List<LinksModel> links = mongoTemplate.find(query, LinksModel.class);
        return links;
    }

    public void deleteAllTheBabyLinksForThatParentUrl(String url) {
        Query query = new Query();
        query.addCriteria(Criteria.where("baseurl").is(url));
        mongoTemplate.remove(query,LinksModel.class);
    }
}
