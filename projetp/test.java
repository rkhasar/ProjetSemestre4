import java.util.*;

public class test {
	
	public static Jeu jeu;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String reponse;
		
		//INITIALISATION DES PARAMETRES DE LA PARTIE

		boolean contour = Interactions.contour();
		int nbLignes=Interactions.demande("lignes");
		int nbColonnes=Interactions.demande("colonnes");
		LinkedList<Joueur> lesJoueurs=new LinkedList();
		Interactions.gestionJoueurs(lesJoueurs);
		jeu = new Jeu(contour, nbLignes, nbColonnes,lesJoueurs);
		
		// JEU AU TOUR PAR TOUR
		int numeroJoueur=1;
		
		while (!jeu.fini()){
			System.out.println(jeu);
			System.out.println("TAPEZ ENTREE");
			sc.nextLine();
			jeu.remplirCarre(numeroJoueur);
			if (!jeu.fini()){
				lesJoueurs.get(numeroJoueur-1).jouer(jeu);
				numeroJoueur=changerJoueur(numeroJoueur, jeu);
			}
		}
		jeu.afficherResultat();
	}
	
	
	public static int changerJoueur(int numeroJoueur,Jeu jeu){
		if (numeroJoueur==jeu.getLesJoueurs().size()){
			return 1;
		}
		return ++numeroJoueur;
	}

	public static int demandeNbJoueurs(){
		Scanner sc = new Scanner(System.in);
		String messageErreur=new String();
		int nbJoueurs=0;
		do{
			messageErreur=new String();
			System.out.println("Combien de joueurs aura cette partie ?");
			try{
				nbJoueurs=sc.nextInt();
				if (nbJoueurs<2){
					messageErreur="Veuillez rentrer un nombre supérieur à 2.";
				}
			}catch(Exception e){
				messageErreur="Veuillez rentrer un nombre superieur à 2.";
			}
		}while(!messageErreur.isEmpty());
		return nbJoueurs;
	}
	
}
