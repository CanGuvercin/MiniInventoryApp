package com.guvercin.MiniInventoryApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AltClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank(message = "Item name cannot be blank")
    private String name;

    @Column(length = 500)
    private String description;

    @Min(0)
    private int quantity;

    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(nullable = false)
    private boolean active;

    @Embedded
    private Dimensions dimensions;

    @ElementCollection
    private List<String> tags;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String barcode;

    private String sku;

    private String manufacturer;

    private LocalDateTime expirationDate;

    private String location;

    @Column(length = 1000)
    private String internalNotes;

    @Version
    private int version;

    public enum ItemStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        DISCONTINUED,
        PENDING_APPROVAL
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimensions {
        private double width;
        private double height;
        private double depth;
        private double weight;
    }
}
