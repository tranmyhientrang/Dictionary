package Dictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DictionaryCommandline {
    public DictionaryManagement dim = new DictionaryManagement();

    public DictionaryManagement getDim() {
        return dim;
    }

    public void setDim(DictionaryManagement dim) {
        this.dim = dim;
    }

    /**
     * hien thi danh sach dictionary.
     */
    public void dictionaryBasic() {
        this.dim.insertFromCommandline();
        this.dim.showAllWords();
    }

    /**
     * hien thi va tim kiem.
     *
     * @param t
     * @throws FileNotFoundException
     */
    public void dictionaryAdvanced(String t) throws FileNotFoundException {
        this.dim.insertFromFile();
        this.dim.showAllWords();
        this.dim.dictionaryLookup(t);
    }

    /**
     * tim kiem theo tung chu.
     * @param s1
     * @return
     */
    public ArrayList<Word> dictionarySearcher(String s1) {
        ArrayList<Word> l = new ArrayList<Word>();
        int t = s1.length();
        for (int i = 0; i < this.dim.d.getN(); i++) {
            String s2 = new String();
            s2 = this.dim.d.words[i].getWord_target().substring(0, t);
            if ((s1.equalsIgnoreCase(s2)) == true) {
                l.add(this.dim.d.words[i]);
            }
        }
        return l;
    }

}
