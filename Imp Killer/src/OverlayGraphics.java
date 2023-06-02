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
        graphics.drawString("Runtime: " + formatTime(runTime), 100, 100);
        graphics.drawString("Current Task: " + task, 100, 120);
        graphics.drawString("Imps Killed: " + imps, 100, 140);
    }

    public void update(long runTime, String task, int imps)
    {
        this.runTime = runTime;
        this.task = task;
        this.imps = imps;
    }

    private final String formatTime(final long ms){
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60; m %= 60; h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}