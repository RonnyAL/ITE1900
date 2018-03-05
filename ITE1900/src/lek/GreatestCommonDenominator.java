package lek;

public class GreatestCommonDenominator {
	
	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		else
//			System.out.println(p % q);
			return Math.abs(gcd(q, p % q));
	}

	public static void main(String[] args) {
		int p = -394200;
		int q = 1220430;
		int d = gcd(p, q);
		System.out.println("gcd(" + p + ", " + q + ") = " + d);
	}
}
