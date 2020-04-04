package com.twiagle.cacheSys;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tb
 * @date 7/30/19-10:44 AM
 * sparse String separated by space into arrayList<String>
 */
public class SparseLine {
    public SparseLine(String line) {
        values = new LinkedList<>();
        this.line = new String(line);
    }
/*

 */
    public List<String> getValues() {
        String rest = line;
        do {
            if (rest.indexOf(' ') != -1) {
                values.add(rest.substring(0, rest.indexOf(' ')));
            }else {
                values.add(rest.substring(0));
                break;
            }
            rest = rest.substring(rest.indexOf(' ') + 1);
        } while (true);
        return values;
    }
    List<String> values;
    String line;
}
