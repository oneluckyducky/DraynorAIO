package fishing.nodes;

import main.global.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

import fishing.misc.Const;
import fishing.misc.Variables;

public class Fishing extends Node {

	@Override
	public boolean activate() {
		//main.global.Methods.s("FISHING ACTIVATE");
		return Methods.isIn()
				&& Const.FISHING_AREA
						.contains(Players.getLocal().getLocation())
				&& !Inventory.isFull()
				&& Variables.mode == 1
				&& Inventory.containsAll(Const.FISHING_BAIT)
				&& Players.getLocal().getAnimation() == -1

				|| Methods.isIn()
				&& Const.FISHING_AREA
						.contains(Players.getLocal().getLocation())
				&& !Inventory.isFull() && Variables.mode == 0
				&& Players.getLocal().getAnimation() == -1

		;
	}

	@Override
	public void execute() {
		final NPC spot = NPCs.getNearest(327);
		if (spot != null) {
			if (spot.isOnScreen()) {
				main.global.Methods.s("Fishing");
				if (Random.nextInt(0, 7) % 2 == 0) {
					fishing.misc.Methods.interact(spot,
							Variables.mode == 0 ? "Net" : "Bait",
							"Fishing spot");
					Task.sleep(1500);
					final Timer timer = new Timer(800);
					while (timer.isRunning()) {
						Task.sleep(200);
						if (Players.getLocal().getAnimation() == Variables.animation) {
							timer.reset();
						}
					}
				} else {
					spot.interact(Variables.mode == 0 ? "Net" : "Bait",
							"Fishing spot");
					final Timer timer = new Timer(800);
					while (timer.isRunning()) {
						Task.sleep(200);
						if (Players.getLocal().getAnimation() == Variables.animation) {
							timer.reset();
						}
					}
				}
			} else {
				Walking.walk(spot);
				Camera.turnTo(spot);
			}
		}

	}

}
