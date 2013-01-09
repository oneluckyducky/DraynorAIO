package spawner.nodes.cobra;

import java.awt.Point;

import main.Summoning;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

import spawner.misc.Const;

public class ObeliskWalk extends Node {

	@Override
	public boolean activate() {
		return main.global.Methods.isIn() && !Summoning.isFamiliarSummoned()
				&& Summoning.getPoints() == 0;
	}

	@Override
	public void execute() {
		main.global.Methods.s("Walking to obelisk");
		Walking.walk(new Tile(3091, 3222, 0));
		final SceneObject obelisk = SceneEntities.getNearest(Const.OBELISK_ID);
		if (obelisk != null && obelisk.isOnScreen()) {
			Point p = obelisk.getCentralPoint();
			main.global.Methods.s("Recharging Summoning Points");
			Mouse.hop(p.x, p.y);
			if (Mouse.click(false) && Menu.isOpen()
					&& Menu.contains("Renew-points")) {
				Menu.select("Renew-points");
			}
			Camera.turnTo(obelisk);
			Task.sleep(500);
			if (Menu.isOpen() && !Menu.contains("Renew-points")) {
				Camera.setAngle(Random.nextInt(50, 189));
			}
		}
	}
}
