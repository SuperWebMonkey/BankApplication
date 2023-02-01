package db.mybatis;

import db.dao.ICustomerDAO;
import db.models.Customer;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.createEntity(customer);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Customer customer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.updateEntity(customer);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customerDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public Customer getEntityById(int id) {
        Customer customer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getEntityById(id);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllEntities() {
        List<Customer> customers;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customers = customerDAO.getAllEntities();
        }
        return customers;
    }

    @Override
    public Customer getCustomerByFirstName(String firstName) {
        Customer customer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getCustomerByFirstName(firstName);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByLastName(String lastName) {
        Customer customer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getCustomerByFirstName(lastName);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        Customer customer;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getCustomerByFirstName(phone);
        }
        return customer;
    }
}
