package Main.Scrap.Model;

public class LinksModel {
    String linkUrl;
    String linkText;
    String baseurl;


    public LinksModel(String linkUrl, String linkText, String baseurl) {
        this.linkText = linkText;
        this.linkUrl = linkUrl;
        this.baseurl = baseurl;
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
