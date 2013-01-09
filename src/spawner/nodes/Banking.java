package spawner.nodes;

import main.Summoning;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;

import spawner.misc.Methods;
import spawner.misc.Variables;

public class Banking extends Node {
	
	
	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && Inventory.isFull() && Methods.isInBank()
				|| main.global.Methods.isIn() && Methods.getScrollsLeft() == 0
				&& Methods.isInBank() || main.global.Methods.isIn()
				&& !Summoning.isFamiliarSummoned()
				&& Inventory.getCount(Variables.pouchId) == 0
				&& Methods.isInBank();
	}

	@Override
	public void execute() {
		main.global.Methods.s("Opening bank");
		if (Methods.openBank()) {
			while (!Bank.isOpen()) {
				Task.sleep(150);
			}
		}
		if (Bank.isOpen()) {
			main.global.Methods.s("Depositing Familiar Inventory");
			if(Summoning.isFamiliarSummoned()) {
				Bank.depositFamiliarInventory();
				Task.sleep(800);
			}
			if(Inventory.getCount() > 0) {
				Bank.depositInventory();
				while(Inventory.getCount() > 0 ) {
					Task.sleep(50);
				}
			}
			if (Methods.getScrollsLeft() == 0
					&& Bank.getItemCount(Variables.scrollId) == 0
					|| Methods.getScrollsLeft() == 9999
					&& Bank.getItemCount(Variables.scrollId) == 0) {
				Methods.stop();
			}
			if (!Summoning.isFamiliarSummoned()
					&& Inventory.getCount(Variables.scrollId) == 0) {
				main.global.Methods.s("Withdrawing new pouch");
				Bank.withdraw(Variables.pouchId, 1);
				while(Inventory.getCount(Variables.pouchId) < 1 ) {
					Task.sleep(50);
				}
			}
			if (Methods.getScrollsLeft() == 0
					&& Bank.getItem(Variables.scrollId).getStackSize() >= 1) {
				main.global.Methods.s("Withdrawing more scrolls");
				Bank.withdraw(Variables.scrollId, 0);
				if(!(Inventory.getCount(Variables.scrollId) > 0)) {
					Bank.withdraw(Variables.scrollId, 0);
				}
				while(Inventory.getCount(Variables.scrollId) < 0 ) {
					Task.sleep(50);
				}
			}
			if(!(Inventory.getCount(Variables.scrollId) > 0)) {
				Bank.withdraw(Variables.scrollId, 0);
				while(Inventory.getCount(Variables.scrollId) < 0 ) {
					Task.sleep(50);
				}
			}
			main.global.Methods.s("Leaving bank");
			new Tile(3091, 3248, 0).randomize(1, 2).clickOnMap();
			Task.sleep(1500);
		}
	}
}
