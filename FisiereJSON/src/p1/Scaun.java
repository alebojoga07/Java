package p1;
import org.json.*;
/**
 * O clasa Scaun care are ca si atribute producatorul, materialul si pretul unui scaun
 * @author Bojoga Andreea Alexandra
 */
public class Scaun implements Comparable<Scaun>{
	private String producator;
	private String material;
	private double pret;
	
	/**
	 * Constructorul implicit al clasei Scaun
	 */
	public Scaun() {
		producator="necunoscut";
		material="necunoscut";
		pret=0;
	}
	
	/**
	 * Constructorul cu parametrii pentru producator, material si pret
	 * @param producator producatorul scaunului
	 * @param material materialul scaunului
	 * @param pret pretul scaunului
	 */
	public Scaun(String producator, String material, double pret) {
		this.producator=producator;
		this.material=material;
		this.pret=pret;
	}
	
	/**
	 * Metoda get pentru atributul producator al clasei Scaun
	 * @return producatorul scaunului
	 */
	public String getProducator() {
		return producator;
	}

	/**
	 * Metoda get pentru atributul material al clasei Scaun
	 * @return materialul scaunului
	 */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * Metoda get pentru atributul pret al clasei Scaun
	 * @return pretul scaunului
	 */
	public double getPret() {
		return pret;
	}
	/**
	 * Metoda de afisare a clasei Scaun
	 */
	@Override
	public String toString() {
		return producator+ " "+material+" "+pret;
	}
	
	/**
	 * Compara doua obiecte de tip Scaun in functie de pretul acestora
	 */
	@Override
	public int compareTo(Scaun other) {
        return Double.compare(this.pret, other.pret);
    }
	
	/**
	 * Converteste obiectul Scaun care are atributele producator, material si pret  intr-un format JSON
	 * @return Un sir de caractere in care obiectele de tip Scaun sunt in format JSON
	 */
	 public String conversieJSON() {
	        JSONObject jsonScaun = new JSONObject();  
	        jsonScaun.put("producator", producator); 
	        jsonScaun.put("material", material);
	        jsonScaun.put("pret", pret);
	        return jsonScaun.toString(); 
	    }
}
