package proj1.featuresVectors;

import java.util.List;

public class AuthorName extends Feature {

    private List<String> authorNames;

    public AuthorName(List<String> authorNames) {
        super(true);
        this.authorNames = authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }


}
