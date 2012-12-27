/*
* Класс описывает сушность в базе данных таблицы Type_Work
 */
package database.entity;

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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Aleo
 */
@Entity
@Table(name = "TYPE_WORK", catalog = "TEST", schema = "PUBLIC", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_TYPE_WORK"})})
@NamedQueries({
    @NamedQuery(name = "TypeWork.findAll", query = "SELECT t FROM TypeWork t")})

public class TypeWork implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TYPE_WORK", nullable = false)
    private Integer idTypeWork;
    @Basic(optional = false)
    @Column(name = "NAME_TYPE_WORK", nullable = false, length = 50)
    private String nameTypeWork;

    public TypeWork() {
    }

    public TypeWork(Integer idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public TypeWork(Integer idTypeWork, String nameTypeWork) {
        this.idTypeWork = idTypeWork;
        this.nameTypeWork = nameTypeWork;
    }

    public Integer getIdTypeWork() {
        return idTypeWork;
    }

    public void setIdTypeWork(Integer idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public String getNameTypeWork() {
        return nameTypeWork;
    }

    public void setNameTypeWork(String nameTypeWork) {
        this.nameTypeWork = nameTypeWork;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeWork != null ? idTypeWork.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeWork)) {
            return false;
        }
        TypeWork other = (TypeWork) object;
        if ((this.idTypeWork == null && other.idTypeWork != null) || (this.idTypeWork != null && !this.idTypeWork.equals(other.idTypeWork))) {
            return false;
        }
        if ((this.nameTypeWork == null && other.nameTypeWork != null) || (this.nameTypeWork != null && !this.nameTypeWork.equals(other.nameTypeWork))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.TypeWork[ idTypeWork=" + idTypeWork + ", nameTypeWork=" + nameTypeWork +" ]";
    }
    
}
