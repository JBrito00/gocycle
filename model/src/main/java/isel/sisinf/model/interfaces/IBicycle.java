package isel.sisinf.model.interfaces;

import isel.sisinf.model.GearSystem;
import isel.sisinf.model.Status;

public interface IBicycle
{
    public int getId();
    public void setId(int id);
    public String getModel();
    public void setModel(String name);
    public double getWeight();
    public void setWeight(double weight);
    public String getBrand();
    public void setBrand(String brand);
    public GearSystem getGearSystem();
    public void setGearSystem(GearSystem gearSystem);
    public Status getStatus();
    public void setStatus(Status status);

    /*
    public int getAutonomy();
    public void setAutonomy(int autonomy);
    public int getMaxSpeed();
    public void setMaxSpeed(int maxVelocity);
     */
}
