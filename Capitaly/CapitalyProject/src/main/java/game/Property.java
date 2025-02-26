/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hajiaga
 */
public class Property extends Field{
    
    public Integer rent;
    
    protected Integer price;
    
    protected Boolean house;
    
    protected Player owner;
    
    public Property(FieldType type){
        super(type);
        
        price = 1000;
        
        house = false;
        
        owner = null;
        
        rent = 500;
    }
    
    @Override
    public Integer getPrice(){
        return price;
    }
    
    public void setOwner(Player player){this.owner = player;}
    
    public void buyHouse(){
        house = true;
        
        rent = 2000;
        
        owner.loseMoney(4000);
    }
    public Integer fineRent(){
        owner.addMoney(rent);
        return rent;
    }
    
    @Override
    public Boolean isEmpty() {
        if(owner == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void lose(){
        rent = 500;
        owner = null;
        house = false;
    }
    
}
