package aday.repository;

import aday.config.HibernateUtil;
import aday.model.Branch;
import aday.model.Company;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyRepository {
    private final EntityManager em = HibernateUtil.getEntityManager();

    public List<Company> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // do some work
            List<Company> companyList = session.createQuery("SELECT c FROM Company c", Company.class).getResultList();
            tx.commit();
            return companyList;
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public void save(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // do some work
            session.save(company);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addBranch(Long id, Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // do some work
            String query = "INSERT INTO company_branch (Company_id, branchList_id) VALUES (:compId, :braId);\n";
            session.createNativeQuery(query)
                    .setParameter("compId", id)
                    .setParameter("braId", branch.getId())
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

    public void removeBranch(Long id, Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Company company = (Company) session.load(Company.class, id);
            company.getBranchList().remove(branch);
            session.update(company);
            session.close();
        } finally {
            session.close();
        }
    }
}
