/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Product;

/**
 *
 * @author apprentice
 */
public interface productDao {
    
    Product getProductInfo(String productType);
    
    void readFromProductFile() throws flooringMasteryPersistenceException;  	    
}
