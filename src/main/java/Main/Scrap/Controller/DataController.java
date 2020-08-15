package Main.Scrap.Controller;


import Main.Scrap.Handler.DataHandler;
import Main.Scrap.Model.NewUniversity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
