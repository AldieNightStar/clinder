package haxidenti.clinder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clinder {

    public static ClinderArgs parse(List<String> args) {
        ClinderArgs outArgs = new ClinderArgs();
        List<String> list = new ArrayList<>(8);
        String lastName = null;
        for (int i = 0; i < args.size(); i++) {
            String val = args.get(i);
            String param = readParam(val);
            if (param != null) {
                collectAndClear(outArgs, lastName, list);
                lastName = param;
                continue;
            }
            // Do not allow to put to empty name
            if (lastName == null || lastName.length() < 1) continue;
            // If not param
            list.add(val);
        }
        collectAndClear(outArgs, lastName, list);
        outArgs.lock();
        return outArgs;
    }

    public static ClinderArgs parse(String[] args) {
        return parse(Arrays.asList(args));
    }

    public static void serve(List<String> args, ClinderApp app) {
        ClinderArgs a = parse(args);
        if (a.isPresent("h") || a.isPresent("help")) app.help(a);
        else app.run(a);
    }

    public static void serve(String[] args, ClinderApp app) {
        serve(Arrays.asList(args), app);
    }

    private static String readParam(String s) {
        if (s.length() < 2) return null;
        if (s.startsWith("--")) return s.substring(2);
        if (s.startsWith("-") && !"0123456789.".contains(s.substring(1, 2))) {
            return s.substring(1);
        }
        return null;
    }

    private static void collectAndClear(ClinderArgs outArgs, String name, List<String> list) {
        list.forEach(el -> outArgs.append(name, el));
        list.clear();
        outArgs.appendTrueOnEmpty(name);
    }
}
