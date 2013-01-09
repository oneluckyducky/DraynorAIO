package painting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import main.DraynorAIO;
import money.misc.*;

public class MoneyPaint {

	public static void onRepaint(Graphics g) {
		switch (Variables.mode) {
		case 0: // soft clay
		case 1: // fill vials
		case 2: // basket
			
			
			 int madeHour = main.global.Methods.getPerHour(Variables.made,
						DraynorAIO.startTime);
				 int profitBasket = (Variables.fullBasketPrice
						- (Variables.fruitPrice * 5) - Variables.basketPrice);

				 int profit = (Variables.made * profitBasket);
				 int profitHour = main.global.Methods.getPerHour(profit,
						DraynorAIO.startTime);

				 String profitStr = DecimalFormat.getInstance().format(profit);
				 String profitHourly = DecimalFormat.getInstance().format(
						profitHour);

				 String made = DecimalFormat.getInstance().format(
						Variables.made);
				 String madeHourly = DecimalFormat.getInstance().format(
						madeHour);

				 String fruitPrice = DecimalFormat.getInstance().format(
						Variables.fruitPrice);
				 String basketPrice = DecimalFormat.getInstance().format(
						Variables.basketPrice);
				 String profitPerBasket = DecimalFormat.getInstance().format(
						profitBasket);

			g.drawString("Fruit Id: " + Variables.fruitId, 3, 25);
			g.drawString("Fruit Price: " + fruitPrice, 3, 38);

			g.drawString("Basket Price: " + basketPrice, 145, 12);
			g.drawString("Profit per Basket: " + profitPerBasket, 145, 25);
			g.drawString("Profit:  " + profitStr + "  (" + profitHourly + ")",
					145, 38);

			g.drawString("Baskets Filled: " + made + "  (" + madeHourly + ")",
					287, 25);

			g.setColor(Color.GRAY);
			g.setFont(new Font("Kristen ITC", Font.BOLD, 11));
			g.drawString("Draynor Basket Filling by OneLuckyDuck", 5, 382);
			break;

		}
	}

}
