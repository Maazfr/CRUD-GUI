import java.util.*;

public class StudentManager
{

public void addStudent(int rollNumber, String name, char gender)
{
StudentDAOInterface studentDAOInterface = new StudentDAO();

try
{
if (rollNumber <= 0) {
System.out.println("Invalid roll number");
return;
}
if (studentDAOInterface.exists(rollNumber)) 
{
System.out.println("That roll number exists");
return;
}


StudentDTOInterface studentDTOInterface;
studentDTOInterface = new StudentDTO();

studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDAOInterface.add(studentDTOInterface);
System.out.println("Student added");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
System.out.println("Student not added");
}

}


public void editStudent(int rollNumber, String name, char gender)
{

try
{
StudentDAOInterface studentDAOInterface = new StudentDAO();
StudentDTOInterface studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);

studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDAOInterface.update(studentDTOInterface);
System.out.println("Student Updated");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}


public void deleteStudent(int rollNumber)
{

try
{
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO(); 
studentDTOInterface=studentDAOInterface.getByRollNumber(rollNumber); 

studentDAOInterface.remove(rollNumber);
System.out.println("Student Deleted");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}


public void searchStudent(int rollNumber)
{

try
{
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO(); 
studentDTOInterface=studentDAOInterface.getByRollNumber(rollNumber); 
System.out.println("Student Searched");


}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}

public void displayListOfStudents()
{
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
try
{
List<StudentDTOInterface> students;
students=studentDAOInterface.get();
StudentDTOInterface studentDTOInterface;
for(int i=0;i<students.size();i++)
{
studentDTOInterface=students.get(i); 
System.out.println("Roll number : "+studentDTOInterface.getRollNumber()); System.out.println("Name : "+studentDTOInterface.getName()); 
System.out.println("Gender : "+studentDTOInterface.getGender());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}