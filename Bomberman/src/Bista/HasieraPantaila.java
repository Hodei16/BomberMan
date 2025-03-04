package Bista;

public class HasieraPantaila {
	private static HasieraPantaila nireHasieraPantaila;
	
	private HasieraPantaila() {}
	
	public static synchronised HasieraPantaila getNireHasieraPantaila() {
		if(nireHasieraPantaila == null) {
			nireHasieraPantaila = new HasieraPantaila();
		}
		return nireHasieraPantaila;
	}
	
	
}
