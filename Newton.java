package newton;
import java.util.Locale;
class Newton {
	

	public static void main(String[] args) {
		System.out.format(Locale.GERMAN, "Bitte geben sie die gewuenschte Zahl deren Quadratwurzel approximiert werden soll ein: ");
		float n = Integer.parseInt(System.console().readLine());
		float x = n/2; //x/2 ist ein guter Startwert.
		float xAlt;
		int i = 1;
		System.out.println("\nStartwert: "+x);
		System.out.println("----------------");
		do{
			xAlt = x;
			x = approx(n, x);
			System.out.format(Locale.GERMAN, i+". Schritt: %.10f\n", x);
			i++;
		}while(!isFinished(x, xAlt));

	}


	/**
	 * Approximiert die Wurzel mittels Newton Verfahren
	 * @param n float Wert von dem wir die Wurzel wissen möchten.
	 * @param ap float Alter approximierter Wert.
	 * @return float approximiertes Ergbnis.
	 */
	public static float approx(float n, float ap){
		return n/(2*ap) + ap/2;
	}
	/**
	 * Testet ob die differenz des aktuellen approx. Ergebnis und des vorherigen Approx. Ergebnis weniger als 10^-10 beträgt.
	 * @param x float aktuelles approx. Ergebnis
	 * @param xAlt float vorheriges approx. Ergebnis
	 * @return boolean
	 */
	public static boolean isFinished(float x , float xAlt) {
		float abs = x-xAlt;
		if(abs < 0){ //Betrag
			abs = abs * (-1);
		}
		return abs < 0.0000000001;
	}
}