import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Jeu {
//ATTRIBUTS
	private int nbLignes;
	private int nbColonnes;
	private List<Coordonnee> coordonneesDejaJouees;
	private List<Coordonnee> coordonneesPossibles;
	private LinkedList<Joueur> lesJoueurs;
	
//CONSTRUCTEUR
	public Jeu (boolean avecContour, int nbLignes, int nbColonnes, LinkedList<Joueur> lesJoueurs){
		this.nbLignes=nbLignes;
		this.nbColonnes=nbColonnes;
		this.lesJoueurs=lesJoueurs;
		coordonneesDejaJouees = new LinkedList<Coordonnee>();
		coordonneesPossibles = new LinkedList<Coordonnee>();
		for (int ligne=1; ligne<=nbLignes*2+1;ligne++){
			for (int colonne=1; colonne<=nbColonnes*2+1;colonne++){
				if ((ligne%2!=0 && colonne%2==0)||(ligne%2==0 && colonne%2!=0)){
					coordonneesPossibles.add(new Coordonnee (ligne,colonne));
				}
			}
		}
		if (avecContour){
			for (int colonne=2; colonne<=nbColonnes*2+1; colonne+=2){
				ajouterCoordonnee(new Coordonnee (1,colonne));
				ajouterCoordonnee(new Coordonnee (nbLignes*2+1,colonne));
			}
			for (int ligne=2; ligne<=nbColonnes*2+1; ligne+=2){
				ajouterCoordonnee(new Coordonnee (ligne,1));
				ajouterCoordonnee(new Coordonnee (ligne,nbColonnes*2+1));
			}
		}
	}
	
//ACCESSEURS
	public LinkedList<Coordonnee> getListeCoordonneesDejaJouees (){
		return new LinkedList<Coordonnee> (coordonneesDejaJouees);
	}
	public LinkedList<Coordonnee> getListeCoordonneesAJouer (){
		return new LinkedList<Coordonnee> (coordonneesPossibles);
	}
	public int getNbLignes (){
		return nbLignes;
	}
	public int getNbColonnes (){
		return nbColonnes;
	}
	public LinkedList<Joueur> getLesJoueurs (){
		return new LinkedList<Joueur> (lesJoueurs);
	}

//AFFICHAGE
	public String toString(){
		String s=new String();
		for (int ligne=0; ligne<=nbLignes*2+1;ligne++){// pour chaque ligne
			if (ligne==0){
				s+="	";
				for (int colonne=1; colonne<=nbColonnes*2+1; colonne++){// pour chaque colonne
					if (colonne%2!=0){// colonne impair (lettre minuscule)
						s+=GestionLettres.lettreMin(colonne);
					}else{// colonne pair (lettre majuscule)
						s+=GestionLettres.lettreMaj(colonne);	
					}	
					s+="  ";
				}
				s+="\n";
			}else{
				if (ligne%2!=0){// ligne impair (lettre minjuscule)
					s+=GestionLettres.lettreMin(ligne);
					s+="	";
					for (int colonne=2; colonne<=nbColonnes*2+1;colonne+=2){
						if (coordonneesDejaJouees.contains(new Coordonnee(ligne,colonne))){
							s+=" ---- ";
						}else{
							s+=" .... ";
						}
					}	
				}else{// ligne pair (lettre majuscule)
					s+=GestionLettres.lettreMaj(ligne);
					s+="	";
					for (int colonne=1; colonne<=nbColonnes*2+1;colonne+=2){
						if (coordonneesDejaJouees.contains(new Coordonnee(ligne,colonne))){
							s+="|     ";
						}else{
							s+=".     ";
						}
					}
				}
				s+="\n";
			}	
		}
		s+="Score : "+"\n";
		for (int i=0; i<lesJoueurs.size();i++){
			s+=lesJoueurs.get(i).getNom()+" : "+lesJoueurs.get(i).getPoints()+" points"+"\n";
		}
		return s;
	}
	
	public void afficherResultat(){
		System.out.println("FIN DE LA PARTIE"+"\n"+"Classement :");
		Collections.sort(lesJoueurs);
		int num=1;
		for (int i=0; i<lesJoueurs.size();i++){
			System.out.println(num+" "+lesJoueurs.get(i).getNom()+" ("+lesJoueurs.get(i).getPoints()+" points)");
			if (!exEquo(i)){
				++num;
			}
		}
	}
	public boolean exEquo (int numeroJoueur){
		if (numeroJoueur<lesJoueurs.size()-1){
			return lesJoueurs.get(numeroJoueur).getPoints()==lesJoueurs.get(numeroJoueur+1).getPoints();
		}
		return false;
	}

//MODIFIER LE JEU
	public void ajouterCoordonnee(Coordonnee c){
		coordonneesDejaJouees.add(c);
		coordonneesPossibles.remove(c);
	}
	public void supprimerCoordonnee(Coordonnee c){
		coordonneesDejaJouees.remove(c);
		coordonneesPossibles.add(c);
	}	
	public void remplirCarre(int numeroJoueur){
		// rempli automatiquement les carres possibles en précisant au joueur avec quelle action
		// ajoute un point par carre rempli au joueur
		Scanner sc = new Scanner(System.in);
		boolean action;
		do{
			action = false;
			for(int ligne = 1;ligne <= nbLignes*2-1;ligne += 2){
				for(int colonne = 2; colonne <= nbColonnes*2+1; colonne += 2){
					LinkedList<Coordonnee> lesTraitsManquants = traitsManquants(ligne,colonne);
					if(lesTraitsManquants.size() == 1){
						action = true;
						int nbDeCarreFini=lesJoueurs.get(numeroJoueur-1).finirCarre(lesTraitsManquants.get(0), this);
						String s=lesJoueurs.get(numeroJoueur-1).getNom()+" fini "+nbDeCarreFini;
						if (nbDeCarreFini==1){
							s+=" carré ";
						}else{
							s+=" carrés ";
						}
						System.out.println(s+" en jouant les coordonnées : "+lesTraitsManquants.get(0));
						lesJoueurs.get(numeroJoueur-1).setPoints();
						System.out.println(this.toString());
						sc.nextLine();
					}
				}
			}
		}while(action);
	}
	
//AUTRES METHODES
	
	public boolean fini(){
		return coordonneesPossibles.isEmpty();
	}
	
	//genere aleatoirement une coordonnee pour le joueur simplet
	public Coordonnee genererCoordonnees(){
		return coordonneesPossibles.get((int)(Math.random()*coordonneesPossibles.size()));
	}
	
	//cree une liste de coordonnees manquantes pour finir un carre
	public LinkedList<Coordonnee> traitsManquants(int ligne,int colonne){
		LinkedList<Coordonnee> traitsManquants = new LinkedList();
		// traitement des quatre coordonnees necessaire pour former un carre 
		traitementCoordonnee(ligne,colonne,traitsManquants);
		traitementCoordonnee(ligne+1,colonne-1,traitsManquants);
		traitementCoordonnee(ligne+1,colonne+1,traitsManquants);
		traitementCoordonnee(ligne+2,colonne,traitsManquants);		
		return traitsManquants;	
	}
	//ajoute à la lsite la coordonnee (ligne,colonne) passee en parametre si elle est deja jouee
	public void traitementCoordonnee(int ligne,int colonne, LinkedList<Coordonnee> liste){
		if(!coordonneesDejaJouees.contains(new Coordonnee(ligne,colonne))){
			liste.add(new Coordonnee(ligne,colonne));
		}
	}
	
	
}
