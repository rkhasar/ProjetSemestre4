
public class Coordonnee {
	int ligne;
	int colonne;

/* Une coordonnee est une paire d'entiers, corespondant à un trait d'un carre du jeu*/
	
	public Coordonnee(int ligne, int colonne){
		this.ligne=ligne;
		this.colonne=colonne;
	}
	public Coordonnee (char ligne, char colonne){
		this.ligne=GestionLettres.convert(ligne);
		this.colonne=GestionLettres.convert(colonne);
	}
	
	public int getLigne(){
		return ligne;
	}
	public int getColonne(){
		return colonne;
	}
	public String toString(){
		return GestionLettres.conversion(ligne)+" "+GestionLettres.conversion(colonne);
	}
	public boolean equals (Object other){
		if (other instanceof Coordonnee){
			return (this.ligne == ((Coordonnee) other).ligne && this.colonne == ((Coordonnee) other).colonne);
		}else{
			return false;
		}
	}
	
	
}
