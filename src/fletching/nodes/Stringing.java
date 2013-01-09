package fletching.nodes;

import java.awt.Point;

import main.global.Const;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;

import fletching.misc.Methods;
import fletching.misc.Variables;

public class Stringing extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn()
				&& Players.getLocal().getAnimation() == -1
				&& Methods.playerHasItem(1777)
				&& Variables.action == 1
				&& Methods
						.playerHasItem(Variables.bowType == 0 ? Variables.shortbowId
								: Variables.longbowId);
	}

	@Override
	// i am an ass faggot
	public void execute() {
		if(Bank.isOpen()) {
			Bank.close();
		}
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
			Task.sleep(600);
		}
		final Item bs = Inventory.getItem(1777); // BOW STRING
		final Item ub = Inventory
				.getItem(Variables.bowType == 0 ? Variables.shortbowId
						: Variables.longbowId);

		if (bs != null && ub != null) {
			Point pbs = bs.getWidgetChild().getCentralPoint();
			Point pub = ub.getWidgetChild().getCentralPoint();
			Mouse.hop(pbs.x, pbs.y);
			Task.sleep(100);
			if(Mouse.click(true)) {
				Mouse.hop(pub.x, pub.y);
				Task.sleep(100);
				Mouse.click(true);
			}
			final Timer timer1 = new Timer(1500);
			final Widget make = Widgets.get(1370); // 38
			while(timer1.isRunning() && !make.validate()) {
				Task.sleep(200);
				if(make.getChild(38) != null && make.getChild(38).isOnScreen()) {
					break;
				}
			}
			main.global.Methods.s("Stringing bow");
			final Point mp = make.getChild(38).getCentralPoint();
			Mouse.hop(mp.x,mp.y);
			if(Mouse.click(mp, true)) {
				while (make != null && make.validate()) {
					Task.sleep(50);
				}
			}
			while (make != null && make.validate()) {
				Task.sleep(50);
			}
			Mouse.click(NPCs.getNearest(Const.BANKERS).getCentralPoint(), false);
			Mouse.move(Mouse.getLocation().x, Mouse.getLocation().y + Random.nextInt(25, 40));
			final Timer timer = new Timer(0);
			while (Inventory.getCount(1777) > 0) {
				Task.sleep(50);
				if (timer.getElapsed() > 14500) {
					break;
				}
			}
		}
	}
}
