/**
 *
 * Approximation der Gaußschen Fehlerfunktion
 * Rudolf Floren - 318099
 * Luca Giorgi - 320724
 *
 */

public class Gaus {
	
	public static void main (String[] args) {
		System.out.printf("         | n = 10  | n = 100 | n = 1000 \n");
		System.out.printf("---------+---------+---------+----------\n");
		System.out.printf(" x = %.1f | %.5f | %.5f | %.5f\n", 0d, approx(0d, 10), approx(0d, 100), approx(0d,1000));
		System.out.printf("---------+---------+---------+----------\n");
		System.out.printf(" x = %.1f | %.5f | %.5f | %.5f\n", 0.5d, approx(0.5d, 10), approx(0.5d, 100), approx(0.5d,1000));
		System.out.printf("---------+---------+---------+----------\n");
		System.out.printf(" x = %.1f | %.5f | %.5f | %.5f\n", 1d, approx(1d, 10), approx(1d, 100), approx(1d,1000));
	}


	/**
	 * Eigentliche Approximieerung
	 *
	 *
	 */
	public static double approx (double x, int n) {
		double sum = 0;
		double xi[] = new double[n+1];

		// Teilintervalgröße
		double h = x / n;
	
		// befülle xi mit den Stützstellen
		for(int i = 0; i <= n; i++) {
			xi[i] = i*h;
		}
		//Addiere die Summe von e^(-x_i^2) von i=1 bis n-1
		for(int i = 1;i<=(n-1); i++) {
			sum+=Math.exp(-1*Math.pow(xi[i], 2));
		}
		//berechne ersten Teil der Funktion
		double result = Math.exp(-1*Math.pow(xi[0], 2)) + Math.exp(-1*Math.pow(xi[n], 2));
		//Teile durch 2
		result = result / 2;

		
		//Liefere das Ergebnis * h zurück.
		return h*(result+sum);

	}
}