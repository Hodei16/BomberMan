package Eredua;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class EszenarioFactory {
    private static EszenarioFactory nireEszenarioFactory;
    private final Map<String, Supplier<EszenarioKudeatzailea>> eszenarioSortzaileak = new HashMap<>();

    private EszenarioFactory() {
        eszenarioSortzaileak.put("EszenarioClassic", EszenarioClassic::new);
        eszenarioSortzaileak.put("EszenarioSoft", EszenarioSoft::new);
    }

    public static synchronized EszenarioFactory getNireEszenarioFactory() {
        if (nireEszenarioFactory == null) {
            nireEszenarioFactory = new EszenarioFactory();
        }
        return nireEszenarioFactory;
    }

    public EszenarioKudeatzailea createEszenarioa(String pMota) {
        return Optional.ofNullable(eszenarioSortzaileak.get(pMota)).map(Supplier::get).orElseGet(EszenarioEmpty::new);
    }
}