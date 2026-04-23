// =========================================================
// MEMENTO PATTERN — MS Word Undo (Ctrl+Z)
// Snapshots are saved after each keystroke.
// Undo restores the previous snapshot without
// exposing the document's internal structure.
// =========================================================
import java.util.ArrayDeque;
import java.util.Deque;

public class Memento {

    // Memento — opaque snapshot (no public getters beyond state)
    static final class Snapshot {
        private final String content;
        Snapshot(String content) { this.content = content; }
        private String getContent() { return content; }
    }

    // Originator — creates and restores from snapshots
    static class Document {
        private String content = "";

        void type(String text) { content += text; }

        Snapshot save() {
            System.out.println("  [Saved snapshot] \"" + content + "\"");
            return new Snapshot(content);
        }

        void restore(Snapshot snap) {
            content = snap.getContent();
            System.out.println("  [Restored]       \"" + content + "\"");
        }

        String getContent() { return content; }
    }

    // Caretaker — manages the undo stack
    static class UndoManager {
        private final Deque<Snapshot> history = new ArrayDeque<>();

        void push(Snapshot snap) { history.push(snap); }

        Snapshot undo() {
            if (history.isEmpty()) { System.out.println("  Nothing to undo."); return null; }
            return history.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Memento Pattern ===\n");

        Document    doc   = new Document();
        UndoManager undo  = new UndoManager();

        System.out.println("--- Typing ---");
        doc.type("Hello ");       undo.push(doc.save());
        doc.type("World ");       undo.push(doc.save());
        doc.type("from Java ");   undo.push(doc.save());
        doc.type("Design Patterns!");

        System.out.println("\nCurrent content: \"" + doc.getContent() + "\"");

        System.out.println("\n--- Ctrl+Z (undo 3 times) ---");
        doc.restore(undo.undo());
        doc.restore(undo.undo());
        doc.restore(undo.undo());

        System.out.println("\nFinal content: \"" + doc.getContent() + "\"");
    }
}