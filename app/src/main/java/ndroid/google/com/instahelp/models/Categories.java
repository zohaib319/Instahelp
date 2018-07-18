package ndroid.google.com.instahelp.models;

import java.util.ArrayList;

public class Categories {
    String name;
    ArrayList<SubCategories> subCategories;
    public Categories()
    {

    }
    public Categories(String name)
    {
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubCategories(ArrayList<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SubCategories> getSubCategories() {
        return subCategories;
    }
}

