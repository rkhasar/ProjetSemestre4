import java.util.*;

public class GestionLettres {
	/* Cette classe permet de gérer les lettres utilisees pour une meilleure visibilité du jeu
	 * et les interactions de jeu avec le joueur humain */
	
	
	public static boolean formatCoordonnees (String lesCoordonnees){
		//vérifie le format des coordonnees saisies par le joueur humain
		return (lesCoordonnees.length()==3 && lesCoordonnees.charAt(1)==' ' && ((estMinuscule(lesCoordonnees.charAt(0)) && estMajuscule(lesCoordonnees.charAt(2))) || (estMajuscule(lesCoordonnees.charAt(0)) && estMinuscule(lesCoordonnees.charAt(2)))));
	}
	public static boolean estMinuscule(char c){
		return (c>='a' && c<='z');
	}
	public static boolean estMajuscule(char c){
		return (c>='A' && c<='Z');
	}
	
	public static String problemeCoordonnees (String lesCoordonnees, Jeu jeu){
		//gere les problemes lies a l'interaction jouer avec le joueur humain
		String message=new String();
		if (!formatCoordonnees(lesCoordonnees)){
			message="Format des coordonnées incorrectes."+"\n"
					+ "Rappel : une minuscule et une majuscule (ou majuscule et minuscule) différentes séparées par un espace."+"\n";
		}else{
			lesCoordonnees=lesCoordonnees.toLowerCase();
			Coordonnee coord = new Coordonnee(lesCoordonnees.charAt(0),lesCoordonnees.charAt(2));
			if (jeu.getListeCoordonneesDejaJouees().contains(coord)){
				message="Les coordonnées sont déjà jouées."+"\n";
			}
			if (!(jeu.getListeCoordonneesAJouer().contains(new Coordonnee(lesCoordonnees.charAt(0),lesCoordonnees.charAt(2))))){
				message="Les coordonnées n'existent pas."+"\n";
			}
		}
		return message;
	}
	
	public static String conversion(int nb){
		if (nb%2==0){
			return GestionLettres.lettreMaj(nb);
		}
		return GestionLettres.lettreMin(nb);
	}
	
	public static String lettreMin(int nb) throws IllegalArgumentException{
			if (nb<27){
				switch (nb){
				case 1: return "a";
				case 2: return "b";
				case 3: return "c";
				case 4: return "d";
				case 5: return "e";
				case 6: return "f";
				case 7: return "g";
				case 8: return "h";
				case 9: return "i";
				case 10: return "j";
				case 11: return "k";
				case 12: return "l";
				case 13: return "m";
				case 14: return "n";
				case 15: return "o";
				case 16: return "p";
				case 17: return "q";
				case 18: return "r";
				case 19: return "s";
				case 20: return "t";
				case 21: return "u";
				case 22: return "v";
				case 23: return "w";
				case 24: return "x";
				case 25: return "y";
				}
			return "z";
			}
			throw new IllegalArgumentException();
		}

	public static String lettreMaj(int nb)throws IllegalArgumentException{
		if (nb<27){
			switch (nb){
			case 1: return "A";
			case 2: return "B";
			case 3: return "C";
			case 4: return "D";
			case 5: return "E";
			case 6: return "F";
			case 7: return "G";
			case 8: return "H";
			case 9: return "I";
			case 10: return "J";
			case 11: return "K";
			case 12: return "L";
			case 13: return "M";
			case 14: return "N";
			case 15: return "O";
			case 16: return "P";
			case 17: return "Q";
			case 18: return "R";
			case 19: return "S";
			case 20: return "T";
			case 21: return "U";
			case 22: return "V";
			case 23: return "W";
			case 24: return "X";
			case 25: return "Y";
			}
		return "Z";
		}
		throw new IllegalArgumentException();
	}
	public static int convert (char c){
		switch (c){
			case 'a': return 1;
			case 'b': return 2;
			case 'c': return 3;
			case 'd': return 4;
			case 'e': return 5;
			case 'f': return 6;
			case 'g': return 7;
			case 'h': return 8;
			case 'i': return 9;
			case 'j': return 10;
			case 'k': return 11;
			case 'l': return 12;
			case 'm': return 13;
			case 'n': return 14;
			case 'o': return 15;
			case 'p': return 16;
			case 'q': return 17;
			case 'r': return 18;
			case 's': return 19;
			case 't': return 20;
			case 'u': return 21;
			case 'v': return 22;
			case 'w': return 23;
			case 'x': return 24;
			case 'y': return 25;
			}
		return 26;
	}

}
