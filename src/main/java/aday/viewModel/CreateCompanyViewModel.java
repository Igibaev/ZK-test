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
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateCompanyViewModel implements Serializable {
    private final CompanyService companyService;
    private final BranchRepository branchRepository;
    private final AddressRepository addressRepository;
    private Company company;
    private ListModel<Address> addressListModel;
    private ListModel<String> legalFormListModel;
    private ListModel<Branch> branchListModel;

    public CreateCompanyViewModel() {
        this.branchRepository = new BranchRepository();
        this.companyService = new CompanyService();
        this.company = new Company();
        this.addressRepository = new AddressRepository();
        this.addressListModel = new ListModelList<>(addressRepository.findAll());
        this.branchListModel = new ListModelList<>(branchRepository.findAll());
        this.legalFormListModel = new ListModelList<>(Arrays.stream(LegalForm.values())
                .map(LegalForm::name)
                .collect(Collectors.toList()));
    }

    public ListModel<String> getLegalFormListModel() {
        return legalFormListModel;
    }

    public ListModel<Address> getAddressListModel() {
        return addressListModel;
    }

    public ListModel<Branch> getBranchListModel() {
        return branchListModel;
    }

    public Company getCompany() {
        return company;
    }

    @Command //@Command annotates a command method
    @NotifyChange("company") //@NotifyChange annotates data changed notification after calling this method
    public void save(){
        if (company.getAddress() == null) {
            Clients.alert("Адрес не выбран");
            return;
        }
        if (company.getName() == null || company.getName().trim().isEmpty()) {
            Clients.alert("Наименование не должно быть пустым");
            return;
        }

        companyService.create(company);
        Clients.showNotification("Компания добавлена в БД");
        company = null;
    }

    @Command
    @NotifyChange("company")
    public void reset(){
        company = null;
    }

    @Command
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window) Executions.createComponents(
                "/create_company.zul", null, null);
        window.doModal();
    }
}