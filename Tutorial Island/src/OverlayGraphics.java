import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import org.osbot.rs07.canvas.paint.Painter;

public class OverlayGraphics implements Painter {
    private long runTime;

    @Override
    public void onPaint(Graphics2D graphics) {
        Font font = new Font("Arial", Font.BOLD, 16);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Runtime: " + runTime, 100, 100);
    }

    public void update(long runTime)
    {
        this.runTime = runTime;
    }
}