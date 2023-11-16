
package com.mycompany.modelo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbsparta121
 */
public class EmpleadoDAO {
    private Session sesion;
    private Transaction trns;
    
    private void iniciaOperacion(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        trns = sesion.beginTransaction();
    }
    
    
    private void manejaException(HibernateException e){
       trns.rollback();
       throw new HibernateException("Error en la capa de datos." + e);
    }
    
    
    public boolean grabar(Empleado empleado){
        boolean ok = false;
        
        try{
            iniciaOperacion();
            sesion.save(empleado);
            trns.commit();
            ok = true;          
        }
        catch(HibernateException e){
            manejaException(e);
            ok =  false;
        }
    return ok;
    }
    
    public Empleado consultar(int id){
        Empleado empleado = null;
        try{
            iniciaOperacion();
            empleado = (Empleado) sesion.get(Empleado.class, id);
        }
        catch(HibernateException e){
            manejaException(e); 
        }
        finally{
            sesion.close();
        }
        return empleado;
    }
    
    public boolean eliminar(Empleado empleado){
        boolean ok = false;
        
        try{
            iniciaOperacion();
            sesion.delete(empleado);
            trns.commit();
            ok = true;          
        }
        catch(HibernateException e){
            manejaException(e);
            ok = false;
        }
    return ok;
    }  


    public boolean actualizar(Empleado empleado){
        boolean ok = false;
        
        try{
            iniciaOperacion();
            sesion.update(empleado);
            trns.commit();
            ok = true;          
        }
        catch(HibernateException e){
            manejaException(e);
            ok =  false;
        }
    return ok;
    }
}
