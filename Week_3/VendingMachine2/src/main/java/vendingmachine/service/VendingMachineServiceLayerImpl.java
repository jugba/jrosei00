/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.service;

import com.sun.istack.internal.logging.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Change;
import vendingmachine.dto.Fad;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    BigDecimal insertedAmount;
    BigDecimal Price;
    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao myDao) {
        this.dao = myDao;
    }

    public VendingMachineServiceLayerImpl() {

    }

    public void Write(ArrayList< Fad> Commemorating) throws VendingMachinePersistenceException {
        VendingMachineDao item = new VendingMachineDaoImpl();
        item.write(Commemorating);
    }

    public void sufficientFunds(ArrayList< Fad> UglyItem, int Inventory, BigDecimal insertedAmount) throws InsufficientFundsException {
        Price = UglyItem.get(Inventory).getPrice();

        if (insertedAmount.compareTo(Price) < 0) {
            throw new InsufficientFundsException("Please insert the correct amount.");
        }
    }

    public void updateInventory(ArrayList< Fad> UglyItem, int Inventory) throws VendingMachinePersistenceException {
        int inventory = UglyItem.get(Inventory).getInventory();
        inventory -= 1;
        UglyItem.get(Inventory).setInventory(inventory);
        dao.write(UglyItem);
    }

    public void ItemInventory(ArrayList< Fad> UglyItem, int Inventory) throws NoItemInventoryException {
        if (UglyItem.get(Inventory).getInventory() <= 0) {
            throw new NoItemInventoryException("That item seems to be out of stock. Good news, though. It's ugly anyways.");
        }
    }

    @Override
    public ArrayList<Fad> Read() {
        try {
            VendingMachineDao item = new VendingMachineDaoImpl();
            ArrayList< Fad> getUglyItem = new ArrayList<>();
            getUglyItem = item.Read();
            return getUglyItem;
        } catch (VendingMachinePersistenceException e) {
            //Logger.getLogger(VendingMachineServiceLayerImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public BigDecimal Change(ArrayList<Fad> UglyItem, int candyInList, BigDecimal Payment) {
        insertedAmount = insertedAmount.subtract(Price);
        return insertedAmount;
    }

    @Override
    public Change changeToCoins(BigDecimal Change) {
        Change coins = new Change();

        int Quarter = 0;
        int Dime = 0;
        int Nickel = 0;
        int Penny = 0;

        BigDecimal quarter = new BigDecimal(".25");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal penny = new BigDecimal(".01");

        while (Change.compareTo(quarter) >= 0) {
            Quarter++;
            Change = Change.subtract(quarter);
        }
        while (Change.compareTo(dime) >= 0) {
            Dime++;
            Change = Change.subtract(dime);
        }
        while (Change.compareTo(nickel) >= 0) {
            Nickel++;
            Change = Change.subtract(nickel);
        }
        while (Change.compareTo(penny) >= 0) {
            Penny++;
            Change = Change.subtract(penny);
        }

        coins.setQuarter(Quarter);
        coins.setDime(Dime);
        coins.setNickel(Nickel);
        coins.setPenny(Penny);
        return coins;
    }
}
