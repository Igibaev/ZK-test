package aday.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "company")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String legalForm;
    @ManyToOne(optional = false)
    private Address address;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Branch> branchList = new HashSet<>();

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", legalForm='" + legalForm + '\'' +
                ", address=" + address +
                ", branchList=" + branchList +
                '}';
    }
}
