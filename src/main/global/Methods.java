package main.global;

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.imageio.ImageIO;

import main.DraynorAIO;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Lobby;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;

public class Methods {
	
	public static boolean depositInventory(final boolean fast) {
		if(fast) {
			final WidgetChild di = Widgets.get(762).getChild(34);
			if(di != null) {
				final Point dip = di.getCentralPoint();
				Mouse.hop(dip.x, dip.y);
				return Mouse.click(true);
			}
		} else {
			return Bank.depositInventory();
		}
		return false;
	}
	
	public static boolean openBank() {
		if(Menu.isOpen() && Menu.contains("Bank")) {
			return Menu.select("Bank");
		} else {
			final NPC banker = NPCs.getNearest(Const.BANKERS);
			if (banker != null) {
				if (banker.click(false) && Menu.isOpen() && Menu.contains("Bank")) {
					return Menu.select("Bank");
				}
			}
		}
		return false;
	}

	public static boolean isInBank() {
		return main.global.Const.BANK_AREA.contains(Players.getLocal().getLocation());
	}

	public static boolean isIn() {
		return Game.isLoggedIn() && Game.getClientState() == 11
				&& !Context.resolve().refreshing && !Lobby.isOpen();
	}

	public static void s(String s) {
		DraynorAIO.status = s;
		System.out.println("[Draynor AIO] " + s);
	}

	public static int getPerHour(int base, long time) {
		return (int) ((base) * 3600000D / (System.currentTimeMillis() - time));
	}

	public static int[] listToArray(List<Integer> ints) {
		int[] ret = new int[ints.size()];
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = ints.get(i).intValue();
		}
		return ret;
	}

	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public static int getPrice(final int id) {
		try {
			String price;
			final URL url = new URL(
					"http://open.tip.it/json/ge_single_item?item=" + id);
			final URLConnection con = url.openConnection();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("mark_price")) {
					price = line.substring(line.indexOf("mark_price") + 13,
							line.indexOf(",\"daily_gp") - 1);
					price = price.replace(",", "");
					return Integer.parseInt(price);

				}
			}
		} catch (final Exception ignored) {
			return 0;
		}
		return 0;
	}
}
