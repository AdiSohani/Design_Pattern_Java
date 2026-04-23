// =========================================================
// STATE PATTERN — Traffic Light
// The light object delegates all behaviour to its
// current state object. Behaviour changes automatically
// when the state changes.
// =========================================================
public class State {

    // State interface
    interface TrafficState {
        void pressButton(TrafficLight light);
        String color();
        String instruction();
    }

    // Context
    static class TrafficLight {
        private TrafficState currentState;

        TrafficLight() { currentState = new RedState(); }

        void setState(TrafficState state) {
            this.currentState = state;
        }

        void pressButton() {
            System.out.println("Before: " + currentState.color() + " — " + currentState.instruction());
            currentState.pressButton(this);
            System.out.println("After : " + currentState.color() + " — " + currentState.instruction());
            System.out.println();
        }
    }

    // Concrete states
    static class RedState implements TrafficState {
        public void pressButton(TrafficLight l) { l.setState(new GreenState()); }
        public String color()       { return "RED";    }
        public String instruction() { return "Stop — wait for green"; }
    }

    static class GreenState implements TrafficState {
        public void pressButton(TrafficLight l) { l.setState(new YellowState()); }
        public String color()       { return "GREEN";  }
        public String instruction() { return "Go — intersection clear"; }
    }

    static class YellowState implements TrafficState {
        public void pressButton(TrafficLight l) { l.setState(new RedState()); }
        public String color()       { return "YELLOW"; }
        public String instruction() { return "Slow down — light changing"; }
    }

    public static void main(String[] args) {
        System.out.println("=== State Pattern ===\n");

        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 4; i++) {
            light.pressButton();
        }
    }
}