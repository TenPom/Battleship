// Battleship.java
package de.htwg.battleship;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.battleship.aview.gui.GUI;
import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
/**
 * Battleship start file.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public final class Battleship {

    /**
     * Private Constructor.
     */
    private Battleship() { 
    	PropertyConfigurator.configure("log4j.properties");
        Injector injector = Guice.createInjector(new BattleshipModule());
        master = injector.getInstance(IMasterController.class);
        master.setInjector(injector);
//        tui = new TUI(master);
//        new GUI(master);
        Battleship.instance = this;
    }
    /**
     * MasterController of the entire game.
     */
    private static IMasterController master;
    /**
     * Saves TUI.
     */
    private static TUI tui;
    /**
     * Scanner to read from stdin.
     */
    private static Scanner scanner;
    
    private static Battleship instance;

    /**
     * Main-Method.
     * @param args not used
     */
    public static void main(final String[] args) {
//    	PropertyConfigurator.configure(Battleship.class.getClassLoader().getResource("log4j.properties"));
        PropertyConfigurator.configure("log4j.properties");
        Injector injector = Guice.createInjector(new BattleshipModule());
        master = injector.getInstance(IMasterController.class);
        master.setInjector(injector);
//        tui = new TUI(master);
//        new GUI(master);
        scanner = new Scanner(System.in);
        while (true) {
            tui.processInputLine(scanner.nextLine());
        }
    }
    
    public static Battleship getInstance () {
    	if(Battleship.instance == null) {
    		Battleship.instance = new Battleship();
    	}
    	return Battleship.instance;
    }
    
    public TUI getTui() {
    	return tui;
    }
    
    public static IMasterController getController() {
    	return master;
    }
    
    public IMasterController createController() {
    	Injector injector = Guice.createInjector(new BattleshipModule());
        master = injector.getInstance(IMasterController.class);
    	return master;
    }
}
