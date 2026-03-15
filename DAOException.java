public class DAOException extends Exception
{
public DAOException(String message)
{
super(message);
}
public String toString()
{
return "com.school.dl.exceptions.DAOException :"+this.getMessage();
}
}