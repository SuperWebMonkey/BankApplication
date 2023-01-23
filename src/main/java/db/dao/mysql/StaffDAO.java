package db.dao.mysql;

import db.connectionpool.ConnectionPool;
import db.dao.IStaffDAO;
import db.models.Staff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO implements IStaffDAO {
    private static final Logger LOGGER = LogManager.getLogger(StaffDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Staff> getAllEntities() {
        List<Staff> staffList = new ArrayList<Staff>();
        String sql = "SELECT * FROM staff";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt(1));
                staff.setFirstName(rs.getString(2));
                staff.setLastName(rs.getString(3));
                staffList.add(staff);
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
        return staffList;
    }

    public Staff getEntityById(int id) {
        Staff staff = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM staff WHERE staff_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("staff_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                staff = new Staff(customerId, firstName, lastName);
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
        return staff;
    }

    public Staff getStaffByFirstName(String dbFirstName) {
        Staff staff = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM staff WHERE first_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dbFirstName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("staff_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                staff = new Staff(customerId, firstName, lastName);
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
        return staff;
    }

    public Staff getStaffByLastName(String dbLastName) {
        Staff staff = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM staff WHERE last_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dbLastName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("staff_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                staff = new Staff(customerId, firstName, lastName);
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
        return staff;
    }

    public Staff createEntity(Staff staff) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO staff (staff_id, first_name, last_name) VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, staff.getStaffId());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
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

    public void updateEntity(Staff staff) {
        String sql = "UPDATE staff SET first_name = ?, last_name = ? WHERE customer_id = ?";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update staff: " + staff);
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setInt(3, staff.getStaffId());
            ps.executeUpdate();
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
        String sql = "Delete FROM staff WHERE staff_id = (?)";
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
