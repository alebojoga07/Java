import java.io.*;
import java.util.*;

import org.json.*;

import p1.Scaun;

/**
 * Clasa principala main 
 */
public class Main {
	
	/**
	 * Metoda principala care prezinta gestionarea scaunelor
	 * @param args Argumentele din linia de comanda
	 */
    public static void main(String[] args) {
    	//Citirea datelor din fisierul CSV
        String pathScaun = "src\\Scaun.csv";
        
        //Citirea datelor despre scaune din fisierul CSV intr-o lista
        List<Scaun> listaScaune = citire(pathScaun);

        //Sortarea listei de scaune in functie de pret folosind implementarea compareTo din clasa Scaun
        Collections.sort(listaScaune);

        //Afisarea scaunelor sortate
        for (Scaun scaun : listaScaune) {
            System.out.println(scaun);
        }

        //Verificare daca lista de scaune nu este goala inainte de a accea primul scaun din lista
        if (!listaScaune.isEmpty()) {
        	//Primul scaun din lista
            Scaun primulScaun = listaScaune.get(0);
            //Convertirea primului scaun din lista in formatul JSON si afisarea acestuia
            String jsonScaun = primulScaun.conversieJSON();
            System.out.println(jsonScaun);
        }

        //Apelarea metodei producatoriScaune pentru a afisa producatorii scaunelor din fisierul CSV
        producatoriScaune(listaScaune);
    }
/**
 * Metoda care citeste datele despre scaune din fisierul CSV si returneaza o lista cu acestea
 * @param pathScaun calea catre fisierul CSV
 * @return O lista de obiecte Scaun 
 * 
 * @throws FileNotFoundException Daca fisierul  nu poate fi gasit
 * @throws IOException Daca apare  eroare in timpul citirii datelor din fisier
 */
    public static List<Scaun> citire(String pathScaun) {
        List<Scaun> listaScaune = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(pathScaun))) {
            br.readLine();

            while ((line = br.readLine()) != null) { 
            	// Separam liniile in campuri separate prin ","
                String[] values = line.split(",");
                //Elimin spatiile nefolositoare din jurul valorilor
                String producator = values[0].trim(); 
                String material = values[1].trim();
                double pret = Double.parseDouble(values[2].trim());
                
                //Creez un obiect de tip Scaun si il adaug in lista
                Scaun scaun = new Scaun(producator, material, pret); 
                listaScaune.add(scaun);
            }
            //Cazul in care fisierul nu poate fi gasit
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
            //Cazul in care apare o eroare in timpul citirii datelor din fisier
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaScaune;
    }

    /**
     * Afișează producătorii de scaune și scrie datele despre scaunele fiecărui producător într-un fișier JSON separat.
     * @param listaScaune 
     */
    public static void producatoriScaune(List<Scaun> listaScaune) {
    	
    	//Dictionar pentru a grupa scaunele dupa producatori
        Map<String, List<Scaun>> scaunePeProducatori = new HashMap<>(); 

        //Parcurg lista de scaune si le grupez in functie de producator, iar daca producatorul nu a fost gasit creez un nou fisier
        for (Scaun scaun : listaScaune) {
            scaunePeProducatori.computeIfAbsent(scaun.getProducator(), k -> new ArrayList<>()).add(scaun); 
        }

        // Parcurg dictionarul cu scaunele grupate in functie de producatorsi scriu datele in fisere sepaeare in format JSON
        for (Map.Entry<String, List<Scaun>> entry : scaunePeProducatori.entrySet()) {
            String producator = entry.getKey();  
            List<Scaun> scauneProducator = entry.getValue(); 
            String numeFisier = producator + ".json"; //numele fisierului
            scriereJSON(numeFisier, scauneProducator); //Apelarea metodei pentru a scrie datele in fisierul JSON
        }
    }

    /**
     * Scrierea datelor despre scaune intr-un fisier JSON
     * @param numeFisier
     * @param scaune
     */
    public static void scriereJSON(String numeFisier, List<Scaun> scaune) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {  
        	//Parcurgerea listei de scaune  si conversia datelor in format JSON
            for (int i = 0; i < scaune.size(); i++) {  
                writer.write(scaune.get(i).conversieJSON()); 
                //Verificare daca este ultimul element din lista, daca nu se adauga virgula intre obiectele JSON
                if (i < scaune.size() - 1) { 
                    writer.write(","); 
                }
                writer.write("\n"); 
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }
}
