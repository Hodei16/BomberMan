package Eredua;

import java.util.Scanner;

public class Teklatua {
	
	private static Teklatua nireTeklatua = null;
	private Scanner sc;
	
	private Teklatua() {
		sc = new Scanner(System.in);
	}
	public static Teklatua getTeklatua() {
		if(nireTeklatua == null) {
			nireTeklatua = new Teklatua();
		}
		return nireTeklatua;
	}
	public String detectWASD() {
	    String input = sc.nextLine().trim().toLowerCase();
	    if (input.equals("w")) {
	        return "w";
	    } else if (input.equals("a")) {
	        return "a";
	    } else if (input.equals("s")) {
	        return "s";
	    } else if (input.equals("d")) {
	        return "d";
	    }
	    return "";
	}

}
