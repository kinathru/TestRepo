/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fb;

/**
 *
 * @author imalhasaranga
 */
public class a {
    public static void main(String[] args) { 
        for (int i = 0; i < 20; i++) {
           positon(100, i);
            
        }
        System.out.println("");
    }
    
    public static void positon(int x,int y){
        
        if(x>y){
            System.out.println(y);
        }else{
            System.out.println(y%x);
        }
    }
}
