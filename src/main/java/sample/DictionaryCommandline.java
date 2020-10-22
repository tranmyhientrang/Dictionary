package sample;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DictionaryCommandline {
    public DictionaryManagement dim = new DictionaryManagement();

    public DictionaryManagement getDim() {
        return dim;
    }

    public void setDim(DictionaryManagement dim) {
        this.dim = dim;
    }

    public void dictionaryBasic(){
        this.dim.insertFromCommandline();
        this.dim.showAllWords();
    }

    /** public void dictionaryAdvanced() throws FileNotFoundException {
     this.dim.insertFromFile();
     this.dim.showAllWords();
     this.dim.dictionaryLookup();
     }
     */
    public ArrayList<Word> dictionarySearcher(String s1){
        ArrayList<Word> l = new ArrayList<Word>();
        int t = s1.length();
       // StringBuilder st = new StringBuilder();
        for(int i=0;i<this.dim.d.getN(); i++){
            String s2 = new String();
            s2 = this.dim.d.words[i].getWord_target().substring(0,t);
            if((s1.equalsIgnoreCase(s2))==true){
                l.add(this.dim.d.words[i]);
                //st.append(this.dim.d.words[i].getWord_target()).append("\n");
            }
        }
        return l;
    }

    public static void main(String[] args) throws FileNotFoundException {
        DictionaryCommandline d = new DictionaryCommandline();
        d.dim.insertFromFile();
        ArrayList<Word> a = new ArrayList<Word>(d.dictionarySearcher("yes"));
        //for (int i=0;i<a.size();i++){
            System.out.println(a.get(0).getWord_target());
        //}

    }
}
