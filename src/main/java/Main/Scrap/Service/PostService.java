package Main.Scrap.Service;

import Main.Scrap.Model.GeneralPost;
import Main.Scrap.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    @Autowired
    public PostRepo postRepo;

    public  PostService(PostRepo postRepo){
        this.postRepo=postRepo;
    }

    public GeneralPost saveNewPost(GeneralPost post) {

     return postRepo.save(post);

    }
}
