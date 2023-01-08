package haxidenti.clinder;

public interface ClinderApp {
    void run(ClinderArgs args);

    default void help(ClinderArgs args) {
        System.out.println("This is help. Put here usage for -h and --help arguments");
    }
}
