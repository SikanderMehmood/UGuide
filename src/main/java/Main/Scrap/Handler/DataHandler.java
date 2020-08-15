package Main.Scrap.Handler;


import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Service.DataService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataHandler {

    public DataService dataService;

    public  DataHandler(DataService dataService){
        this.dataService=dataService;
    }

    public List<NewUniversity> getAllUniversities() {

      return   dataService.getAllUniversities();
    }
}
