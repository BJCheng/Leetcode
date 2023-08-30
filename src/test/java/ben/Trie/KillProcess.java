package ben.Trie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KillProcess {
    class Trie {
        Map<Integer, Trie> map;
        Integer val;
        public Trie(Integer val) {
            this.map = new HashMap<>();
            this.val = val;
        }
        void search(Integer id, List<Integer> result) {
            if(!this.map.containsKey(id)) {
                for(Map.Entry<Integer, Trie> entry: this.map.entrySet()) {
                    entry.getValue().search(id, result);
                }
            } else {
                this.map.get(id).getChildren(result);
            }
        }
        void getChildren(List<Integer> result) {
            result.add(this.val);
            for(Map.Entry<Integer, Trie> entry: this.map.entrySet()) {
                entry.getValue().getChildren(result);
            }
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Trie> map = new HashMap<>();
        for(int i=0; i<pid.size(); i++) {
            if(!map.containsKey(pid.get(i))) {
                map.put(pid.get(i), new Trie(pid.get(i)));
            }
            if(!map.containsKey(ppid.get(i))) {
                map.put(ppid.get(i), new Trie(ppid.get(i)));
            }
            if(map.get(ppid.get(i)).map.containsKey(pid.get(i))) {
                map.get(ppid.get(i)).map.put(pid.get(i), map.get(pid.get(i)));
            }
        }
        Trie root = map.get(0);
        List<Integer> result = new ArrayList<>();
        root.search(kill, result);
        return result;
    }

    @Test
    public void test() {
        new KillProcess().killProcess(Arrays.asList(1,3,10,5), Arrays.asList(3,0,5,3), 5);
    }
}
