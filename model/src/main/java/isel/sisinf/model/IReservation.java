package isel.sisinf.model;

import java.sql.Date;

public interface IReservation
{
    public int getBicycle();
    public void setBicycle(int bicycle);
    public int getValue();
    public void setValue(int value);
    public Date getBeginingDate();
    public void setBeginingDate(Date beginingDate);
    public Date getEndingDate();
    public void setEndingDate(Date endingDate);
}
