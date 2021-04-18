package Data;

import Data.Article;

import java.util.List;

public class Classified{

    private Article article;
    private String category;
    private String orginalCategory;

    public Classified(Article article, String category, String orginalCategory) {
        this.article = article;
        this.category = category;
        this.orginalCategory = orginalCategory;
    }

    public String getOrginalCategory() {
        return orginalCategory;
    }


    public void setOrginalCategory(String orginalCategory) {
        this.orginalCategory = orginalCategory;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
