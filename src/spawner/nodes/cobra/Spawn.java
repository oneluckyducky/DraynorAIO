package spawner.nodes.cobra;

import java.awt.Point;

import main.Summoning;
import main.Summoning.Option;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import spawner.misc.Enums.Eggs;
import spawner.misc.Methods;
import spawner.misc.Variables;

public class Spawn extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && Summoning.isFamiliarSummoned()
				&& Methods.isInBank() && Methods.getSpecialLeft() > 2
				&& Inventory.getCount(Eggs.CHICKEN_EGG.getId()) > 0;
	}

	@Override
	public void execute() {
		@SuppressWarnings("unused")
		final WidgetChild special = Widgets.get(662).getChild(40);

		Summoning.setLeftClickOption(Option.CAST);
		
		if(Bank.isOpen()) {
			Bank.close();
		}

		if (!Methods.isInBank()) {
			main.global.Methods.s("Walking into bank");
			Walking.walk(new Tile(3092, 3244, 0));
			final Timer t = new Timer(500);
			while (t.isRunning() && !Methods.isInBank()) {
				Task.sleep(120);
				if (Players.getLocal().isMoving()) {
					t.reset();
				}
			}
		}
		if (Methods.getScrollsLeft() > 0 && Methods.openSummoning()) {
			main.global.Methods.s("Casting Ophidian Incubation");
			do {
				if (Methods.getScrollsLeft() == 0
						|| Inventory.getItem(Variables.scrollId) == null
						|| !Methods.isInBank()
						|| Inventory.getItem(Eggs.CHICKEN_EGG.getId()) == null) {
					break;
				}
				if (Summoning.select(Option.CAST)) {
					Item egg = Inventory.getItem(Eggs.CHICKEN_EGG.getId());
					Point ep = egg != null ? egg.getWidgetChild()
							.getAbsoluteLocation() : null;

					Mouse.hop(ep.x, ep.y);
					Task.sleep(80);
					Mouse.click(true);
					while (Players.getLocal().getAnimation() == 7660) {
						Task.sleep(150);
					}
				}
			} while (Methods.getSpecialLeft() > 2
					| Inventory.getCount(Eggs.CHICKEN_EGG.getId()) == 0);
		}
	}
}
