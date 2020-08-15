package Main.Scrap.Model;

import org.jsoup.nodes.Document;

public class Information {
    private String uniName;
    private String UniParentUrl;
    private String uniqueString;
    private String docs;

    public Information(String uniName, String uniParentUrl, String docs, String uniqueString) {
        this.uniName = uniName;
        UniParentUrl = uniParentUrl;
        this.uniqueString = uniqueString;
        this.docs = docs;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniParentUrl() {
        return UniParentUrl;
    }

    public void setUniParentUrl(String uniParentUrl) {
        UniParentUrl = uniParentUrl;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }
}
