package com.mcds5510.valid;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcds5510.dao.TransactionMethods;
import com.mcds5510.entity.Transaction;
import com.mcds5510.logger.SimpleLogger;

public class ValidationTest {

	public static boolean validationMethod(String input) {
		if (input == null || input.trim().isEmpty()) {
			System.out.println("Empty value: Re-enter the value");
			return false;
		}
		String io = input.trim();
		Pattern special = Pattern.compile("[!@#$%*+<>?{}:;^]");
		Pattern digits = Pattern.compile("[0-9]");

		Matcher ms = special.matcher(io);
		Matcher md = digits.matcher(io);

		if (ms.find() || md.find()) {
			System.out.println("Invalid data: Re-enter value:");
			return false;
		} else {
			System.out.println("valid data:");
		}
		return true;
	}

	public static boolean isEmpyAllField(String value) {
		if (value == null || value.trim().isEmpty()) {
			System.out.println("You can't enter a empty value");
			return false;
		}
		return true;
	}

	public static void validationCerditCard(String cardType) {

	}

	public static long validateAmericanExpress(Scanner sc) {
		try {
			long cardNumber = sc.nextLong();
			long input = cardNumber / 10;
			int l = 1;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr = firstDigit(cardNumber);
			if (arr.get(0) == 3 && arr.get(1) == 15) {
				// look for second digit
				long reverseFirst = reverseMethod(input);
				reverseFirst = reverseFirst / 10;
				long reverseSecond = reverseMethod(reverseFirst);
				arr = firstDigit(reverseSecond);
				if (arr.get(0) == 4 || arr.get(0) == 7) {
					return cardNumber;
				} else {
					System.out.println("not valid");

				}

			} else {
				System.err.println("not valid card!!");
			}
		} catch (InputMismatchException e) {
			SimpleLogger.logger.warning(e.toString());
		}
		return 0;
	}

	public static long validateVisaCard(Scanner sc) {

		try {
			long cardNumber = sc.nextLong();
			long input = cardNumber / 10;
//		int reversedNum = 0;
			int l = 1;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr = firstDigit(cardNumber);
			if (arr.get(0) == 4 && arr.get(1) == 16) {
				System.out.println("Fine works::");
				return cardNumber;
			} else {
				System.err.println("not valid card!!");
			}
		} catch (InputMismatchException e) {
			SimpleLogger.logger.warning(e.toString());
		}
		return 0;
	}

	public static long validateMasterCard(Scanner sc) {
		try {
			long cardNumber = sc.nextLong();
			long input = cardNumber / 10;
			int reversedNum = 0;
			int l = 1;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr = firstDigit(cardNumber);
			if (arr.get(0) == 5 && arr.get(1) == 16) {
				long reverseFirst = reverseMethod(input);
				reverseFirst = reverseFirst / 10;
				long reverseSecond = reverseMethod(reverseFirst);
				arr = firstDigit(reverseSecond);
				if (arr.get(0) == 1 || arr.get(0) == 2 || arr.get(0) == 3 || arr.get(0) == 4 || arr.get(0) == 5) {
					System.out.println("valid" + arr.get(0));
					return cardNumber;
				} else {
					System.out.println("not valid" + arr.get(0));

				}
			} else {
				System.err.println("not valid card!!");
			}
		} catch (InputMismatchException e) {
			SimpleLogger.logger.warning(e.toString());
		}

		return 0;
	}

	private static ArrayList<Integer> firstDigit(long n) {
		ArrayList<Integer> arr = new ArrayList<>();
		int l = 1;
		while (n >= 10) {
			long temp = n / 10;
			n = temp;
			l++;
		}
		int num = (int) n;
		arr.add(num);
		arr.add(l);
		return arr;
	}

	private static long reverseMethod(long reversedNum) {
		long rese = 0;
		while (reversedNum != 0) {
			rese = rese * 10 + reversedNum % 10;
			reversedNum = reversedNum / 10;
		}

		return rese;
	}
	
	
	public static void expireDateValidation(Transaction trans) {
		String ExpireDate = TransactionMethods.scannerMethod().nextLine();
		String[] monthYear = ExpireDate.split("\\/");
		String month = monthYear[0];
		String year = monthYear[1];
		int yearNum = Integer.parseInt(year);
		int yearLastDigit = yearNum % 2000;
		int monthNum = Integer.parseInt(month);
		int monthCheck = monthNum % 10;
		if (monthCheck >= 1 && monthNum <= 12) {
			if (yearLastDigit >= 16 && yearLastDigit <= 31) {
				trans.setExpDate(ExpireDate);

			} else {
				SimpleLogger.logger.info("not valid year ");
				// not valid expireDate setting to default 01/2016
				trans.setExpDate("01/2016");
			}
		} else {
			trans.setExpDate("01/2016");
		}
	}

	// validation for Varchar..
	public static String validateVar(Scanner sc) {
		String finalName;
		do {
			while (!sc.hasNextLine()) {
				System.out.println("Enter value not valid!!");
			}
			finalName = sc.nextLine();
		} while (!ValidationTest.validationMethod(finalName));
		System.out.println("Valid input: " + finalName);

		return finalName;
	}
}
