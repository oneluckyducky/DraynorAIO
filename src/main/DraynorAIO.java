package main;

import gui.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import main.global.Const;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

import painting.FishingPaint;
import painting.FletchingPaint;
import painting.MoneyPaint;
import painting.SpawnerPaint;

@Manifest(authors = { "OneLuckyDuck" }, name = "Draynor AIO 0.3D", description = "Does everything in draynor village", version = 0.3D)
public class DraynorAIO extends ActiveScript implements PaintListener,
		MessageListener {

	private Tree jobContainer = null;
	public static ArrayList<Node> jobs = new ArrayList<Node>();

	public final static Timer timer = new Timer(0);
	public static int globalMode = -1;
	public static long startTime;
	public static boolean guiIsDone = false;
	public static String status = null;
	
	

	public void onStart() {
		main.global.Methods.s("Waiting for GUI");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI g = new GUI();
				g.setVisible(true);
			}
		});

		startTime = System.currentTimeMillis();

		Mouse.setSpeed(Speed.FAST);

	}

	@Override
	public int loop() {
		if (main.global.Methods.isIn()) {
			if (guiIsDone) {
				if (jobContainer != null) {
					final Node job = jobContainer.state();
					if (job != null) {
						jobContainer.set(job);
						getContainer().submit(job);
						job.join();
					}
				} else {
					jobContainer = new Tree(jobs.toArray(new Node[jobs.size()]));
				}
			}
		}
		return Random.nextInt(100, 150);
	}

	@Override
	public void onRepaint(Graphics g) {
		if (main.global.Methods.isIn()) {
			final Point p = Mouse.getLocation();

			g.setColor(Mouse.isPressed() ? Color.GREEN : Color.white);
			g.drawOval(p.x - 3, p.y - 3, 6, 6);

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, (int) Game.getDimensions().getWidth(), 50);

			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 11));
			g.drawString("Run Time: " + DraynorAIO.timer.toElapsedString(), 3,
					12);

			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Color.BLUE);
			g2.setFont(new Font("Arial", Font.BOLD, 12));
			g2.drawImage(Const.BAR, 0, 510, null, null);
			g2.drawString("Status: " + status, 15, 522);

			switch (globalMode) {
			case 0: // spawning
				SpawnerPaint.onRepaint(g);
				break;
			case 1: // fletching
				FletchingPaint.onRepaint(g);
				break;
			case 2: // fishing
				FishingPaint.onRepaint(g);
				break;
			case 3:
				MoneyPaint.onRepaint(g);
				break;
			default:
				return;
			}

		}
	}

	@Override
	public void messageReceived(MessageEvent msg) {
		String m = msg.getMessage();
		if (m.contains("produced ")) {
			spawner.misc.Variables.produced++;
		}

		if (m.contains("the wood") || m.contains("add a string")) {
			fletching.misc.Variables.actions++;
		}

		if (m.contains("ardin")) {
			fishing.misc.Variables.sardines++;
		}
		if (m.contains("hrim")) {
			fishing.misc.Variables.shrimps++;
		}
		if (m.contains("erri")) {
			fishing.misc.Variables.herrings++;
		}
		if (m.contains("nchov")) {
			fishing.misc.Variables.anchovies++;
		}

	}
}
