package Dictionary;

public class Dictionary {
    private int n = 0;
    public Word[] words = new Word[10000];

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

}
