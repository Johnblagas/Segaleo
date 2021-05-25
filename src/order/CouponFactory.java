package order;

import java.util.Date;
import java.util.Random;

public class CouponFactory {
	private static String email = "phil@michalopoulos.gr";
	static Random rand = new Random();

	public static Coupon GenerateCoupon() {
		String code = "";
		// Βαλαμε στατικο για τεστ αλλα θα παιρνει ορισμα τον χρηστη και θα παιρνουμε
		// απο κει το μειλ
		for (int i = 0; i < 3; i++) {
			code += email.toCharArray()[rand.nextInt(email.length())];
		}
		int randomNumber = rand.nextInt(9990 + 1 - 1000) + 1000;
		code += Integer.toString(randomNumber);

		return new Coupon(code.toUpperCase(), new Date());
	}

	public static boolean isValid(Coupon coupon) {
		Date today = new Date();
		String code = coupon.getCode();
		String letters = code.substring(0, 3);
		boolean flag = true;
		// calculates the time that takes for a useer to use the coupon in milliseconds
		long mill = today.getTime() - coupon.getDate().getTime();

		// converts the millseconds to days
		long days = (long) (mill / (1000 * 60 * 60 * 24));

		// check if the format and the date of the coupon is right
		for (char x : letters.toCharArray()) {
			if (email.toUpperCase().contains(String.valueOf(x))) {
				continue;
			} else {
				flag = false;
			}
		}
		if (flag && code.length() == 7) {
			if (days < 3) {
				return true;
			}
		}
		return false;
	}
}
