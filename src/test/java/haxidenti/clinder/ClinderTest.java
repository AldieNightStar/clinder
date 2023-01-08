package haxidenti.clinder;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClinderTest {
    @Test
    void testSimpleArgs() {
        List<String> list = List.of("-t", "-a", "123", "-b", "234", "-c", "555", "-99");
        ClinderArgs args = Clinder.parse(list);

        assertEquals("true", args.getFirst("t"));
        assertEquals("123", args.getFirst("a"));
        assertEquals("234", args.getFirst("b"));
        assertEquals("555", args.getFirst("c"));
        assertEquals(List.of("555", "-99"), args.getList("c"));
    }

    @Test
    void testServeHelp() {
        List<String> list = List.of("-z", "-h");
        AtomicBoolean ok = new AtomicBoolean(false);
        Clinder.serve(list, new ClinderApp() {
            @Override
            public void run(ClinderArgs args) {
                throw new IllegalArgumentException("");
            }

            @Override
            public void help(ClinderArgs args) {
                ok.set(true);
            }
        });

        assertTrue(ok.get());
    }
}