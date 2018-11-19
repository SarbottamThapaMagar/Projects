package com.mcds5510.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.mcds5510.connect.DBConnection;
import com.mcds5510.entity.Transaction;
import com.mcds5510.logger.SimpleLogger;
import com.mcds5510.valid.ValidationTest;

public class TransactionMethods {

	
	public TransactionMethods() {
		new SimpleLogger();
	}

//Scanner for the class..
	public static Scanner scannerMethod() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}

	// method to check existence of user...
	private static boolean checkId(int insertId) {

		if (selectMethod(insertId)) {
			return true;
		}
		return false;
	}

	// insert method..
	public static void insertMethod(int id) {

		Collection<Transaction> insertTrans = new ArrayList<Transaction>();

		if (checkId(id)) {
			SimpleLogger.logger.info("Id exists: update!!(Y/N)");
			String request = TransactionMethods.scannerMethod().nextLine();
			if (request.toLowerCase().equals("y")) {
				// call update method from here
				updateMethod(id);
			} else {
				System.exit(0);
				;
			}
		} else {
			Connection connection = null;
			Transaction trans = new Transaction();

			trans.setId(id);
			userInput(insertTrans, trans);

			// SQL insert......
			MySQLAccess dao = new MySQLAccess();

			try {
				connection = DBConnection.getDBConnection();

				boolean trxns = dao.createTransaction(connection, insertTrans);
			} catch (Exception e) {
				SimpleLogger.logger.warning("Exception: " + e.toString());
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// calling exception class...
						SimpleLogger.logger.warning("SQLException: " + e.toString());

					}
				}
			}

		}

	}

	// select method for getTransaction.....
	public static boolean selectMethod(int id) {
		MySQLAccess dao = new MySQLAccess();

		try {
			Connection connection = DBConnection.getDBConnection();
			Collection<Transaction> trxns = dao.getTheTransactions(connection, id);
			if (!trxns.isEmpty()) {
				for (Transaction trxn : trxns) {
					SimpleLogger.logger.info(trxn.toString());
				}
			} else {
				if (connection != null) {
					connection.close();
				}
				return false;
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SimpleLogger.logger.warning("Exception: " + e.toString());

		}

		return true;
	}

	// update method for updateTransaction
	public static boolean updateMethod(int id) {

		Collection<Transaction> insertTrans = new ArrayList<Transaction>();

		if (!checkId(id)) {
			SimpleLogger.logger.info("Id dont exists: create new id !!(Y/N)");
			String request = TransactionMethods.scannerMethod().nextLine();
			if (request.toLowerCase().equals("y")) {
				// call insert method from here
				insertMethod(id);
			} else {
				System.exit(0);
			}
		} else {
			Connection connection = null;
			Transaction trans = new Transaction();

			trans.setId(id);
			userInput(insertTrans, trans);

			// SQL insert......
			MySQLAccess dao = new MySQLAccess();

			try {
				connection = DBConnection.getDBConnection();

				boolean trxns = dao.updateTransaction(connection, insertTrans);
			} catch (Exception e) {
				SimpleLogger.logger.warning("Exception: " + e.toString());

			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// calling exception class...
						SimpleLogger.logger.warning("SQLException: " + e.toString());
					}
				}
			}

		}

		return false;
	}

	// delete method calling deleteTransaction....
	public static boolean deleteMethod() {
		MySQLAccess dao = new MySQLAccess();
		Connection connection = null;
		Collection<Transaction> trxns;

		System.out.println("Enter Transaction ID to delete: ");
		int deleteId = scannerMethod().nextInt();

		if (checkId(deleteId)) {
			try {
				boolean result = dao.deleteTransaction(connection, deleteId);
				return result;

			} catch (Exception e) {
				SimpleLogger.logger.warning("Exception: " + e.toString());

			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						SimpleLogger.logger.warning("SQLException: " + e.toString());
					}
				}
			}
		} else {
			SimpleLogger.logger.info("User don't Exists!!");
		}

		return false;

	}

	private static void userInput(Collection<Transaction> insertTrans, Transaction trans) {
		long millis = System.currentTimeMillis();
		long cardNo = 0;
		String creditCardTpye = null;
		System.out.println("Enter the Name on the Card:");
		String name = ValidationTest.validateVar(scannerMethod());
		trans.setNameOnCard(name);
		System.out.println("Enter credit card type: " + "M for Masters, V for Visa or AE for American Express");
		Scanner sc = new Scanner(System.in);
		String cardType = sc.nextLine();
		switch (cardType.toUpperCase()) {
		case "M":
			// Masters condition..

			System.out.println("Enter number on the card: ");
			cardNo = ValidationTest.validateMasterCard(sc);
			creditCardTpye ="MASTER";
			break;
		case "V":
			// Visa condition...
			System.out.println("Enter number on the card: ");
			cardNo = ValidationTest.validateVisaCard(sc);
			creditCardTpye ="VISA";

			break;
		case "AE":
			// condition
			System.out.println("Enter number on the card: ");
			cardNo = ValidationTest.validateAmericanExpress(sc);
			creditCardTpye ="AMERICAN EXPRESS";

			break;
		default:

			break;
		}


		trans.setCardNumber(Long.toString(cardNo));
		trans.setCreditCardType(creditCardTpye);
		System.out.println("Enter the Expire Date:");
		ValidationTest.expireDateValidation(trans);
		
		trans.setCreatedBy(System.getProperty("user.name"));
		System.out.println("Enter the Quantity:");
		trans.setQuantity(scannerMethod().nextInt());
		System.out.println("Enter the unit price:");
		trans.setUnitPrice(scannerMethod().nextDouble());
		System.out.println("Enter the total price:");
		trans.setTotalPrice(scannerMethod().nextDouble());
		trans.setCreatedOn(millis);

		insertTrans.add(trans);
	}



	// validation for not null

//	public static long validateInteger(Scanner sc) {
//		long finalName;
//		do {
//			while (!sc.hasNextLine()) {
//				System.out.println("Enter value not valid!!");
//			}
//			finalName = sc.nextLong();
//		} while (!ValidationTest.validationCerditCard());
//		System.out.println("Valid input: " + finalName);
//
//		return finalName;
//	}

}
