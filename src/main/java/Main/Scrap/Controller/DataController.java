package Main.Scrap.Controller;


import Main.Scrap.Handler.DataHandler;
import Main.Scrap.Model.NewUniversity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class DataController {
    public DataHandler dataHandler;

    public  DataController(DataHandler dataHandler){
        this.dataHandler=dataHandler;
    }

    @GetMapping(value = "/allUniversities")
    public List<NewUniversity> getAllUniversities(){
        return  dataHandler.getAllUniversities();
    }
//    @GetMapping(value = "/singleUni/{id}")
//    public  NewUniversity getOneUni(@PathVariable String id){
//        return  dataHandler.getOneUni(id);
//    }
}
