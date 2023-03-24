package aday.viewModel;

import aday.model.Address;
import aday.model.Branch;
import aday.model.Company;
import aday.model.LegalForm;
import aday.repository.AddressRepository;
import aday.repository.BranchRepository;
import aday.service.CompanyService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.io.Serializable;

public class AddBranchViewModel implements Serializable {
    private final AddressRepository addressRepository;
    private final BranchRepository branchRepository;
    private final CompanyService companyService;
    private Branch branch;
    private static Company company;
    private ListModel<Address> addressListModel;

    public AddBranchViewModel() {
        this.companyService = new CompanyService();
        this.addressRepository = new AddressRepository();
        this.branchRepository = new BranchRepository();
        this.addressListModel = new ListModelList<>(addressRepository.findAll());
        this.branch = new Branch();
    }

    public Branch getBranch() {
        return branch;
    }

    public Company getCompany() {
        return company;
    }

    public ListModel<Address> getAddressListModel() {
        return addressListModel;
    }

    @Command
    public void addBranch() {
        try {
            if (branch.getAddress() == null) {
                Clients.alert("Адрес не выбран");
                return;
            }
            if (branch.getName() == null || branch.getName().trim().isEmpty()) {
                Clients.alert("Наименование не должно быть пустым");
                return;
            }
            Long id = branchRepository.save(branch);
            branch.setId(id);
            companyService.addBranch(AddBranchViewModel.company, branch);
            Clients.showNotification("Филиал добавлен к компании");
            Constraint
            branch = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Command
    public void removeBranch(@BindingParam("companyId") Long companyId, @BindingParam("branchId") Long branchId) {
        try {
            branchRepository.delete(companyId, branchId);
            Clients.showNotification("Филиал удален из компании");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Command
    @NotifyChange("company")
    public void showModal(Event e, @BindingParam("company") Company company) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window) Executions.createComponents(
                "/create_branch.zul", null, null);
        window.doModal();
        AddBranchViewModel.company = company;
    }
}
