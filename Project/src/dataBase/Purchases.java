package dataBase;

import java.util.ArrayList;
import java.util.List;

import metier.Comment;
import metier.Purchase;

public class Purchases {

	// Fields
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	// Saver
	
	// Getter
	public Purchase getPurchase(int code) {
		for (int i = 0; i < this.purchases.size(); i++) {
			if (this.purchases.get(i).getCode() == code) {
				return this.purchases.get(i);
			}
		}
		return null;
	}
}
