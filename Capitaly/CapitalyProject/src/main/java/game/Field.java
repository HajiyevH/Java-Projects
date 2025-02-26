/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
/**
 *
 * @author hajiaga
 */
public abstract class Field {
    
    FieldType type;
    
    public Field(FieldType type){
        this.type = type;

    }
    
    public FieldType getType(){return type;}
    
    public Integer getPrice(){return null;}
    
    public Boolean isEmpty(){return null;}
}
