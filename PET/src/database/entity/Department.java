/*
* Класс описывает сушность в базе данных таблицы Department
 */
package database.entity;

import java.io.Serializable;

/**
 *
 * @author Aleo
 */
public class Department implements Serializable {
   private Integer idDepartment;
   private String nameDepartment;

    public Department() {
    }

    public Department(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Department(Integer idDepartment, String nameDepartment) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartment != null ? idDepartment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.idDepartment == null && other.idDepartment != null) || (this.idDepartment != null && !this.idDepartment.equals(other.idDepartment))) {
            return false;
        }
        if ((this.nameDepartment == null && other.nameDepartment != null) || (this.nameDepartment != null && !this.nameDepartment.equals(other.nameDepartment))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        //return "database.entity.Department[ idDepartment=" + idDepartment + ", nameDepartment="+nameDepartment+" ]";
        return nameDepartment;
    }
    
}
