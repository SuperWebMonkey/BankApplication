package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.IPaymentTypeDao;
import db.models.PaymentTypes;
import db.models.Payments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentTypeDao implements IPaymentTypeDao {
    private static final Logger LOGGER = LogManager.getLogger(PaymentTypeDao.class);

    public List<PaymentTypes> getAllEntities(){

        List<PaymentTypes>  ptList = new ArrayList<>();
        String sql = "SELECT * FROM payment_types";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaymentTypes pt = new PaymentTypes();
                pt.setPaymentType(rs.getInt(1));
                pt.setPaymentTypeName(rs.getString(2));

                ptList.add(pt);
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

        return ptList;
    }

    public PaymentTypes getEntityById(int id) {

        PaymentTypes pt = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM payment_type WHERE payment_type_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int paymentTypeId = rs.getInt("payment_type_id");
                String ptName = rs.getString("payment_type_name");

                pt = new PaymentTypes(paymentTypeId, ptName);
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

        return pt;
    }

    public PaymentTypes createEntity(PaymentTypes pt) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO payment_types (payment_type_id, payment_type_name) " +
                "VALUES (?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pt.getPaymentType());
            ps.setString(2, pt.getPaymentTypeName());
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

    public void updateEntity(PaymentTypes pt) {
        String sql = "UPDATE payment_types SET payment_type_name = (?) WHERE payment_type_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + pt);

            ps.setString(1, pt.getPaymentTypeName());
            ps.setInt(2, pt.getPaymentType());

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
        String sql = "Delete FROM payment_types WHERE payment_type_id = (?)";
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
