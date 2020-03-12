package Main.Scrap.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NewUniversity {

    String name;
    String url;
    String location;

public  NewUniversity(String name, String url, String location)
{
    this.name=name;
    this.location=location;
    this.url=url;
}

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    public String getUrl() { return url;}
    public void setUrl(String url) { this.url = url;}
    public String getLocation() { return location;}
    public void setLocation(String location) { this.location = location;}
}
