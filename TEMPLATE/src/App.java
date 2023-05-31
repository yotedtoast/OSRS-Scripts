import java.awt.Graphics2D;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "bubbles", info = "I'm depressed", name = "", logo = "", version = 0.0)

public class App extends Script{
    private OverlayGraphics overlay;
    private long startTime;

    public void onStart()
    {
        log("Starting");
        startTime = System.currentTimeMillis();
        overlay = new OverlayGraphics();
    }

    public int onLoop()
    {
        return 0;
    }

    public void onPaint(Graphics2D graphics)
    {
        overlay.update(System.currentTimeMillis() - startTime);
        if (overlay!=null)
            overlay.onPaint(graphics);
    }
}
