package Eredua;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class BomberManFactory {
    private static BomberManFactory nireBomberManFactory;
    private final Map<Integer, Supplier<BomberMan>> bomberManSortzaileak = new HashMap<>();

    private BomberManFactory() {
        bomberManSortzaileak.put(1, () -> new BomberZuria(0, 0));
        bomberManSortzaileak.put(2, () -> new BomberBeltza(0, 0));
    }

    public static synchronized BomberManFactory getNireBomberManFactory() {
        if (nireBomberManFactory == null) {
            nireBomberManFactory = new BomberManFactory();
        }
        return nireBomberManFactory;
    }

    public BomberMan createBomberMan(int pMota) {
        return Optional.ofNullable(bomberManSortzaileak.get(pMota)).map(Supplier::get).orElseThrow(() -> 
                          new IllegalArgumentException(pMota + " motako BomberMan ez da existitzen.")
                      );
    }
}