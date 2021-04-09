package proj1.featuresVectors;

import proj1.Data.Article;

public interface FeatureExtractor {
    FeatureVector extract(Article document);
}
