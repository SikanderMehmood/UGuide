package Main.Scrap.Controller;

import Main.Scrap.Handler.PostHandler;
import Main.Scrap.Model.GeneralPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping(value = "/api")
public class SaveNewPostController {

    @Autowired
    public PostHandler postHandler;



    public SaveNewPostController(PostHandler postHandler){
this.postHandler=postHandler;
    }
    @PostMapping("/generalPost")
    public GeneralPost saveNewPost(@Valid @RequestBody GeneralPost post)
    {
        return postHandler.saveNewPost(post);
    }
}
