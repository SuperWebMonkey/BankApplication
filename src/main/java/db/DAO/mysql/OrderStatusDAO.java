package db.DAO.mysql;

import db.ConnectionPool.ConnectionPool;
import db.DAO.IOrderStatusDAO;
import db.models.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDAO implements IOrderStatusDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderStatusDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<OrderStatus> getAllEntities(){
        List<OrderStatus>  osList = new ArrayList<>();
        String sql = "SELECT * FROM order_status";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setStatusId(rs.getInt(1));
                os.setStatusName(rs.getString(2));
                osList.add(os);
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
        return osList;
    }

    public OrderStatus getEntityById(int id) {
        OrderStatus os = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM order_status WHERE status_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                os = new OrderStatus(statusId, statusName);
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
        return os;
    }

    public OrderStatus getEntityByName(String db_name) {
        OrderStatus os = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM order_status WHERE status_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, db_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                os = new OrderStatus(statusId, statusName);
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
        return os;
    }

    public OrderStatus createEntity(OrderStatus os) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO order_status (status_id, status_name) " +
                "VALUES (?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, os.getStatusId());
            ps.setString(2, os.getStatusName());
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

    public void updateEntity(OrderStatus os) {
        String sql = "UPDATE order_status SET status_name = (?) WHERE status_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update customer: " + os);
            ps.setString(1, os.getStatusName());
            ps.setInt(2, os.getStatusId());
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
        String sql = "Delete FROM order_status WHERE status_id = (?)";
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
