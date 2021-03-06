package blog.cosmos.home.nuzap;



/**
 * A {@link News} object contains information related to a single post
 * **/
public class News {

    /** Title of the post**/
    private String title;

    /** Content of the post**/
    private String content;

    /** Date of the post**/
    private String date;

    /** Excerpt of the post**/
    private String description;

    /** Tag of the post**/
    private String tags;

    /** Categories of the post**/
    private String categories;

    /** Featured Image of the post**/
    private String feature_image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    /** Returns the title of the post**/
    public String getTitle() {
        return title;
    }

    /** Sets the title of the post**/
    public void setTitle(String title) {
        this.title = title;
    }

    /** Returns the content of the post**/
    public String getContent() {
        return content;
    }

    /** Sets the content of the post**/
    public void setContent(String content) {
        this.content = content;
    }

    /** Returns the Date of the post**/
    public String getDate() {
        return date;
    }

    /** Sets the Date of the post**/
    public void setDate(String date) {
        this.date = date;
    }

    /** Returns the Excerpt of the post**/
    public String getDescription() {
        return description;
    }

    /** Sets the description of the post**/
    public void setDescription(String description) {
        this.description = description;
    }

    /** Returns the tags of the post**/
    public String getTags() {
        return tags;
    }

    /** Sets the tag of the post**/
    public void setTags(String tags) {
        this.tags = tags;
    }


    /** Returns the Categories of the post**/
    public String getCategories() {
        return categories;
    }


    /** Sets the Categories of the post**/
    public void setCategories(String categories) {
        this.categories = categories;
    }


    /** Returns the Featured Image of the post**/
    public String getFeature_image() {
        return feature_image;
    }


    /** Sets the Featured Image of the post**/
    public void setFeature_image(String feature_image) {
        this.feature_image = feature_image;
    }

}
