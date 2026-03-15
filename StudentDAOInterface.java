import java.util.*;
public interface StudentDAOInterface
{
public void add(StudentDTOInterface studentDTO) throws DAOException; 
public void update(StudentDTOInterface studentDTO) throws DAOException; 
public void remove(int rollNumber) throws DAOException; 
public StudentDTOInterface getByRollNumber(int rollNumber) throws DAOException; 
public boolean exists(int rollNumber) throws DAOException; 
public List<StudentDTOInterface> get() throws DAOException;
}