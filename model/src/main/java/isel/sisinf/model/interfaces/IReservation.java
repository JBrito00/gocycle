package isel.sisinf.model.interfaces;

import isel.sisinf.model.Bicycle;

import java.util.Date;

public interface IReservation
{
    public int getNumber();
    public void setNumber(int number);
    public int getBicycle();
    public void setBicycle(Bicycle bicycle);
    public double getPrice();
    public void setPrice(double price);
    public Date getBeginingDate();
    public void setBeginingDate(Date beginingDate);
    public Date getEndingDate();
    public void setEndingDate(Date endingDate);
}
