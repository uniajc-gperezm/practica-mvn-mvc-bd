
package com.uniajc.mvn;


 
import com.uniajc.mvn.Vista.MainView; 
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(MainView::new);
    }
}