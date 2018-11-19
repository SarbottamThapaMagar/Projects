package com.mcds5510;


import com.mcds5510.dao.TransactionMethods;
import com.mcds5510.logger.SimpleLogger;

public class Main {

	public static void main(String[] args) {
		new SimpleLogger();

		SimpleLogger.logger.info("Enter the Transaction type:");
		String transactionType = TransactionMethods.scannerMethod().nextLine();

		userTransactionSelection(transactionType);

	}

	public static void userTransactionSelection(String transactionType) {
		boolean n = true;
		while (n == true) {
			switch (transactionType.toLowerCase()) {

			case "select":
//				System.out.println("Enter transaction Id: ");
				SimpleLogger.logger.info("You're going to select data:");

				SimpleLogger.logger.info("Enter transaction Id: ");
				int txnId = TransactionMethods.scannerMethod().nextInt();

				if (TransactionMethods.selectMethod(txnId)) {
					SimpleLogger.logger.info("success");
				} else {
//					System.out.println("no such data found:");
					SimpleLogger.logger.info("no such data found:");
				}

				n = false;
				break;

			case "insert":
				SimpleLogger.logger.info("Insert Transaction:");
//				System.out.println("Enter Transaction ID: ");
				SimpleLogger.logger.info("Enter Transaction ID: ");
				int insertId = TransactionMethods.scannerMethod().nextInt();
				TransactionMethods.insertMethod(insertId);
				n = false;
				break;
			case "update":
				SimpleLogger.logger.info("update Transaction: ");
//				System.out.println("Enter userID to update: ");
				SimpleLogger.logger.info("Enter userID to update: ");
				int updateId = TransactionMethods.scannerMethod().nextInt();
				
				TransactionMethods.updateMethod(updateId);
				n = false;
				break;
			case "delete":
//				System.out.println("delete Transaction: ");
				SimpleLogger.logger.info("delete Transaction: ");
				if (TransactionMethods.deleteMethod()) {
					SimpleLogger.logger.info("Transaction is Successfully deleted!");
				} else {
					SimpleLogger.logger.info("failed to delete");

				}
				n = false;
				break;

			default:
				SimpleLogger.logger.info("enter only select,insert,update or delete transaction type:");
//				System.out.println("Enter the Transaction type: ");
				SimpleLogger.logger.info("Enter the Transaction type: ");

				transactionType = TransactionMethods.scannerMethod().nextLine();

				break;
			}
		}
	}

}
