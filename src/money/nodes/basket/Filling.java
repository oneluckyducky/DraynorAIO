package money.nodes.basket;

import money.misc.Constant;
import money.misc.Methods;
import money.misc.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;

public class Filling extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && main.global.Methods.isInBank()
				&& Inventory.getCount(Variables.fruitId) > 4
				&& Inventory.getCount(Constant.BASKET) > 0;
	}

	@Override
	public void execute() {
		main.global.Methods.s("Filling baskets");
		Methods.fillBaskets();

	}

}
