package sk.tuke.oop.aliens;

import sk.tuke.oop.framework.Animation;
import sk.tuke.oop.aliens.actor.AbstractActor;

public class Reactor extends AbstractActor {

    private boolean zahrievanie = true;

    private int temperature;
    private int damage;
    private String manufacturer;
    public Animation normalAnimation;
    public Animation hotAnimation;
    public Animation brokenAnimation;

    public int getTemperature(){
        return temperature;
    }

    public int getDamage(){
        return damage;
    }

    public void increaseTemperature(int increment){

        if (increment < 0)
            return;
        zahrievanie = true;

        if (getTemperature() + increment > 6000){
            increment = 6000 - getTemperature();
        }
//        if (getTemperature() == 6000){
//            setAnimation(brokenAnimation);
//        }
        if (getTemperature() < 6000){
            setTemperature(temperature + increment);
        }
//        if (getTemperature() > 4000 && getTemperature() < 6000){
//            setAnimation(hotAnimation);
//        }
        if (getTemperature() > 2000) {
            setDamage((getTemperature() - 2000) * 100 / 4000);
        }
        updateAnimation();


    }


    public void decreaseTemperature(int decrement) {

        if (decrement < 0)
            return;

        zahrievanie = false;

        if(getTemperature() > 0) {
            setTemperature(getTemperature() - decrement);
        }

        updateAnimation();
    }

    private void updateAnimation() {
        int temp = getTemperature();

        if (temp > 2000 && temp < 6000){
            setAnimation(hotAnimation);
        }

        if (temp < 4000) {
            setAnimation(normalAnimation);
        }

        if (temp > 4000 && temp < 6000){
            setAnimation(hotAnimation);
        }
        if (temp >= 6000) {
            setAnimation(brokenAnimation);
        }

    }



    public Reactor() {

        temperature = 0;
        damage = 0;
        manufacturer = null;

                // create animation object
        normalAnimation = new Animation("images/reactor_on.png", 80, 80, 100);
        hotAnimation = new Animation("images/reactor_hot.png", 80, 80, 50);
        brokenAnimation = new Animation("images/reactor_broken.png", 80, 80, 100);
        // play animation repeatedly
        normalAnimation.setPingPong(true);
        // set actor's animation to just created Animation object
        setAnimation(normalAnimation);
    }

    public Reactor(String manufacturer) {

        temperature = 0;
        damage = 0;
        this.manufacturer = manufacturer;

                // create animation object
        normalAnimation = new Animation("images/reactor_on.png", 80, 80, 100);
        hotAnimation = new Animation("images/reactor_hot.png", 80, 80, 50);
        brokenAnimation = new Animation("images/reactor_broken.png", 80, 80, 100);
        // play animation repeatedly
        normalAnimation.setPingPong(true);
        // set actor's animation to just created Animation object
        setAnimation(normalAnimation);
    }


    public boolean idServiceNeeded() {
        if (damage > 50 && temperature > 3000) {
            return true;
        }
        else
            return false;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void repairWith(Hammer hammer) {
        if(hammer != null && damage > 0 && damage < 100){

            damage -= 50;
            if (damage < 0) damage = 0;
            temperature = (damage * 30 + 2000 < temperature) ? damage * 30 + 2000 : temperature; //preratat je problem, zadanie v spanielcine
            updateAnimation();
            hammer.use();
        }
    }

}

