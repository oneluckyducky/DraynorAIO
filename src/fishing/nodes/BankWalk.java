package fishing.nodes;

import main.global.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;

import fishing.misc.Variables;

public class BankWalk extends Node {

	@Override
	public boolean activate() {
		//main.global.Methods.s("BANK WALK ACTIVATE");
		return Methods.isIn()
				&& Inventory.isFull() && !Methods.isInBank() && !Variables.powerFishing;
	}

	@Override
	public void execute() {
		Methods.s("Walking to bank");
		Walking.walk(new Tile(3094, 3243, 0));
	}

}
