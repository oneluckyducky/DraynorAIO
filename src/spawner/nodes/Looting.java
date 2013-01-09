package spawner.nodes;

import java.awt.Point;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;

import spawner.misc.Methods;
import spawner.misc.Variables;

public class Looting extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && !Inventory.isFull()
				&& Methods.getSpecialLeft() < 30;
	}

	@Override
	public void execute() {
		final GroundItem p = GroundItems.getNearest(Variables.loot);
		if (p != null) {
			if(Random.nextInt(0, 7) % 3 == 0) {
				main.global.Methods.s("Picking up " + p.getGroundItem().getName());
				if (p.interact("Take", p.getGroundItem().getName())) {
					Task.sleep(100);
					final Timer timer = new Timer(240);
					while (timer.isRunning() && p.validate()) {
						Task.sleep(120);
						if (Players.getLocal().isMoving()) {
							timer.reset();
						}
					}
					Variables.profit += main.global.Methods.getPrice(p.getId());
					Variables.collected++;
				}
			} else {
				main.global.Methods.s("Picking up " + p.getGroundItem().getName());
				Point cp = p.getCentralPoint();
				//Mouse.move(cp);
				Mouse.hop(cp.x, cp.y);
				Mouse.click(cp.x, cp.y, false);
				if(Menu.isOpen() && Menu.contains("Take", p.getGroundItem().getName())) {
					Menu.select("Take", p.getGroundItem().getName());
					Task.sleep(100);
					final Timer timer = new Timer(240);
					while (timer.isRunning() && p.validate()) {
						Task.sleep(120);
						if (Players.getLocal().isMoving()) {
							timer.reset();
						}
					}
					Variables.profit += main.global.Methods.getPrice(p.getId());
					Variables.collected++;
				}
			}
		}
	}
}
