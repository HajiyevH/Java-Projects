/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author hajiaga
 */
public class GreedyPlayer extends Player {
    public GreedyPlayer(String name){
        super(name);
    }
    
    @Override
    public void stepProperty(Property field){
        if(properties.contains(field)){
            if(budget > 4000 && field.house == false){
                field.buyHouse();
                System.out.println(this.name + "bought house");
            }
        }
        else{
            if(field.isEmpty()){
                if(budget > field.getPrice()){
                    buy(field);
                    System.out.println(this.name + "bought property");

                }
            }else{
                fine(field);
                System.out.println(this.name + "paid fine" + field.house);

            }
        }

    }
    
}
