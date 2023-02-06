package db.mybatis;

import db.dao.IOrderDAO;
import db.models.Order;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(Order order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            orderDAO.createEntity(order);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Order order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            orderDAO.updateEntity(order);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            orderDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public Order getEntityById(int id) {
        Order order;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            order = orderDAO.getEntityById(id);
        }
        return order;
    }

    @Override
    public List<Order> getAllEntities() {
        List<Order> orders;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            orders = orderDAO.getAllEntities();
        }
        return orders;
    }

    @Override
    public Order getOrderByPayment(double price) {
        Order order;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            order = orderDAO.getOrderByPayment(price);
        }
        return order;
    }
}
