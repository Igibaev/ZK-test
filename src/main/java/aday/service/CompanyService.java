package aday.service;


import aday.model.Branch;
import aday.model.Company;
import aday.repository.CompanyRepository;

import java.util.List;

public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService() {
        this.companyRepository = new CompanyRepository();
    }

    public List<Company> findAll() {
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    public void create(Company company) {
        companyRepository.save(company);
    }

    public void addBranch(Company company, Branch branch) {
        companyRepository.addBranch(company.getId(), branch);
    }

    public void removeBranch(Company company, Branch branch) {
        companyRepository.removeBranch(company.getId(), branch);
    }
}
