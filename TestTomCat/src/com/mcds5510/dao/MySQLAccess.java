package com.mcds5510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.mcds5510.entity.Transaction;
import com.mcds5510.logger.SimpleLogger;

public class MySQLAccess {

	
	// select method for Transaction...1
		public Collection<Transaction> getAllTransactions(Connection connection) {
			Statement statement = null;
			ResultSet resultSet = null;
			Collection<Transaction> results = null;
			// Result set get the result of the SQL query
			try {
				// Statements allow to issue SQL queries to the database
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from transaction1");
				results = createTrxns(resultSet);

				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SimpleLogger.logger.warning(e.toString());
			} finally {
				statement = null;
				resultSet = null;
			}
			return results;

		}

		// select method for Transaction...2

		public Collection<Transaction> getTheTransactions(Connection connection, int txnID) {
			Statement statement = null;
			ResultSet resultSet = null;
			Collection<Transaction> results = null;
			// Result set get the result of the SQL query
			try {
				// Statements allow to issue SQL queries to the database
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from transaction1 where ID=" + txnID);
				results = createTrxns(resultSet);

				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SimpleLogger.logger.warning(e.toString());
			} finally {
				statement = null;
				resultSet = null;
			}
			return results;

		}

		private Collection<Transaction> createTrxns(ResultSet resultSet) throws SQLException {
			Collection<Transaction> results = new ArrayList<Transaction>();

			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				Transaction trxn = new Transaction();
				trxn.setNameOnCard(resultSet.getString("NameOnCard"));
				trxn.setCardNumber(resultSet.getString("CardNumber"));
				trxn.setExpDate(resultSet.getString("ExpDate"));

				trxn.setId(resultSet.getInt("ID"));
				trxn.setExpDate(resultSet.getString("ExpDate"));
				trxn.setUnitPrice(resultSet.getDouble("UnitPrice"));
				trxn.setQuantity(resultSet.getInt("Quantity"));
				trxn.setTotalPrice(resultSet.getDouble("TotalPrice"));
				trxn.setCreatedBy(resultSet.getString("CreatedBy"));
				trxn.setCreditCardType(resultSet.getString("CreditCardType"));
				results.add(trxn);

			}

			return results;

		}

		// insert method for Transaction.......

		public boolean createTransaction(Connection con, Collection<Transaction> txn) {

			try {
				for (Transaction trxn : txn) {

					System.out.println("Name on the card = " + trxn.getNameOnCard());
					String insertSql = "INSERT INTO Transaction1 (ID,NameOnCard,CardNumber,UnitPrice,\r\n"
							+ "	Quantity,TotalPrice,ExpDate,CreatedOn,CreatedBy,CreditCardType) VALUES (?,?,?,?,?,?,?,?,?,?)";

					PreparedStatement statement = con.prepareStatement(insertSql);
					statement.setInt(1, trxn.getId());
					statement.setString(2, trxn.getNameOnCard());
					statement.setString(3, trxn.getCardNumber());
					statement.setDouble(4, trxn.getUnitPrice());
					statement.setInt(5, trxn.getQuantity());
					statement.setDouble(6, trxn.getTotalPrice());
					statement.setString(7, trxn.getExpDate());
					Timestamp createdOn = new Timestamp(trxn.getCreatedOn());

					statement.setTimestamp(8, createdOn);
					statement.setString(9, trxn.getCreatedBy());
					statement.setString(10, trxn.getCreditCardType());

					int rowInserted = statement.executeUpdate();

					if (rowInserted > 0) {
						SimpleLogger.logger.info("A new row is inserted " + rowInserted);
						return true;
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SimpleLogger.logger.warning(e.toString());
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// call exceptionHandling class...
						SimpleLogger.logger.warning(e.toString());
					}
				}
			}

			return false;

		}

		// delete Transaction method
		public boolean deleteTransaction(Connection con, int id) {

			try {
				String deleteSql = "DELETE FROM transaction1 WHERE ID=?";
				PreparedStatement statement = con.prepareStatement(deleteSql);
				statement.setInt(1, id);

				int rowsDeleted = statement.executeUpdate();

				if (rowsDeleted > 0) {
					SimpleLogger.logger.info("A user was deleted successfully!");
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SimpleLogger.logger.warning(e.toString());
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						SimpleLogger.logger.warning(e.toString());
					}
				}
			}

			return false;
		}

		// updateTransaction...

		public boolean updateTransaction(Connection con, Collection<Transaction> txn) {

			try {
				for (Transaction trxn : txn) {
					String updatesql = "UPDATE transaction1 SET NameOnCard=?,CardNumber=?,"
							+ "UnitPrice=?,Quantity=?,TotalPrice=?,ExpDate=?,CreatedON=?,CreatedBy=?,CreditCardTpye=? WHERE ID=?";

					PreparedStatement statement = con.prepareStatement(updatesql);
					statement.setString(1, trxn.getNameOnCard());
					statement.setString(2, trxn.getCardNumber());
					statement.setDouble(3, trxn.getUnitPrice());
					statement.setInt(4, trxn.getQuantity());
					statement.setDouble(5, trxn.getTotalPrice());

					statement.setString(6, trxn.getExpDate());
					Timestamp createdOn = new Timestamp(trxn.getCreatedOn());
					statement.setObject(7, createdOn);

					statement.setString(8, trxn.getCreatedBy());
					statement.setString(9, trxn.getCreditCardType());
					statement.setInt(10, trxn.getId());

					int rowUpdated = statement.executeUpdate();

					if (rowUpdated > 0) {
						SimpleLogger.logger.info("An Existing user updated successfully!");
						return true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SimpleLogger.logger.warning(e.toString());
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						SimpleLogger.logger.warning(e.toString());
					}

				}
			}
			return false;
		}
}
