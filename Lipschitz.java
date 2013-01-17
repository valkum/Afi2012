/**
 * Rudolf Floren - 318099
 * Zum 2. mal ist der Übungspartner abgesprungen.
 */
public class Lipschitz {
	private double a;
	private double b;
	private static double[] xdach = {7., 11.,};
	private static double[] xdach2 = {7., 3.,};
	private static double[] xdach3 = {2., 11.,};
	private static double[] xdach4 = {2., 3.,};

	public static void main (String[] args) {
		double[] x_0 = new double[2];
		Lipschitz l1 = new Lipschitz(0., 4.);
		System.out.printf("[0, 4]^2   : %.20f\n", l1.approx());
		Lipschitz l2 = new Lipschitz(-5./2., 5.);
		System.out.printf("[-5/2, 5]^2: %.20f\n", l2.approx());
		Lipschitz l3 = new Lipschitz(-3., 8.);
		System.out.printf("[-3, 8]^2  : %.20f\n", l3.approx());
		double eps = Math.pow(10, -5);
		int m = 1000;
		System.out.println("Fixpunktiteration (i)");
		x_0[0] = 0.;
		x_0[1] = 0.;
		iterate(x_0, eps, l1.approx(), m);
		System.out.println("Fixpunktiteration (ii)");
		x_0[0] = -5./2.;
		x_0[1] = 5.;
		iterate(x_0, eps, l2.approx(), m);
		System.out.println("Fixpunktiteration (iii)");
		x_0[0] = 15./2.;
		x_0[1] = -2.;
		iterate(x_0, eps, l3.approx(), m);

	}
	public Lipschitz (double a, double b) {
		this.a = a;
		this.b = b;
	}
	public static double[] phi(double[] x) {
		double[] phi = new double[2];
		phi[0] =  (1./8.)*Math.pow(x[0], 2) - (1./8.)*x[0] + (7./4.);
		phi[1] = (1./9.)*Math.pow(x[1], 2) - (5./9.)*x[1] + (33./9.);
		return phi;
	}

	public double[] x(int i, int j) {
		double[] x = new double[2];
		x[0] = this.a + i*((this.b-this.a)/10);
		x[1] = this.a + j*((this.b-this.a)/10);
		return x;
	}
	public static double norm(double[] x) {
		return Math.sqrt( Math.pow(x[0], 2) + Math.pow(x[1], 2) );
	}
	public static double[] vektordiff (double[] x1, double[] x2) {
		double[]diff = new double[2];
		diff[0] = x1[0] - x2[0];
		diff[1] = x1[1] - x2[1];
		return diff;
	}
	public static double min (double[] x) {
		double min = x[0];
		for(double y : x) {
			if(y < min) min = y;
		}
		return min;
	}
	public double approx() {
		Double L_max = null;
		double L_ijkl;

		for (int i = 0; i<=10; i++) {
			for (int j = 0; j<=10; j++) {
				for (int k = 0; k <= 10; k++) {
					for (int l = 0; l <= 10; l++){
						if((i == k) && (j == l)) {
							L_ijkl = 0;
						} else {
							double zaehler = norm( vektordiff( phi( x(i, j) ) , phi( x(k, l) ) ) );
							double nenner = norm( vektordiff( x(i, j), x(k, l) ) );
							L_ijkl = zaehler/nenner;
						}
						if(L_max == null) L_max = L_ijkl; // erster durchlauf L_max ist noch leer.
						if(L_ijkl > L_max) L_max = L_ijkl;
					}
				}
			}
		}
		return L_max;
	}

	public static void iterate(double[] x_0, double eps, double L, int m) {
		Double err = null;
		double[] x_1 = phi(x_0);
		double[] x_k = x_0;
		boolean end = false;
		int k = 0;
		while (k < m) {
			if(k != 0){
				x_k = phi(x_k);
			}
			// kleinsten Fixpunkt auswählen
			double[] z = {norm(vektordiff(x_k, xdach)), norm(vektordiff(x_k, xdach2)), norm(vektordiff(x_k, xdach3)), norm(vektordiff(x_k, xdach4)),};
			err = min(z);
			double L_k =  (Math.pow(L, k)/1-L)*norm(vektordiff(x_1,x_0));
			System.out.printf("err = %.20f\n", err);
			System.out.printf("L_k = %.20f\n", L_k);
			System.out.printf("eps = %.20f\n", eps);
			end = ((err <= L_k) && (L_k <= eps));
			System.out.println(end);
			if (L < 1 && end) break;
			k++;
		}

		System.out.printf("x_k = (%f, %f)\n", x_k[0], x_k[1]);
		System.out.printf("err = %f\n", err);
		System.out.printf("k = %d\n", k);
	}
}
