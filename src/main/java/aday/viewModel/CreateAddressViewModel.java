package aday.viewModel;

import aday.model.Address;
import aday.repository.AddressRepository;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Window;

import java.io.Serializable;

public class CreateAddressViewModel implements Serializable {
    private final AddressRepository addressRepository;
    private Address address;

    public CreateAddressViewModel() {
        this.addressRepository = new AddressRepository();
        this.address = new Address();
    }

    public Address getAddress() {
        return address;
    }

    @Command //@Command annotates a command method
    @NotifyChange("address") //@NotifyChange annotates data changed notification after calling this method
    public void save(){
        if (address.getCity() == null || address.getCity().trim().isEmpty()) {
            Clients.alert("Город не должно быть пустым");
            return;
        }
        if (address.getHouse() == null || address.getHouse().trim().isEmpty()) {
            Clients.alert("Дом не должно быть пустым");
            return;
        }
        if (address.getStreet() == null || address.getStreet().trim().isEmpty()) {
            Clients.alert("Улица не должно быть пустым");
            return;
        }
        addressRepository.save(address);
        Clients.showNotification("Адрес добавлен в БД");
        address = null;
    }

    @Command
    @NotifyChange("address")
    public void reset(){
        address = null;
    }

    @Command
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window) Executions.createComponents(
                "/create_address.zul", null, null);
        window.doModal();
    }
}
