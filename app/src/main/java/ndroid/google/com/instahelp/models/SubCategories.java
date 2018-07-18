package ndroid.google.com.instahelp.models;

public class SubCategories{
    String name,image;
    boolean cb;

    public  SubCategories(String name,String image,boolean cb)
    {
        this.name=name;
        this.image=image;
        this.cb=cb;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCheckbox(boolean checkbox) {
        this.cb = checkbox;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Boolean getCheckbox() {
        return cb;
    }

    public String getImage() {
        return image;
    }
}
