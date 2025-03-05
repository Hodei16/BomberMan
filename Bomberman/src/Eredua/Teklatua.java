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
		String input = sc.nextLine().trim().toUpperCase();
		if (input =="W") {
            return "W";
        } else if (input == "A") {
        	return "W";
        } else if (input == "S") {
        	return "W";
        } else if (input == "D") {
        	return "W";
        }
		return "";
	}
}
