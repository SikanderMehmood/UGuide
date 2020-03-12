package Main.Scrap.Model;

import javax.xml.soap.Text;
import org.jsoup.nodes.Document;

public class Information {
    private String uniName;
    private String UniParentUrl;
    private String docs;

    public Information(String uniName, String uniParentUrl, String docs) {
        this.uniName = uniName;
        UniParentUrl = uniParentUrl;
        this.docs = docs;
    }

    public String getUniName() { return uniName; }
    public void setUniName(String uniName) { this.uniName = uniName; }
    public String getUniParentUrl() { return UniParentUrl; }
    public void setUniParentUrl(String uniParentUrl) { UniParentUrl = uniParentUrl; }
    public String getDocs() { return docs; }
    public void setDocs(String docs) { this.docs = docs; }
}
