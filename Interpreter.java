// =========================================================
// INTERPRETER PATTERN — Train Announcement Board
// Parses a compact code like "DEL-MUM-17:30-PF3" using
// a small grammar and renders it as a human-readable announcement.
// =========================================================
import java.util.*;

public class Interpreter {

    // Context — carries data between expressions
    static class Context {
        String raw;
        String[] parts;
        Context(String raw) {
            this.raw = raw;
            this.parts = raw.split("-");
        }
    }

    // Expression interface
    interface Expression {
        String interpret(Context ctx);
    }

    // Terminal expressions
    static class SourceCityExpression implements Expression {
        public String interpret(Context ctx) { return cityName(ctx.parts[0]); }
    }

    static class DestCityExpression implements Expression {
        public String interpret(Context ctx) { return cityName(ctx.parts[1]); }
    }

    static class DepartureTimeExpression implements Expression {
        public String interpret(Context ctx) { return ctx.parts[2]; }
    }

    static class PlatformExpression implements Expression {
        public String interpret(Context ctx) {
            return ctx.parts[3].replace("PF", "Platform ");
        }
    }

    // Non-terminal expression — combines terminals into a full announcement
    static class AnnouncementExpression implements Expression {
        private final Expression src  = new SourceCityExpression();
        private final Expression dest = new DestCityExpression();
        private final Expression time = new DepartureTimeExpression();
        private final Expression plat = new PlatformExpression();

        public String interpret(Context ctx) {
            return "Train from " + src.interpret(ctx)
                    + " to "        + dest.interpret(ctx)
                    + " | Departure: " + time.interpret(ctx)
                    + " | "         + plat.interpret(ctx);
        }
    }

    static String cityName(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("DEL", "New Delhi");    map.put("MUM", "Mumbai");
        map.put("BLR", "Bengaluru");    map.put("HYD", "Hyderabad");
        map.put("CHE", "Chennai");      map.put("KOL", "Kolkata");
        return map.getOrDefault(code, code);
    }

    public static void main(String[] args) {
        System.out.println("=== Interpreter Pattern ===\n");

        Expression announcement = new AnnouncementExpression();

        String[] codes = { "DEL-MUM-17:30-PF3", "BLR-HYD-09:15-PF1", "CHE-KOL-22:45-PF7" };
        for (String code : codes) {
            System.out.println("Code   : " + code);
            System.out.println("Display: " + announcement.interpret(new Context(code)));
            System.out.println();
        }
    }
}