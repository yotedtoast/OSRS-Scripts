import java.awt.Graphics2D;

import org.osbot.rs07.api.Skills;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "bubbles", info = "I'm depressed", name = "", logo = "", version = 0.0)

public class App extends Script{
    private OverlayGraphics overlay;
    private long startTime;

    private final Area TREEAREA = new Area(3141, 3515, 3154, 3505);
    private final Area BANKAREA = new Area(3160, 3492, 3162, 3488);

    public void onStart()
    {
        log("Starting");
        startTime = System.currentTimeMillis();
        overlay = new OverlayGraphics();
        getExperienceTracker().start(Skill.WOODCUTTING);
    }

    public int onLoop()
    {
        if (getInventory().getEquipment().contains(1351) || getInventory().getEquipment().contains(1349))
        {
            getAxe();
        }
        return 0;
    }

    public void onPaint(Graphics2D graphics)
    {
        overlay.update(System.currentTimeMillis() - startTime);
        if (overlay!=null)
            overlay.onPaint(graphics);
    }

    private void getAxe()
    {
        
    }
}
