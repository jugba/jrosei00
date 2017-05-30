/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import vendingmachine.dto.Fad;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    public static final String Fads_90s = "Fads_90s.txt";
    public static final String DELIMITER = " :: ";

    public ArrayList< Fad> Read() throws VendingMachinePersistenceException {

        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(Fads_90s)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load vending machine menu.", e);
        }

        String currentLine;
        String[] currentTokens;
        ArrayList< Fad> Items = new ArrayList();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Fad currentFad = new Fad(currentTokens[0]);
            currentFad.setPrice(new BigDecimal(currentTokens[1]));
            currentFad.setInventory(Integer.parseInt(currentTokens[2]));
            Items.add(currentFad);
        }
        return Items;
    }

    public void Write(ArrayList < Fad > Commemorating) throws VendingMachinePersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter (new FileWriter(Fads_90s));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException ("Could not save 90s ugly fads", e);
        }
        
        for (Fad currentFad : Commemorating) {
            out.println(currentFad.getItem() + DELIMITER
            + currentFad.getPrice() + DELIMITER
            + currentFad.getInventory());
            out.flush();
        }
        out.close();

    }

    @Override
    public void write(ArrayList<Fad> Commemorating) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}