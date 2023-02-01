package db.mybatis;

import db.dao.IDrivingCompanyDAO;
import db.models.DrivingCompany;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DrivingCompanyDAO implements IDrivingCompanyDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(DrivingCompany dc) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            dcDAO.createEntity(dc);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(DrivingCompany dc) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            dcDAO.updateEntity(dc);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            dcDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public DrivingCompany getEntityById(int id) {
        DrivingCompany dc;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            dc = dcDAO.getEntityById(id);
        }
        return dc;
    }

    @Override
    public List<DrivingCompany> getAllEntities() {
        List<DrivingCompany> drivingCompanies;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            drivingCompanies = dcDAO.getAllEntities();
        }
        return drivingCompanies;
    }

    @Override
    public DrivingCompany getDrivingCompanyByPrice(double price) {
        DrivingCompany dc;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDrivingCompanyDAO dcDAO = sqlSession.getMapper(IDrivingCompanyDAO.class);
            dc = dcDAO.getDrivingCompanyByPrice(price);
        }
        return dc;
    }
}
