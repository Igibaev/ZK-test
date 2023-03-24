package aday.repository;

import aday.config.HibernateUtil;
import aday.model.Address;
import aday.model.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BranchRepository {

    public Branch findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Branch branch =  session.find(Branch.class, id);
            session.close();
            return branch;
        }
        finally {
            session.close();
        }
    }

    public List<Branch> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Branch> branchList =  session.createQuery("SELECT c FROM Branch c", Branch.class).getResultList();
            session.close();
            return branchList;
        }
        finally {
            session.close();
        }
    }

    public Long save(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Long id = (Long) session.save(branch);
            session.close();
            return id;
        }
        finally {
            session.close();
        }
    }

    public void delete(Long compId, Long braId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // do some work
            String query = "DELETE FROM company_branch WHERE Company_id = ? AND branchList_id = ?;";
            session.createNativeQuery(query)
                    .setParameter(1, compId)
                    .setParameter(2, braId)
                    .executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
