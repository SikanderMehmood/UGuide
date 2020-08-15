package Main.Scrap.Repository;

import Main.Scrap.Model.NewUniversity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<NewUniversity,String>{

}
