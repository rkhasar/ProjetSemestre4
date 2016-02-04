import java.util.Scanner;

public class Humain extends Joueur{
	private int partiesGagnees;
	private int partiesJouees;
	
	public Humain (String nom, int numero){
		this.nom=nom;
		this.numero=numero;
		this.points=0;
		this.partiesGagnees=0;
		this.partiesJouees=1;
	}
	
	public Humain (String nom, int numero, int partiesGagnees, int partiesJouees){
		this.nom=nom;
		this.numero=numero;
		this.points=0;
		this.partiesGagnees=0;
		this.partiesJouees=1;
	}
	
	public int getPartiesGagnees (){
		return partiesGagnees;
	}
	
	public int getPartiesJouees (){
		return partiesJouees;
	}
	
	public void setPartiesGagnees (){
		partiesGagnees++;
	}
	
	public void setPartiesJouees (){
		partiesJouees++;
	}
	
	public void jouer (Jeu jeu){
		Scanner sc = new Scanner(System.in);
		String messageErreur=new String();
		String reponse;
		System.out.println("Au tour de "+getNom());
		do{
			System.out.println(messageErreur);
			System.out.println("Donnez les coordonnees à jouer sous la forme ligne colonne. Exemple c B");
			reponse=sc.nextLine().trim();
			messageErreur=GestionLettres.problemeCoordonnees(reponse,jeu);
		}while(!messageErreur.isEmpty());
		jeu.ajouterCoordonnee(new Coordonnee(reponse.toLowerCase().charAt(0),reponse.toLowerCase().charAt(2)));
	}
}
