package sample;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.Formatter;

public class DictionaryManagement {
    public Dictionary d = new Dictionary();

    public Dictionary getD() {
        return d;
    }

    public void setD(Dictionary d) {
        this.d = d;
    }

    public void insertFromCommandline() {
        int tmp;
        Scanner sc = new Scanner(System.in);
        tmp = sc.nextInt();
        sc.nextLine();
        for (int i = this.d.getN(); i < tmp; i++) {
            this.d.words[this.d.getN()] = new Word();
            this.d.words[this.d.getN()].setWord_target(sc.nextLine());
            this.d.words[this.d.getN()].setWord_explain(sc.nextLine());
            this.d.setN(this.d.getN() + 1);
        }
    }

    public String showAllWords() {
        StringBuilder st = new StringBuilder();
        String s1 = "No";
        String s2 = "English";
        String s3 = "Vietnamese";
        st.append(s1).append("\t\t\t").append(s2).append("\t\t\t").append(s3).append("\n");
        for (int i = 0; i < this.d.getN(); i++) {
            st.append(i + 1).append("\t\t\t").append(this.d.words[i].getWord_target()).append("\t\t\t").append(this.d.words[i].getWord_explain()).append("\n");
        }
        return st.toString();
    }

    public void insertFromFile() throws FileNotFoundException {
        String url = "C:\\Users\\Sac Xuan.PC\\Desktop\\tham.txt";
        FileInputStream f = new FileInputStream(url);
        Scanner sc = new Scanner(f, "UTF-8");
        sc.nextLine();
        int i = 0;
        try {
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                String[] item = temp.split("\\t");
                this.d.words[i] = new Word();
                this.d.words[i].setWord_target(item[0]);
                this.d.words[i].setWord_explain(item[1]);
                i++;
            }
        } finally {
            try {
                sc.close();
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(DictionaryManagement.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        this.d.setN(i);
    }

    public String dictionaryLookup(String t) {
        String t1 = new String();
        for (int i = 0; i < this.d.getN(); i++) {
            if (this.d.words[i].getWord_target().equalsIgnoreCase(t) == true) {
                System.out.println(this.d.words[i].getWord_target());
                System.out.println(this.d.words[i].getWord_explain());
                t1 = this.d.words[i].getWord_target() + "\n" + this.d.words[i].getWord_explain();
                break;
            } else {
                t1 = "Don't find!";
            }
        }
        return t1;
    }

    public void dictionaryAdd(String target, String ex) {
        Scanner sc = new Scanner(System.in);
        this.d.words[this.d.getN()] = new Word();
        this.d.words[this.d.getN()].setWord_target(target);
        this.d.words[this.d.getN()].setWord_explain(ex);
        this.d.setN(this.d.getN() + 1);
    }

    public void dictionarySet(Word w1, Word w2) {
        for (int i = 0; i < this.d.getN(); i++) {
            if ((this.d.words[i].getWord_target().equalsIgnoreCase(w1.getWord_target()) == true) || (this.d.words[i].getWord_explain().equalsIgnoreCase(w2.getWord_explain()) == true)) {
                this.d.words[i] = w2;
            }
        }
    }

    public void dictionaryDelete(String t) {
        int dem = -1;
        for (int i = 0; i < this.d.getN(); i++) {
            if (this.d.words[i].getWord_target().equalsIgnoreCase(t) == true) {
                dem = i;
            }
        }
        for (int i = dem; i < this.d.getN() - 1; i++) {
            this.d.words[i] = this.d.words[i + 1];
        }
        this.d.words[this.d.getN() - 1] = null;
        this.d.setN(this.d.getN() - 1);

    }

    public void dictionaryExportToFile() throws FileNotFoundException {
        Formatter f = new Formatter("C:\\Users\\Sac Xuan.PC\\Desktop\\tham.txt");
        f.format("\n");
        for (int i = 0; i < this.d.getN(); i++) {
            f.format("%s\t%s\r\n", this.d.words[i].getWord_target(), this.d.words[i].getWord_explain());
        }
        f.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        DictionaryManagement d1 = new DictionaryManagement();
        try {
            d1.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String t = d1.showAllWords();
        System.out.println(t);
    }
}
