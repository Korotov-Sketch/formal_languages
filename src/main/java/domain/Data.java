package domain;

import java.util.Set;

public class Data {

    private Set<String> numbers;
    private Set<String> signsForNumbers;
    private Set<String> punctuationSigns;
    private Set<String> alphas;
    private Set<String> types;
    private Set<String> keywords;

    public Set<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<String> numbers) {
        this.numbers = numbers;
    }

    public Set<String> getSignsForNumbers() {
        return signsForNumbers;
    }

    public void setSignsForNumbers(Set<String> signsForNumbers) {
        this.signsForNumbers = signsForNumbers;
    }

    public Set<String> getPunctuationSigns() {
        return punctuationSigns;
    }

    public void setPunctuationSigns(Set<String> punctuationSigns) {
        this.punctuationSigns = punctuationSigns;
    }

    public Set<String> getAlphas() {
        return alphas;
    }

    public void setAlphas(Set<String> alphas) {
        this.alphas = alphas;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
