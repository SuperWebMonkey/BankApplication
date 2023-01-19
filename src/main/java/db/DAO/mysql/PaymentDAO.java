package db.DAO.mysql;

import db.ConnectionPool.ConnectionPool;
import db.DAO.IPaymentDAO;
import db.models.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements IPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(PaymentDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Payment> getAllEntities(){
        List<Payment>  paymentList = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt(1));
                payment.setAmount(rs.getDouble(2));
                payment.setPaymentTypeId(rs.getInt(1));
                paymentList.add(payment);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return paymentList;
    }

    public Payment getEntityById(int id) {
        Payment payment = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM payments WHERE payment_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                double amount = rs.getDouble("amount");
                int paymentType = rs.getInt("payment_type_id");
                payment = new Payment(paymentId, amount, paymentType);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return payment;
    }

    public Payment getEntityByAmount(double db_amount) {
        Payment payment = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM payments WHERE amount = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, db_amount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                double amount = rs.getDouble("amount");
                int paymentType = rs.getInt("payment_type_id");
                payment = new Payment(paymentId, amount, paymentType);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return payment;
    }

    public Payment createEntity(Payment payment) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO payments (payment_id, amount, payment_type_id) " +
                "VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, payment.getPaymentId());
            ps.setDouble(2, payment.getAmount());
            ps.setInt(3, payment.getPaymentTypeId());
            ps.executeUpdate();
            LOGGER.info("Insertion was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return null;
    }

    public void updateEntity(Payment payment) {
        String sql = "UPDATE payments SET amount = (?), payment_type_id = (?) WHERE payment_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update customer: " + payment);
            ps.setDouble(1, payment.getAmount());
            ps.setInt(2, payment.getPaymentTypeId());
            ps.setInt(3, payment.getPaymentId());
            ps.execute();
        } catch(Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM payments WHERE payment_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            LOGGER.info("Removal was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }
}
