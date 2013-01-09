package spawner.nodes.cobra;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Tile;

import spawner.misc.Const;

public class Failsafe extends Node{

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && !Const.TOTAL_AREA.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		main.global.Methods.s("We are way out of place, walking back to draynor");
		Walking.walk(new Tile(3092, 3244, 0));
	}

}
