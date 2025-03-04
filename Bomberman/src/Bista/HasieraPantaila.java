package Bista;

public class HasieraPantaila {
	private static HasieraPantaila nireHasieraPantaila;
	
	private HasieraPantaila() {}
	
	public static synchronized HasieraPantaila getNireHasieraPantaila() {
		if(nireHasieraPantaila == null) {
			nireHasieraPantaila = new HasieraPantaila();
		}
		return nireHasieraPantaila;
	}
	
	
}
