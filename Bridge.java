// =========================================================
// BRIDGE PATTERN — TV Remote & TV Brand
// Remote (abstraction) is independent of TV brand (implementation).
// Mix any remote with any TV brand freely.
// =========================================================
public class Bridge {

    // Implementation interface
    interface TV {
        void setVolume(int level);
        void setChannel(int number);
        String brand();
    }

    // Concrete implementations
    static class SamsungTV implements TV {
        public void setVolume(int l)  { System.out.println("Samsung TV  — Volume  → " + l); }
        public void setChannel(int n) { System.out.println("Samsung TV  — Channel → " + n); }
        public String brand()         { return "Samsung"; }
    }

    static class SonyTV implements TV {
        public void setVolume(int l)  { System.out.println("Sony TV     — Volume  → " + l); }
        public void setChannel(int n) { System.out.println("Sony TV     — Channel → " + n); }
        public String brand()         { return "Sony"; }
    }

    // Abstraction — holds a reference to the implementation (the bridge)
    abstract static class Remote {
        protected TV tv;
        Remote(TV tv) { this.tv = tv; }
        abstract void volumeUp();
        abstract void volumeDown();
        abstract void nextChannel();
    }

    // Refined abstraction
    static class BasicRemote extends Remote {
        private int vol = 10, ch = 1;
        BasicRemote(TV tv) { super(tv); }

        void volumeUp()    { tv.setVolume(++vol); }
        void volumeDown()  { tv.setVolume(--vol); }
        void nextChannel() { tv.setChannel(++ch); }
    }

    static class SmartRemote extends Remote {
        private int vol = 20, ch = 5;
        SmartRemote(TV tv) { super(tv); }

        void volumeUp()    { vol += 5; tv.setVolume(vol); }   // jumps by 5
        void volumeDown()  { vol -= 5; tv.setVolume(vol); }
        void nextChannel() { ch += 10; tv.setChannel(ch); }   // jumps by 10
    }

    public static void main(String[] args) {
        System.out.println("=== Bridge Pattern ===\n");

        System.out.println("BasicRemote + Samsung:");
        Remote r1 = new BasicRemote(new SamsungTV());
        r1.volumeUp(); r1.nextChannel();

        System.out.println("\nSmartRemote + Sony:");
        Remote r2 = new SmartRemote(new SonyTV());
        r2.volumeUp(); r2.nextChannel();
    }
}