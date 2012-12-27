/*
 * Класс описывает сушность в базе данных таблицы Mark
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aleo
 */
@Entity
@Table(name = "MARK", catalog = "TEST", schema = "PUBLIC", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_MARK"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mark.findAll", query = "SELECT m FROM Mark m"),
    @NamedQuery(name = "Mark.findByIdMark", query = "SELECT m FROM Mark m WHERE m.idMark = :idMark"),
    @NamedQuery(name = "Mark.findByNameMark", query = "SELECT m FROM Mark m WHERE m.nameMark = :nameMark")})
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MARK", nullable = false)
    private Integer idMark;
    @Basic(optional = false)
    @Column(name = "NAME_MARK", nullable = false, length = 50)
    private String nameMark;

    public Mark() {
    }

    public Mark(Integer idMark) {
        this.idMark = idMark;
    }

    public Mark(Integer idMark, String nameMark) {
        this.idMark = idMark;
        this.nameMark = nameMark;
    }

    public Integer getIdMark() {
        return idMark;
    }

    public void setIdMark(Integer idMark) {
        this.idMark = idMark;
    }

    public String getNameMark() {
        return nameMark;
    }

    public void setNameMark(String nameMark) {
        this.nameMark = nameMark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMark != null ? idMark.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mark)) {
            return false;
        }
        Mark other = (Mark) object;
        if ((this.idMark == null && other.idMark != null) || (this.idMark != null && !this.idMark.equals(other.idMark))) {
            return false;
        }
        if ((this.nameMark == null && other.nameMark != null) || (this.nameMark != null && !this.nameMark.equals(other.nameMark))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Mark[ idMark=" + idMark + ", nameMark="+ nameMark +" ]";
    }
    
}
