package pl.time4it.demo_store_2.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    private Category category;

    @Column(name = "price")
    private BigDecimal price = new BigDecimal(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_producer")
    private Producer producer;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "promotion")
    private boolean promotion;

    @Column(name = "serial_no")
    private  String serialNo;

    @Column(name = "picture")
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="product", cascade = CascadeType.ALL)
    private List<Opinion> opinions  = new ArrayList<>();


    }



