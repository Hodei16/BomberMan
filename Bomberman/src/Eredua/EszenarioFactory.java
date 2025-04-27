package Eredua;

public class EszenarioFactory {
	private static EszenarioFactory nireEszenarioFactory;
	
	private EszenarioFactory() {}
	
	public static EszenarioFactory getNireEszenarioFactory() {
		if(nireEszenarioFactory == null) {
			nireEszenarioFactory = new EszenarioFactory();
		}
		return nireEszenarioFactory;
	}
	
	public EszenarioKudeatzailea createEszenarioa(String pMota) {
		EszenarioKudeatzailea nireEszenario = null;
		if (pMota == "EszenarioClassic") {nireEszenario = new EszenarioClassic();}
		else if (pMota == "EszenarioSoft") {nireEszenario = new EszenarioSoft();}
		else {nireEszenario = new EszenarioEmpty();}
		return nireEszenario;
	}
}
