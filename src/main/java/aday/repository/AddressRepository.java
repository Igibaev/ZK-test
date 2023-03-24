package aday.repository;

import aday.config.HibernateUtil;
import aday.model.Address;
import aday.model.Company;
import org.hibernate.Session;

import java.util.List;

public class AddressRepository {

    public List<Address> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Address> addressList =  session.createQuery("SELECT c FROM Address c", Address.class).getResultList();
            session.close();
            return addressList;
        }
        finally {
            session.close();
        }
    }

    public void save(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.save(address);
            session.close();
        }
        finally {
            session.close();
        }
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Address address = (Address) session.load(Address.class, id);
            session.delete(address);
            session.close();
        }
        finally {
            session.close();
        }
    }
}
