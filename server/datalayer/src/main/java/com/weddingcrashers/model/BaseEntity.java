package com.weddingcrashers.model;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity class.
 *
 * @author dmpliamplias
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    // ---- Members

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    /** Creation date. */
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;


    // ---- Constructor
    /**
     * Constructor.
     */
    protected BaseEntity() {
        // nop
    }


    // ---- Methods

    @PrePersist
    public void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
