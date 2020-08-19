package Main.Scrap.Service;

import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Repository.DataRepository;
import Main.Scrap.Repository.NewUniInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DataService {
    public DataRepository dataRepository;
    public NewUniInfo newUniInfo;
    public MongoTemplate mongoTemplate;

    public DataService(DataRepository dataRepository, NewUniInfo newUniInfo) {
        this.dataRepository = dataRepository;
        this.newUniInfo = newUniInfo;


    }

    public List<NewUniversity> getAllUniversities() {
        return dataRepository.findAll();
    }

    public NewUniversity getOneUni(String id) {
         NewUniversity newuni = newUniInfo.findById(id).get();
         return new NewUniversity(newuni.getName(),newuni.getUrl(),newuni.getLocation());

    }
}
