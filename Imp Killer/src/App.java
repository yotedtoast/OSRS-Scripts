import java.awt.Graphics2D;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

@ScriptManifest(author = "bubbles", info = "", name = "Imp Killer", logo = "", version = 1.0)

public class App extends Script{
    private OverlayGraphics overlay;
    private long startTime;
    private String task;
    private NPC imp;
    private int IMP_ID = 5007;
    private Area FALADOR_BANK = new Area(3010, 3356, 3014, 3355);
    private Area IMP_AREA = new Area(
        new int[][]{
            { 3004, 3321 },
            { 2992, 3307 },
            { 3005, 3300 },
            { 3021, 3321 }
        }
    );
    
    //SWORD = 1277, SHIELD = 1171
    public void onStart()
    {
        task = "starting";
        log("Starting");
        startTime = System.currentTimeMillis();
        overlay = new OverlayGraphics();
    }

    public int onLoop()
    {
        if (!getInventory().getEquipment().contains(1277))
            gearUp();
        else if (getInventory().isFull())
            goBank(true);
        else if (!IMP_AREA.contains(myPosition()))
            getWalking().webWalk(IMP_AREA);
        else if (((imp = getNpcs().closest(IMP_ID)) != null) && !imp.isUnderAttack())
            imp.interact("Attack");
        else
            hopWorld();
        return 600;
    }

    public void onPaint(Graphics2D graphics)
    {
        overlay.update(System.currentTimeMillis() - startTime, task);
        if (overlay!=null)
            overlay.onPaint(graphics);
    }

    private void gearUp()
    {
        task = "Gearing up";
        goBank(true);
        getBank().withdraw(1277, 11);
        getBank().withdraw(1171, 11);
        new ConditionalSleep(5000)
        {public boolean condition()
            {return getInventory().contains(1277, 1171);}}.sleep();
    }

    private void goBank(boolean empty)
    {
        task = "Banking";
        if (!FALADOR_BANK.contains(myPosition()))
            getWalking().webWalk(FALADOR_BANK);
        else
        {
            try
            {
                if (getBank()!= null)
                {
                    getBank().open();

                    new ConditionalSleep(20000)
                    {
                        public boolean condition()
                        {return (getBank().isOpen());}
                    }.sleep();
                    if (empty)
                    {
                        getBank().depositAll();
                        new ConditionalSleep(10000)
                        {
                            public boolean condition()
                            {return (getInventory().isEmpty());}
                        }.sleep();
                    }
                }
            }
            catch(Exception e)
            {
                log(e.getStackTrace());
            }
        }
    }

    private void hopWorld()
    {

    }
}
