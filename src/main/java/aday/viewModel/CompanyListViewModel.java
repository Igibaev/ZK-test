package aday.viewModel;

import aday.model.Company;
import aday.service.CompanyService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import java.io.Serializable;

public class CompanyListViewModel implements Serializable {
    private CompanyService companyService;
    private ListModel<Company> companyListModel;

    public CompanyListViewModel() {
        this.companyService = new CompanyService();
        this.companyListModel = new ListModelList<>(companyService.findAll());
    }

    public ListModel<Company> getCompanyListModel() {
        return companyListModel;
    }

}
