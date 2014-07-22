/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "foliodiploma")
@NamedQueries({
    @NamedQuery(name = "Foliodiploma.findAll", query = "SELECT f FROM Foliodiploma f")})
public class Foliodiploma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foliodiploma")
    private Integer foliodiploma;

    public Foliodiploma() {
    }

    public Foliodiploma(Integer foliodiploma) {
        this.foliodiploma = foliodiploma;
    }

    public Integer getFoliodiploma() {
        return foliodiploma;
    }

    public void setFoliodiploma(Integer foliodiploma) {
        this.foliodiploma = foliodiploma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foliodiploma != null ? foliodiploma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foliodiploma)) {
            return false;
        }
        Foliodiploma other = (Foliodiploma) object;
        if ((this.foliodiploma == null && other.foliodiploma != null) || (this.foliodiploma != null && !this.foliodiploma.equals(other.foliodiploma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Foliodiploma[ foliodiploma=" + foliodiploma + " ]";
    }
    
}
