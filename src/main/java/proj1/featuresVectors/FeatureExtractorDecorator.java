package proj1.featuresVectors;

import proj1.Data.Article;

import java.util.ArrayList;
import java.util.List;

public class FeatureExtractorDecorator implements FeatureExtractor {

    private List<FeatureExtractor> extractors = new ArrayList<>();

    @Override
    public FeatureVector extract(final Article document) {
        FeatureVector featureVector = new FeatureVector(document);

        for(FeatureExtractor it : extractors){
           featureVector.addAll(it.extract(document));
        }

        /*extractors.stream()
                .forEach((extractor) -> featureVector.addAll(extractor.extract(document)));*/

        return featureVector;
    }

    public void addExtractor(final FeatureExtractor extractor) {
        extractors.add(extractor);
    }
}
