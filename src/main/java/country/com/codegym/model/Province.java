package country.com.codegym.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "không để trống")
    @Column(name="name")
    private String name;

    @NotNull
    @Min(value=1,message = "giá trị dương")
    @Column(name="area")
    private double area;

    @NotNull
    @Min(value=1,message = "giá trị dương")
    @Column(name="popular")
    private double popular;

    @NotNull
    @Min(value=1,message = "giá trị dương")
    @Column(name="gdb")
    private double gdb;

    @NotBlank(message = "không để trống")
    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="country_id")
    private Country country;

    public Province() {
    }

    public Province(double area, double popular, double gdb, String description, Country country) {
        this.area = area;
        this.popular = popular;
        this.gdb = gdb;
        this.description = description;
        this.country = country;
    }

    public Province(String name, double area, double popular, double gdb, String description, Country country) {
        this.name = name;
        this.area = area;
        this.popular = popular;
        this.gdb = gdb;
        this.description = description;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
