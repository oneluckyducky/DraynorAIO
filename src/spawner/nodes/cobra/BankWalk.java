package spawner.nodes.cobra;

import main.Summoning;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;

import spawner.misc.Methods;

public class BankWalk extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && Inventory.isFull() && !Methods.isInBank()
				|| main.global.Methods.isIn() && !Summoning.isFamiliarSummoned()
				&& Summoning.getPoints() > 0 && !Methods.isInBank()
				|| main.global.Methods.isIn() && Methods.getScrollsLeft() == 0
				&& !Methods.isInBank() || main.global.Methods.isIn()
				&& Inventory.getCount() == 0 && !Methods.isInBank();
	}

	@Override
	public void execute() {
		main.global.Methods.s("Walking to bank");
		Walking.walk(new Tile(3094, 3243, 0));

	}

}
