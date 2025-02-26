/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hajiaga
 */
public class LuckyField extends Field {
    public Integer prize; 
    
    public LuckyField(FieldType type,Integer prize){
        super(type);
        
        this.prize = prize;
    }
}
