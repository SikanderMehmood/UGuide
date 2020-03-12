package Main.Scrap.Repository;

import Main.Scrap.Model.Information;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OnePageDetail extends MongoRepository<Information,String> {

}
