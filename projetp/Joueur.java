import java.util.Scanner;

public abstract class Joueur implements Comparable<Joueur>{
	protected String nom;
	protected int numero;
	protected int points;
	
	public String getNom (){
		return nom;
	}
	public int getNumero (){
		return numero;
	}
	public int getPoints (){
		return points;
	}
	
	public void setPoints (){
		this.points++;
	}

	public int compareTo(Joueur other) {
		return other.points-this.points;
	}
	
	public abstract void jouer (Jeu jeu);
	
	public int finirCarre(Coordonnee c,Jeu jeu){
		int nbCarreFini=1;
		int ligne=c.getLigne();
		int colonne=c.getColonne();
		// si colonne ajoutee, verifier que ca ne ferme pas le carre a droite et le carre gauche
		//si ligne ajoutee, verifier que .... du bas et du haut
		 if (c.ligne%2!=0){	// si la premiere coordonnee est impaire alors c'est une ligne de jouee
		 	if(estJoue (ligne-1,colonne-1,ligne-1,colonne+1,ligne-2,colonne,jeu)&&
		 		estJoue (ligne+1,colonne-1,ligne-1,colonne+1,ligne+2,colonne,jeu)){
		 		nbCarreFini++;
		 	}
		 }else{
			if(estJoue (ligne-1,colonne-1,ligne+1,colonne-1,ligne,colonne-2,jeu)&&
				estJoue (ligne-1,colonne+1,ligne+1,colonne+1,ligne,colonne+2,jeu)){
		 		nbCarreFini++;
		 	}
		}
		jeu.ajouterCoordonnee(c);
		return nbCarreFini;
	}
	
	public boolean estJoue(int ligne1, int colonne1, int ligne2, int colonne2, int ligne3, int colonne3,Jeu jeu){
		Coordonnee c1=new Coordonnee(ligne1,colonne1);
 		Coordonnee c2=new Coordonnee(ligne2,colonne2);
 		Coordonnee c3=new Coordonnee(ligne3,colonne3);
 		return (jeu.getListeCoordonneesDejaJouees().contains(c1)
 				&& jeu.getListeCoordonneesDejaJouees().contains(c2)
 				&& jeu.getListeCoordonneesDejaJouees().contains(c3));
	}
}
