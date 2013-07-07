
package fb;

import javax.swing.JLabel;

/**
 *
 * @author imal
 */
public class Settings {
    
    public static String sourcepath;
    public static String sourcefilename;
    public static String destinationpath;
    public static String destinationfilename;
    public static boolean genderbased = false;
    public static String Gender;
    public static JLabel lable;
    public static JLabel totalnuber;
    public static JLabel lastfetchedname;
    public static boolean isProxyInuse = false;
    
    public static boolean canstart(){
    
        boolean b = (sourcepath != null) && (sourcepath != null) && (destinationfilename != null) &&(destinationpath !=null);
        return b;
    }
    
}
