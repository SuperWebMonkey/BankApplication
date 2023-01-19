package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.ICustomerDAO;
import db.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);

    public List<Customer> getAllEntities(){
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, phone FROM customers";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setPhone(rs.getString(4));

                customerList.add(customer);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return customerList;
    }

   public Customer getEntityById(int id) {
        Customer customers = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM customers WHERE customer_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");

                customers = new Customer(customerId, firstName, lastName, phone);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return customers;
    }

    public Customer createEntity(Customer customers) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customers.getCustomerId());
            ps.setString(2, customers.getFirstName());
            ps.setString(3, customers.getLastName());
            ps.setString(4, customers.getPhone());

            ps.executeUpdate();

            LOGGER.info("Insertion was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return null;
    }

    public void updateEntity(Customer customers) {
        String sql = "UPDATE customers SET first_name = (?), last_name = (?), phone = (?) WHERE customer_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + customers);

            ps.setString(1, customers.getFirstName());
            ps.setString(2, customers.getLastName());
            ps.setString(3, customers.getPhone());
            ps.setInt(4, customers.getCustomerId());
            ps.execute();
        } catch(Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM customers WHERE customer_id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();

            LOGGER.info("Removal was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }
}
