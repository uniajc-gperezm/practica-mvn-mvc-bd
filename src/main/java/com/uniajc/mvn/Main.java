
// src/main/java/com/uniajc/mvn/Main.java
// src/main/java/com/uniajc/mvn/Main.java
package com.uniajc.mvn;
// src/main/java/com/uniajc/mvn/Main.java

 
import com.uniajc.mvn.Vista.MainView; 
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Lanza la vista principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(MainView::new);
    }
}