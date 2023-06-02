import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import org.osbot.rs07.canvas.paint.Painter;

public class OverlayGraphics implements Painter {
    private long runTime;
    private String task;
    private int imps;

    @Override
    public void onPaint(Graphics2D graphics) {
        Font font = new Font("Arial", Font.BOLD, 16);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Runtime: " + runTime, 100, 100);
        graphics.drawString("Current Task: " + task, 100, 120);
        graphics.drawString("Imps Killed: " + imps, 100, 140);
    }

    public void update(long runTime, String task, int imps)
    {
        this.runTime = runTime;
        this.task = task;
        this.imps = imps;
    }
}