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

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "DELETED")
    private boolean deleted = false;


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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }
}
