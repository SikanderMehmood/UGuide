package Main.Scrap.Service;

import Main.Scrap.Model.Information;
import Main.Scrap.Model.LinksModel;
import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Repository.NewUniInfo;
import Main.Scrap.Repository.OnePageDetail;
import Main.Scrap.Repository.ScrapRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveOnePageScrapService {

    private ScrapRepository scrapRepository;
    private NewUniInfo newUniInfo;
    private OnePageDetail onePageDetail;
    MongoTemplate mongoTemplate;


    public SaveOnePageScrapService(ScrapRepository scrapRepository, NewUniInfo newUniInfo, MongoTemplate mongoTemplate,OnePageDetail onePageDetail) {
        this.scrapRepository = scrapRepository;
        this.newUniInfo = newUniInfo;
        this.mongoTemplate = mongoTemplate;
        this.onePageDetail=onePageDetail;
    }

    public List<LinksModel> findAllLinksForOneUniversity() {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("baseurl").is(uniParentUrl));
        List<LinksModel> links = mongoTemplate.findAll(LinksModel.class);
        return links;

    }


    public String findUniName(String baseurl) {
        String uniName;
        Query query = new Query();
        query.addCriteria(Criteria.where("url").is(baseurl));
        NewUniversity info = mongoTemplate.findOne(query, NewUniversity.class);
        if (info == null) {
            uniName = "Giki";
        } else {
            uniName = info.getName();
        }
        return uniName;
    }

    public void saveOnePageDetail(Information info) {
        onePageDetail.save(info);

    }
}
