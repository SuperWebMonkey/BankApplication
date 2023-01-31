package db.mybatis;

import db.dao.IAirlineCompanyDAO;
import db.models.AirlineCompany;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AirlineCompanyDAO implements IAirlineCompanyDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(AirlineCompany airlineCompany) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompanyDAO.createEntity(airlineCompany);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(AirlineCompany airlineCompany) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompanyDAO.updateEntity(airlineCompany);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompanyDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public AirlineCompany getEntityById(int id) {
        AirlineCompany airlineCompany;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompany = airlineCompanyDAO.getEntityById(id);
        }
        return airlineCompany;
    }

    @Override
    public List<AirlineCompany> getAllEntities() {
        List<AirlineCompany> airlineCompanies;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompanies = airlineCompanyDAO.getAllEntities();
        }
        return airlineCompanies;
    }

    @Override
    public AirlineCompany getAirlineCompanyByName(String name) {
        AirlineCompany airlineCompany;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAirlineCompanyDAO airlineCompanyDAO = sqlSession.getMapper(IAirlineCompanyDAO.class);
            airlineCompany = airlineCompanyDAO.getAirlineCompanyByName(name);
        }
        return airlineCompany;
    }
}
