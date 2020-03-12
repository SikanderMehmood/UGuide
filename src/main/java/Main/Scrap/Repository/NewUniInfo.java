package Main.Scrap.Repository;

import Main.Scrap.Model.NewUniversity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewUniInfo extends MongoRepository<NewUniversity,String> {
}
