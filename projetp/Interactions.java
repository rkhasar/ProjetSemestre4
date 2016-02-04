
import java.util.*;

public class Interactions {
	
//demande au joueur le nb de lignes et colonnes du jeu
	public static int demande(String critere){
		String messageErreur=new String();
		int reponse=0;
		do{
			messageErreur=new String();
			System.out.println("Avec quel nombre de "+critere+" voulez-vous jouer ?");
			try{
				Scanner sc = new Scanner(System.in);
				reponse=sc.nextInt();
				if (reponse<3 || reponse>12){
					messageErreur="Veuillez rentrer un nombre entre 3 et 12.";
					System.out.println(messageErreur);
				}
			}catch (InputMismatchException e){
				messageErreur="Veuillez rentrer un nombre entre 3 et 12.";
				System.out.println(messageErreur);
			}
		}while(!messageErreur.isEmpty());
		return reponse;
	}

//demande au joueur s'il souhaite jouer avec les contours
	public static boolean contour (){
		Scanner sc = new Scanner(System.in);
		boolean contour=true;
		String messageErreur=new String();
		String reponse=new String();
		do{
			messageErreur=new String();
			System.out.println("Voulez-vous jouer avec les contour ? O/N");
			reponse=sc.nextLine().trim().toLowerCase();
			if (!reponse.equals("o") && !reponse.equals("n")){
				messageErreur="Veuillez rentrer O ou N.";
			}else{
				if (reponse.equals("n")){
					contour=false;
				}
			}
		}while(!messageErreur.isEmpty());
		return contour;
	}

//creer la liste des joueurs humain et ordinateur selon les volontes du joueur
	public static void gestionJoueurs (LinkedList<Joueur> lesJoueurs){
		Scanner sc = new Scanner(System.in);
		String messageErreur=new String();
		int nbJoueurs;
		String reponse;
		boolean ordinateur=contreOrdinateur();
		if (ordinateur){
			boolean joueEnPremier=jouerEnPremier();
			System.out.println("Donnez votre nom.");
			String nom=sc.nextLine();
			if (joueEnPremier){
				lesJoueurs.add(new Humain(nom,1));
				lesJoueurs.add(new Simplet("Ordinateur",1));
			}else{
				lesJoueurs.add(new Simplet("Ordinateur",1));
				lesJoueurs.add(new Humain(nom,1));
			}
		}else{
			nbJoueurs=nbJoueurs();
			for (int i=1; i<=nbJoueurs;i++){
				System.out.println("Donnez le nom du joueur numero "+i+".");
				String nom=sc.nextLine();
				lesJoueurs.add(new Humain(nom,1));
			}
		}
	}
	
	public static boolean contreOrdinateur(){
		Scanner sc = new Scanner(System.in);
		boolean ordinateur=true;
		String messageErreur=new String();
		String reponse;
		do{
			messageErreur=new String();
			System.out.println("Souhaitez-vous jouer contre l'ordinateur ? O/N");
			reponse=sc.nextLine().trim().toLowerCase();
			if (!reponse.equals("o") && !reponse.equals("n")){
				messageErreur="Veuillez rentrer O ou N.";
			}else{
				if (reponse.equals("n")){
					ordinateur=false;
				}
			}
		}while(!messageErreur.isEmpty());
		return ordinateur;
	}
	
	public static boolean jouerEnPremier(){
		Scanner sc = new Scanner(System.in);
		boolean enPremier=true;
		String messageErreur=new String();
		String reponse;
		do{
			messageErreur=new String();
			System.out.println("Voulez-vous jouer en premier ? O/N");
			reponse=sc.nextLine().trim().toLowerCase();
			if (!reponse.equals("o") && !reponse.equals("n")){
				messageErreur="Veuillez rentrer O ou N.";
			}else{
				if (reponse.equals("n")){
					enPremier=false;
				}
			}
		}while(!messageErreur.isEmpty());
		return enPremier;
	}
	
	public static int nbJoueurs(){
		Scanner sc = new Scanner(System.in);
		String messageErreur=new String();
		int reponse;
		do{
			messageErreur=new String();
			System.out.println("Combien de joueurs y a-t-il ?");
			reponse=sc.nextInt();
			if (reponse<2){
				messageErreur="Veuillez rentrer un entier superieur à 2.";
			}
		}while(!messageErreur.isEmpty());
		return reponse;
	}
}
