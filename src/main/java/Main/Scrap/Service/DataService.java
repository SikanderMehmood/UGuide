package Main.Scrap.Service;

import Main.Scrap.Model.NewUniversity;
import Main.Scrap.Repository.DataRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataService {
    public DataRepository dataRepository;

    public  DataService(DataRepository dataRepository){
        this.dataRepository=dataRepository;

    }
    public List<NewUniversity> getAllUniversities() {
        return dataRepository.findAll();
    }
}
