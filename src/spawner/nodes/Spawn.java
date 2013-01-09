package spawner.nodes;

import main.Summoning;
import main.Summoning.Option;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import spawner.misc.Const;
import spawner.misc.Methods;
import spawner.misc.Variables;

public class Spawn extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && Summoning.isFamiliarSummoned()
				&& !Methods.isInBank() && Methods.getSpecialLeft() > 18;
	}

	@Override
	public void execute() {
		final WidgetChild special = Widgets.get(662).getChild(40);
		
		if(Summoning.getLeftClickOption() != Summoning.Option.FOLLOWER_DETAILS) {
			Summoning.setLeftClickOption(Option.FOLLOWER_DETAILS);
		}

		if (!Const.CAST_AREA.contains(Players.getLocal().getLocation())) {
			main.global.Methods.s("Walking to spawn location");
			Walking.walk(new Tile(3092, 3247, 0));
			final Timer t = new Timer(1000);
			while (t.isRunning()) {
				if(Players.getLocal().isMoving()) {
					t.reset();
				}
				if (Const.CAST_AREA.contains(Players.getLocal().getLocation())) {
					break;
				}
			}
		}
		if (Methods.getScrollsLeft() > 0 && Methods.openSummoning()) {
			Walking.walk(new Tile(3092, 3247, 0));
			main.global.Methods.s(Variables.mode == 0 ? "Casting Fruit fall" : "Casting Egg Spawn");
			do {
				if (!special.isOnScreen()) {
					Methods.openSummoning();
				}
				if (Methods.getScrollsLeft() == 0) {
					break;
				}
				if (Methods.isInBank()) {
					break;
				}
				if (Summoning.cast()) {
					while (Players.getLocal().getAnimation() == 7660) {
						Task.sleep(50);
					}
				}
			} while (Methods.getSpecialLeft() > 5);
		}
	}
}
