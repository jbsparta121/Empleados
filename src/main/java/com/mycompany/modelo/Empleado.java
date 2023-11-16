
package com.mycompany.modelo;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author jbsparta121
 */
@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;
    
    @OneToMany(mappedBy="correo", cascade=CascadeType.ALL)
    private Set<CorreoElectronico> correosElectronicos;
    
    public Empleado() {
    }
    
    public Empleado(int id) {
        this.id = id;
    }
    
    public Empleado(int id, String nombre, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<CorreoElectronico> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(Set<CorreoElectronico> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
    }
}
