package sk.tuke.oop.aliens;

import sk.tuke.oop.aliens.actor.AbstractActor;
import sk.tuke.oop.framework.Animation;

public class Hammer extends AbstractActor {
    private int usage;

    public Animation hammerAnimation  = new Animation("images/hammer.png", 16, 16, 10);

    public Hammer() {
        usage = 1;
        setAnimation(hammerAnimation);
    }

    public int getUsage() {
        return usage;
    }

    public void use() {
        usage--;
        if (usage == 0) {
            getWorld().removeActor(this);
        }
    }


}
