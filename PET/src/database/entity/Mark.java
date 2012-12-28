/*
 * Класс описывает сушность в базе данных таблицы Mark
 */
package database.entity;

import java.io.Serializable;

/**
 *
 * @author Aleo
 */
public class Mark implements Serializable {
    private Integer idMark;
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
