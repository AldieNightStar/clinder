package haxidenti.clinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ClinderArgs {
    private HashMap<String, List<String>> map = new HashMap<>(32);
    private boolean locked = false;

    public void append(String param, String val) {
        if (locked) throw new IllegalArgumentException("Clinder Args is locked. No modification allowed");
        List<String> list = map.computeIfAbsent(param, i -> new ArrayList<>(4));
        list.add(val);
    }

    public void appendTrueOnEmpty(String param) {
        if (!isPresent(param)) {
            append(param, "true");
        }
    }

    public String getFirst(String name) {
        return getFirst(name, null);
    }

    public String getFirst(String name, String defVal) {
        List<String> list = map.get(name);
        if (list == null || list.isEmpty()) return defVal;
        return list.get(0);
    }

    public boolean isPresent(String name) {
        return map.get(name) != null;
    }

    public List<String> getList(String name) {
        return Collections.unmodifiableList(
                map.getOrDefault(name, Collections.emptyList())
        );
    }

    public void lock() {
        this.locked = true;
    }
}
