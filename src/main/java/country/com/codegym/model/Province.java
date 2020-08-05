package country.com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name="province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="area")
    private double area;

    @Column(name="popular")
    private double popular;

    @Column(name="gdb")
    private double gdb;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="country_id")
    private Country country;

    public Province(double area, double popular, double gdb, String description, Country country) {
        this.area = area;
        this.popular = popular;
        this.gdb = gdb;
        this.description = description;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPopular() {
        return popular;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }

    public double getGdb() {
        return gdb;
    }

    public void setGdb(double gdb) {
        this.gdb = gdb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
