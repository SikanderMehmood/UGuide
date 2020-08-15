package Main.Scrap.Model;

import org.springframework.data.annotation.Id;

public class LinksModel {
    @Id
    String id;
    String linkUrl;
    String uniqueString;
    String linkText;
    String baseurl;


    public LinksModel(String id, String linkUrl, String linkText, String baseurl, String uniqueString) {
        this.id = id;
        this.linkText = linkText;
        this.uniqueString = uniqueString;
        this.linkUrl = linkUrl;
        this.baseurl = baseurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
}
