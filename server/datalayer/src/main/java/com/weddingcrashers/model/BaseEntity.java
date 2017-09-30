package com.weddingcrashers.model;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity class.
 *
 * @author dmpliamplias
 */
@MappedSuperclass
abstract class BaseEntity implements Serializable {

    // ---- Members

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

}
