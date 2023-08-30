package ben.Trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrie {
    static class Trie {

        public Character val;
        Map<Character, Trie> dict;
        boolean end;

        public Trie() {
            this.dict = new HashMap<>();
        }

        public void insert(String word) {
            if(word.length() == 0) {
                return;
            }
            if(!this.dict.containsKey(word.charAt(0))) {
                Trie trie = new Trie();
                trie.val = word.charAt(0);
                this.dict.put(word.charAt(0), trie);
            }
            Trie trie = this.dict.get(word.charAt(0));
            if(word.length() == 1) {
                trie.end = true;
                return;
            }
            trie.insert(word.substring(1, word.length()));
        }

        public boolean search(String word) {
            if(!this.dict.containsKey(word.charAt(0))) {
                return false;
            }
            if(word.length() == 1) {
                Trie trie = this.dict.get(word.charAt(0));
                return trie.end;
            }
            Trie trie = this.dict.get(word.charAt(0));
            return trie.search(word.substring(1, word.length()));
        }

        public boolean startsWith(String prefix) {
            if(prefix.length() == 0) {
                return true;
            }
            if(!this.dict.containsKey(prefix.charAt(0))) {
                return false;
            }
            Trie trie = this.dict.get(prefix.charAt(0));
            return trie.startsWith(prefix.substring(1, prefix.length()));
        }
    }

    @Test
    public void test() {
        Character cc = '1';
        System.out.println('1' == cc);
//        Trie trie = new Trie();
//        trie.insert("app");
//        trie.insert("apple");
//        Assert.assertTrue(trie.search("app"));
//        Assert.assertFalse(trie.search("appl"));
//        Assert.assertTrue(trie.startsWith("appl"));
//        Assert.assertTrue(trie.search("apple"));
//        Assert.assertFalse(trie.search("appd"));
    }
}
