package Main.Scrap.Handler;

import Main.Scrap.Model.GeneralPost;
import Main.Scrap.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostHandler {
    @Autowired
    public PostService postService;

    public PostHandler(PostService postService){
             this.postService=postService;
    }


    public GeneralPost saveNewPost(GeneralPost post) {

      return  postService.saveNewPost(post);

    }
}
