package Main.Scrap.Repository;

import Main.Scrap.Model.GeneralPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<GeneralPost,String> {
}
