package eu.epitech.Model.Entity;

public interface IUser
{
    public void setId(int id);
    public int getId();
    public void setEmail(String mail);
    public String getEmail();
    public void setPassword(String password);
    public String getPassword();
}
