/*
* Класс описывает сушность в базе данных таблицы Type_Work
 */
package database.entity;

import java.io.Serializable;

/**
 *
 * @author Aleo
 */

public class TypeWork implements Serializable {
    private Integer idTypeWork;
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
