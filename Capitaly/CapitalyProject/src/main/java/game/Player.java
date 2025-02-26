/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
/**
 *
 * @author hajiaga
 */
public abstract class Player {
    public String name;
    
    protected Integer budget;
    
    protected Integer position;
        
    protected ArrayList<Property> properties;
    
    protected Boolean isBankrupt;
    
    public Player(String name){
        this.name = name;
        
        this.budget = 10000;
        
        this.position = 0;
        
        this.properties = new ArrayList<>();
        
        this.isBankrupt = false;
    }
    public Integer getBudget(){return budget;}
    
    public void round(Field field) {
        if (field.getType() == FieldType.Property) {
            stepProperty((Property) field); 
        } else if (field.getType() == FieldType.Service) {
            stepService((ServiceField) field); 
        } else if (field.getType() == FieldType.Lucky) {
            stepLucky((LuckyField) field); 
        }
    }
    
    public void stepProperty(Property field){
        
    }
    
    public void stepService(ServiceField field){
        loseMoney(field.fine);
    }
    public void stepLucky(LuckyField field){
        budget = budget + field.prize;
    }
    
    public void addMoney(Integer money){
        budget = budget + money;
    }
        
    public void loseMoney(Integer money){
       if(budget < money){isBankrupt = true;}
        else{
            budget = budget - money;
        }    
    }
    
    public void buy(Property field){
        
        budget = budget - field.getPrice();
            
        properties.add(field);
            
        field.setOwner(this);
    }
    public void fine(Property field){
        loseMoney(field.rent);
    }
    public void loseGame() {
        for (Property property : properties) {
            property.lose(); 
        }
        
        System.out.println(this.name + " lost the game");

        properties.clear();
    }
}
