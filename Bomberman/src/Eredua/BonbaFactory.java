package Eredua;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class BonbaFactory {
    private static BonbaFactory nireBonbaFactory;
    private final Map<Integer, BiFunction<Integer, Integer, Bonba>> bonbaSortzaileak = new HashMap<>();

    private BonbaFactory() {
        bonbaSortzaileak.put(1, BonbaTxikia::new);
        bonbaSortzaileak.put(2, BonbaHandia::new);
    }

    public static synchronized BonbaFactory getNireBonbaFactory() {
        if (nireBonbaFactory == null) {
            nireBonbaFactory = new BonbaFactory();
        }
        return nireBonbaFactory;
    }

    public Bonba createBonba(int pMota, int x, int y) {
        return Optional.ofNullable(bonbaSortzaileak.get(pMota)).map(creator -> creator.apply(x, y)).orElseThrow(() -> 
        				new IllegalArgumentException(pMota + " motako bonba ez da existitzen.")
                      );
    }
}