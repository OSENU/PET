/*
* Класс описывает сушность в базе данных таблицы Subject
 */
package database.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aleo
 */
@Entity
@Table(name = "SUBJECT", catalog = "TEST", schema = "PUBLIC", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_SUBJECT"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findByIdSubject", query = "SELECT s FROM Subject s WHERE s.idSubject = :idSubject"),
    @NamedQuery(name = "Subject.findByNameSubject", query = "SELECT s FROM Subject s WHERE s.nameSubject = :nameSubject")})
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SUBJECT", nullable = false)
    private Integer idSubject;
    @Basic(optional = false)
    @Column(name = "NAME_SUBJECT", nullable = false, length = 50)
    private String nameSubject;

    public Subject() {
    }

    public Subject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Subject(Integer idSubject, String nameSubject) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubject != null ? idSubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.idSubject == null && other.idSubject != null) || (this.idSubject != null && !this.idSubject.equals(other.idSubject))) {
            return false;
        }
        if ((this.nameSubject == null && other.nameSubject != null) || (this.nameSubject != null && !this.nameSubject.equals(other.nameSubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Subject[ idSubject=" + idSubject + ", nameSubject=" + nameSubject + " ]";
    }
    
}
