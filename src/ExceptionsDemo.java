// Exercices - Exceptions et erreurs
import java.util.*;
import java.io.*;

// Exception personnalisée pour un solde insuffisant (Exercice 1)
class SoldeInsuffisantException extends Exception {
    public SoldeInsuffisantException(String message) {
        super(message);
    }
}

// Exception personnalisée pour un événement complet (exercice 2)
class EvenementCompletException extends Exception {
    public EvenementCompletException(String message) {
        super(message);
    }
}

public class ExceptionsDemo {
    static Scanner scanner = new Scanner(System.in);

//    // Exercice 1 - Distributeur automatique
    public static void distributeurAutomatique() throws SoldeInsuffisantException {
        int solde = 100;
        System.out.print("Montant à retirer (multiple de 10) : ");
        int montant = scanner.nextInt();

        if (montant % 10 != 0) {
            throw new IllegalArgumentException("Le montant doit être un multiple de 10.");
        }
        if (montant > solde) {
            throw new SoldeInsuffisantException("Solde insuffisant pour ce retrait.");
        }

        solde -= montant;
        System.out.println("Retrait effectué. Nouveau solde : " + solde);
    }

    // Exercice 2 - Inscriptions à un événement
    public static void inscriptionEvenement() throws EvenementCompletException {
        int maxParticipants = 3;
        List<String> inscrits = new ArrayList<>();

        while (true) {
            System.out.print("Nom à inscrire (ou 'stop') : ");
            String nom = scanner.nextLine();
            if (nom.equalsIgnoreCase("stop")) break;

            if (inscrits.size() >= maxParticipants) {
                throw new EvenementCompletException("L'évènement est malheureusement complet.");
            }
            inscrits.add(nom);
            System.out.println(nom + " inscrit(e).");
        }
        System.out.println("Liste finale : " + inscrits);
    }

    // Exercice 3 - Lecture de fichier CSV
    public static void lireFichierCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/etudiants.csv"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println("Nom : " + ligne);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }

    // Exercice 4 - Catalogue de livres
    public static void catalogueLivres() {
        String[] livres = {"Le Petit Prince", "1984", "Le Seigneur des Anneaux", "Germinal"};
        try {
            System.out.print("Index du livre à afficher : ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.println("Livre choisi : " + livres[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index invalide : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un entier valide.");
        }
    }

    // Exercice 5 — Analyseur de chaînes (calcul simple)
    public static void analyseurDeChaines() {
        System.out.print("Saisir une expression (ex: 4 + 2) : ");
        String expression = scanner.nextLine();
        try {
            String[] parties = expression.trim().split(" ");
            if (parties.length != 3) throw new IllegalArgumentException("Format invalide (attendu : a + b)");

            int a = Integer.parseInt(parties[0]);
            int b = Integer.parseInt(parties[2]);
            String operateur = parties[1];

            int resultat;
            switch (operateur) {
                case "+": resultat = a + b; break;
                case "-": resultat = a - b; break;
                case "*": resultat = a * b; break;
                case "/": resultat = a / b; break;
                default: throw new IllegalArgumentException("Opérateur non reconnu");
            }
            System.out.println("Résultat : " + resultat);
        } catch (NumberFormatException e) {
            System.out.println("Erreur de conversion : " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Erreur de calcul : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Appels de test en try/catch
        try {
            distributeurAutomatique();
            scanner.nextLine(); // vider le buffer
            inscriptionEvenement();
            lireFichierCSV();
            catalogueLivres();
            analyseurDeChaines();
        } catch (Exception e) {
            System.out.println("Exception par défaut capturée : " + e.getMessage());
        }
    }
}
