package spawner.nodes;

import main.Summoning;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

import spawner.misc.Variables;

public class SummonNew extends Node {

	@Override
	public boolean activate() {
	//	main.global.Methods.s("sumnew");
		return main.global.Methods.isIn() && !Bank.isOpen()
				&& Inventory.getItem(Variables.pouchId) != null
				&& Summoning.getPoints() > Variables.pointCost;
	}

	@Override
	public void execute() {
		final Item pouch = Inventory.getItem(Variables.pouchId);
		if (pouch != null) {
			main.global.Methods.s("Summoning new " + pouch.getName());
			pouch.getWidgetChild().click(false);
			if (Menu.isOpen() && Menu.contains("Summon")) {
				Menu.select("Summon", pouch.getName());
				Task.sleep(500);
			}
		}
	}
}
