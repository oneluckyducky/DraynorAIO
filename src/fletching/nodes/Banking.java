package fletching.nodes;

import java.awt.Point;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.bot.Context;

import fletching.misc.Methods;
import fletching.misc.Variables;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn()
				&& Inventory.getCount(Variables.action == 0 ? Variables.logType
						: 1777) == 0
				|| main.global.Methods.isIn()
				&& !Inventory.containsAll(
						Variables.bowType == 0 ? Variables.shortbowId
								: Variables.longbowId, 1777);
	}

	@Override
	public void execute() {
		main.global.Methods.s("Opening bank");
		if (main.global.Methods.openBank()) {
			while (!Bank.isOpen()) {
				Task.sleep(100);
			}
		}
		main.global.Methods.s("Depositing Inventory");
		if(main.global.Methods.depositInventory(true)) { //762, 34
			while(Inventory.getCount() > 0) {
				Task.sleep(100);
			}
		}
		if (Bank.isOpen()) {
			if (Variables.action == 0) { // cutting
				if (!Methods.bankContains(Variables.logType)) {
					Methods.stop(2);
				}
				if (!Methods.playerHasItem(Variables.logType)) {
					main.global.Methods.s("Withdrawing log");
					final Item log = Bank.getItem(Variables.logType);
					if(log != null) {
						final Point logp = log.getWidgetChild().getCentralPoint();
						Mouse.hop(logp.x, logp.y);
						if(Mouse.click(false) && Menu.isOpen() && Menu.contains("Withdraw-All")) {
							Mouse.hop(logp.x, logp.y + 110);
							Mouse.click(true);
						}
					}
				}
			} else {
				if (!Methods.bankContains(1777)) {
					Methods.stop(1);
				}
				if (!Methods
						.bankContains(Variables.bowType == 0 ? Variables.shortbowId
								: Variables.longbowId)) {
					Methods.stop(0);
				}
				if (!Methods.playerHasItem(1777)) { // bow string
					main.global.Methods.s("Withdrawing bow string");
					final Item bowstring = Bank.getItem(1777);
					if(bowstring != null) {
						final Point bsp = bowstring.getWidgetChild().getCentralPoint();
						Mouse.hop(bsp.x, bsp.y);
						if(Mouse.click(false) && Menu.isOpen()) {
							if(Menu.contains("Withdraw-14")) {
								Mouse.hop(bsp.x, bsp.y + 78);
								Mouse.click(true);
							} else {
								Bank.withdraw(1777, 14);
							}
						}
					}
				}
				if (!Methods
						.playerHasItem(Variables.bowType == 0 ? Variables.shortbowId
								: Variables.longbowId)) {
					main.global.Methods.s("Withdrawing unstrung bow");
					final Item bow = Bank.getItem(Variables.bowType == 0 ? Variables.shortbowId
							: Variables.longbowId);
					if(bow != null) {
						final Point bowp = bow.getWidgetChild().getCentralPoint();
						Mouse.hop(bowp.x, bowp.y);
						if(Mouse.click(false) && Menu.isOpen()) {
							if(Menu.contains("Withdraw-14")) {
								Mouse.hop(bowp.x, bowp.y + 78);
								Mouse.click(true);
								Task.sleep(300);
							} else {
								Bank.withdraw(Variables.bowType == 0 ? Variables.shortbowId
										: Variables.longbowId, 14);
							}
						}
					}
				}
			}
		}

		Bank.close();
		while(Bank.isOpen()) {
			Task.sleep(500);
		}

		if (!Methods.playerHasItem(Variables.action == 0 ? Variables.logType
				: Variables.bowType == 0 ? Variables.shortbowId
						: Variables.longbowId)) {
			Variables.fails++;
		} else if (Variables.action == 1 && !Methods.playerHasItem(1777)) {
			Variables.fails++;
		} else {
			Variables.fails = 0;
		}
		if (Variables.fails == 4) {
			Context.get().getScriptHandler().stop();
		}
	}

}
