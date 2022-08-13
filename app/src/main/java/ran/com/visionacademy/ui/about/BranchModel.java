package ran.com.visionacademy.ui.about;

public class BranchModel {
    private int img;
    private String title , titleDescription;

    public BranchModel(int img, String title, String titleDescription) {
        this.img = img;
        this.title = title;
        this.titleDescription = titleDescription;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }
}
