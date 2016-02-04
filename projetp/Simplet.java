import java.util.Scanner;

public class Simplet extends Joueur{

	public Simplet (String nom, int numero){
		this.nom=nom;
		this.numero=numero;
		this.points=0;
	}
	
	public void jouer (Jeu jeu){
		System.out.println("Au tour de "+getNom());
		Coordonnee coordonneeAleatoire=jeu.genererCoordonnees();
		jeu.ajouterCoordonnee(coordonneeAleatoire);
		System.out.println(getNom()+" joue "+coordonneeAleatoire);
	}
}
