package db.dao.mysql;

import db.connectionpool.ConnectionPool;
import db.dao.IOrderDAO;
import db.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Order> getAllEntities() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setPayment(rs.getDouble(2));
                order.setCustomerId(rs.getInt(3));
                order.setStaffId(rs.getInt(4));
                order.setToursId(rs.getInt(5));
                order.setStatusId(rs.getInt(6));
                order.setPaymentId(rs.getInt(7));
                order.setDrivingCompaniesId(rs.getInt(8));
                orderList.add(order);
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
        return orderList;
    }

    public Order getEntityById(int id) {
        Order order = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM orders WHERE order_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                double payment = rs.getDouble("payment");
                int customerId = rs.getInt("customer_id");
                int staffId = rs.getInt("staff_id");
                int toursId = rs.getInt("tours_id");
                int statusId = rs.getInt("status_id");
                int paymentId = rs.getInt("payment_id");
                int drivingId = rs.getInt("driving_id");
                order = new Order(orderId, payment, customerId, staffId, toursId, statusId, paymentId, drivingId);
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
        return order;
    }

    public Order getOrderByPayment(double payment) {
        Order order = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM orders WHERE payment = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, payment);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                double oPayment = rs.getDouble("payment");
                int customerId = rs.getInt("customer_id");
                int staffId = rs.getInt("staff_id");
                int toursId = rs.getInt("tours_id");
                int statusId = rs.getInt("status_id");
                int paymentId = rs.getInt("payment_id");
                int drivingId = rs.getInt("driving_id");
                order = new Order(orderId, oPayment, customerId, staffId, toursId, statusId, paymentId, drivingId);
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
        return order;
    }

    public void createEntity(Order order) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO hotels (order_id, payment, customer_id, staff_id, tours_id, status_id, payment_id, driving_Id) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, order.getOrderId());
            ps.setDouble(2, order.getPayment());
            ps.setInt(3, order.getCustomerId());
            ps.setInt(4, order.getStaffId());
            ps.setInt(5, order.getToursId());
            ps.setInt(6, order.getStatusId());
            ps.setInt(7, order.getPaymentId());
            ps.setInt(8, order.getDrivingCompaniesId());
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
    }

    public void updateEntity(Order order) {
        String sql = "UPDATE orders SET payment = (?), customer_id = (?), staff_id = (?), " +
                "tours_id = ?, status_id = ?, payment_id = ?, driving_id = ? WHERE order_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update customer: " + order);
            ps.setDouble(1, order.getPayment());
            ps.setInt(2, order.getCustomerId());
            ps.setInt(3, order.getStaffId());
            ps.setInt(4, order.getToursId());
            ps.setInt(5, order.getStatusId());
            ps.setInt(6, order.getPaymentId());
            ps.setInt(7, order.getDrivingCompaniesId());
            ps.setInt(8, order.getOrderId());
            ps.execute();
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

    public void removeEntity(int id) {
        String sql = "Delete FROM orders WHERE order_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
