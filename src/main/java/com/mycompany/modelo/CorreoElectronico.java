     
package com.mycompany.modelo;

import javax.persistence.*;

/**
 *
 * @author jbsparta121
 */
@Entity
@Table(name="correo_electronico")
public class CorreoElectronico {
    @Id
    @Column(name="correo")
    private String correo;
    
    @ManyToOne
    @JoinColumn(name="empleado")
    private Empleado empleado;
    
    public CorreoElectronico() {
    }
    
    public CorreoElectronico(String correo, Empleado empleado) {
        this.correo = correo;
        this.empleado = empleado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
