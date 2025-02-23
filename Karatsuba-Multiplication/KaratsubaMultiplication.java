package document;

import java.math.BigInteger;

public class KaratsubaMultiplication {

	private static BigInteger karatsubamultiplication(BigInteger x, BigInteger y) {


		if (x.bitLength() <= 10 || y.bitLength()<=10) {
			return x.multiply(y);
		}
		
		int n = Math.max(x.toString().length(), y.toString().length());
		
		BigInteger[] parts1 = splitBigInteger(x, n);

		BigInteger a = parts1[0];
		BigInteger b = parts1[1];

		BigInteger[] parts2 = splitBigInteger(y, n);

		BigInteger c = parts2[0];
		BigInteger d = parts2[1];

		BigInteger ac = karatsubamultiplication(a, c);
		BigInteger bd = karatsubamultiplication(b, d);

		BigInteger sum = karatsubamultiplication(a.add(b), c.add(d));
		
		BigInteger middleTerm = sum.subtract(ac).subtract(bd);
		
		int midpoint = n / 2 + n % 2;
		BigInteger power = BigInteger.TEN.pow(midpoint);
		
		
		return ac.multiply(power.pow(2)).add(middleTerm.multiply(power)).add(bd);

	}

	private static BigInteger[] splitBigInteger(BigInteger x, int n) {


		int midpoint = n / 2 + n % 2;

		BigInteger power = BigInteger.TEN.pow(midpoint);
		
		BigInteger part1 = x.divide(power);
		BigInteger part2 = x.mod(power);

		return new BigInteger[] { part1, part2 };
	}

	public static void main(String[] args) {
		BigInteger a = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
		BigInteger b = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");

		System.out.println(karatsubamultiplication(a, b));

	}

}
