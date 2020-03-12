package Main.Scrap.Repository;

import Main.Scrap.Model.LinksModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScrapRepository extends MongoRepository<LinksModel, String> {
}
