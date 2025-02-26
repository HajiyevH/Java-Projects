/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hajiaga
 */
public class TacticalPlayer extends Player {
    private Integer countchance;
    
    public TacticalPlayer(String name){
        super(name);
        
        countchance = 1;
    }
    @Override
    public void stepProperty(Property field){
        if(properties.contains(field)){
            if(field.house == false){
                if(budget > 4000 && countchance % 2 == 1){
                    field.buyHouse();
                    System.out.println(this.name + "bought house");

                }
                countchance += 1;
            }
        }
        else{
            if(field.isEmpty()){
                if(budget > field.getPrice() && countchance % 2 == 1){
                    buy(field);
                    System.out.println(this.name + "bought prop");
                }
                countchance += 1;
            }else{
                fine(field);
                System.out.println(this.name + "paid fine " + field.house);
            }
        }
    }
}
