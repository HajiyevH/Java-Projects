/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hajiaga
 */
public class ServiceField extends Field {
    
    public static Integer fine;
    
    public ServiceField(FieldType type,Integer fine){
        super(type);
        
        this.fine = fine;
    }
    
}
