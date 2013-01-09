package fishing.nodes;

import main.global.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;

import fishing.misc.Const;
import fishing.misc.Variables;

public class FishWalk extends Node {

	@Override
	public boolean activate() {
		//main.global.Methods.s("FISH WALK ACTIVATE");

		return Methods.isIn() && !Inventory.isFull() && Variables.mode == 1
				&& !fishing.misc.Methods.isInFishingArea()
				&& Inventory.containsAll(Const.FISHING_BAIT)

				|| Methods.isIn() && !Inventory.isFull() && Variables.mode == 0
				&& !fishing.misc.Methods.isInFishingArea();

	}

	@Override
	public void execute() {
		main.global.Methods.s("Walking to fishing spot");
		Walking.walk(new Tile(3086, 3232, 0));

	}

}
