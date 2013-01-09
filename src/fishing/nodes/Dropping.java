package fishing.nodes;

import main.global.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

import fishing.misc.Const;
import fishing.misc.Variables;

public class Dropping extends Node {

	@Override
	public boolean activate() {
		//main.global.Methods.s("DROP ACTIVATE");
		return Methods.isIn() && Variables.powerFishing && Inventory.isFull();
	}

	@Override
	public void execute() {
		if (Inventory.isFull()) {
			if (!Variables.mouseKey) {
				main.global.Methods.s("Dropping fish");
				for (int id : Const.FISH_IDS) {
					for (Item it : Inventory.getItems()) {
						if (it.getId() == id) {
							it.getWidgetChild().interact("Drop");
						}
					}
				}
			} else {
				main.global.Methods.s("Dropping fish with mousekeys");
				while(Variables.mode == 0 ? Inventory.getCount() > 0 : Inventory.getCount() > 1) {
					fishing.misc.Methods.mousekeyDrop();
				}
			}
		}
	}
}
