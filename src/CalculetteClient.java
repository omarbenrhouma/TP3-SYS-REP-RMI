import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculetteClient {
    public static void main(String[] args) {
        try {
            // Obtention du registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Récupération du service RMI distant
            CalculetteInterface calculette = (CalculetteInterface) registry.lookup("CalculetteServeur");

            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("Opération souhaitée (1: addition, 2: Soustraction, 3: Multiplication, 4: Division) : ");
                int codeOperation = scanner.nextInt();

                System.out.print("Premier opérande : ");
                double operand1 = scanner.nextDouble();

                System.out.print("Deuxième opérande : ");
                double operand2 = scanner.nextDouble();

                double resultat = 0;

                switch (codeOperation) {
                    case 1:
                        resultat = calculette.addition(operand1, operand2);
                        break;
                    case 2:
                        resultat = calculette.soustraction(operand1, operand2);
                        break;
                    case 3:
                        resultat = calculette.multiplication(operand1, operand2);
                        break;
                    case 4:
                        resultat = calculette.division(operand1, operand2);
                        break;
                    default:
                        System.out.println("Code d'opération invalide.");
                        break;
                }

                System.out.println("Résultat : " + resultat);

                System.out.print("Voulez-vous continuer ? (O/N) : ");
            } while (scanner.next().equalsIgnoreCase("O"));

            System.out.println("Fin du programme.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}