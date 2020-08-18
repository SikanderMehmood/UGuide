package Main.Scrap.Model;

public class GeneralPost {

    public String postName;
    public String postShort;
    public String postDis;

    public  GeneralPost(){

    }

    public GeneralPost(String postName,String postShort, String postDis){
        this.postDis=postDis;
        this.postShort=postShort;
        this.postName=postName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostShort() {
        return postShort;
    }

    public void setPostShort(String postShort) {
        this.postShort = postShort;
    }

    public String getPostDis() {
        return postDis;
    }

    public void setPostDis(String postDis) {
        this.postDis = postDis;
    }
}
