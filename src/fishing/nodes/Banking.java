package fishing.nodes;

import main.global.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;

import fishing.misc.Const;
import fishing.misc.Variables;

public class Banking extends Node {

	@Override
	public boolean activate() {
		//main.global.Methods.s("BANK ACTIVATE");
		return Methods.isIn() && Methods.isInBank() && Inventory.isFull()
				&& !Variables.powerFishing || Methods.isIn()
				&& Methods.isInBank() && Variables.mode == 1
				&& !fishing.misc.Methods.inventoryReady();
	}

	@Override
	public void execute() {
		main.global.Methods.s("Opening bank");
		if (Bank.open()) {
			while (!Bank.isOpen()) {
				Task.sleep(200);
			}
		}
		if (Bank.isOpen()) {
			if (Inventory.getCount() == 28) {
				main.global.Methods.s("Depositing Inventory");
				Bank.depositInventory();
				Task.sleep(600);
			}
			if(Variables.mode == 1) {
				if (fishing.misc.Methods.bankContains(Const.FISHING_BAIT)) {
					main.global.Methods.s("Withdrawing fishing bait");
					Bank.withdraw(Const.FISHING_BAIT, 0);
					Task.sleep(500);
				} else {
					if(Inventory.getItem(Const.FISHING_BAIT) == null) {
						main.global.Methods.s("Out of bait, stopping script");
						fishing.misc.Methods.stopBait();
					}
				}
			}
			if(Variables.mode == 1 && Inventory.getCount(Const.FISHING_BAIT) > 0) {
				main.global.Methods.s("Leaving bank");
				new Tile(3086, 3232, 0).clickOnMap();
				Task.sleep(1000);
			}
		}
	}
}
