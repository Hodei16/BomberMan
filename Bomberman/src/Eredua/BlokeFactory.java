package Eredua;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class BlokeFactory {
    private static BlokeFactory nireBlokeFactory;
    private final Map<Integer, Supplier<Blokea>> blokeak = new HashMap<>();

    private BlokeFactory() {
        blokeak.put(1, Biguna::new);  // Biguna eraikitzailearen erreferentzia
        blokeak.put(2, Gogorra::new); // Gogorra eraikitzailearen erreferentzia
    }

    public static synchronized BlokeFactory getNireBlokeFactory() {
        if (nireBlokeFactory == null) {
            nireBlokeFactory = new BlokeFactory();
        }
        return nireBlokeFactory;
    }

    public Blokea createBlokea(int pMota) {
        return Optional.ofNullable(blokeak.get(pMota)).map(Supplier::get).orElseThrow(() -> 
                          new IllegalArgumentException(pMota + "blokea ez da existitzen.")
                      );
    }
}