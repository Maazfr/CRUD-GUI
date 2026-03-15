import java.io.*;
import java.util.*;

public class StudentDAO implements StudentDAOInterface
{
private static String fileName="student.data"; 
public void add(StudentDTOInterface studentDTOInterface) throws DAOException
{
int rollNumber;
String name;
char gender;
rollNumber=studentDTOInterface.getRollNumber();
name=studentDTOInterface.getName();
gender=studentDTOInterface.getGender();
if(rollNumber<=0)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
if(name==null)
{
throw new DAOException("Name required");
}
name=name.trim();
if(name.length()==0)
{
throw new DAOException("Name required");
}
if(gender!='M' && gender!='F' && gender!='m' && gender!='f')
{
throw new DAOException("Invalid gender : "+gender);
}
if(gender>=97 && gender<=122) gender=(char)(((int)gender)-32);
if(this.exists(rollNumber))
{
throw new DAOException("Roll number : "+rollNumber+" exists.");
}
try
{
File file=new File(fileName);
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(randomAccessFile.length());
randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean exists(int rollNumber) throws DAOException
{
try
{
String rollNumberString;
File file=new File(fileName);
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw"); 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
if(rollNumber==Integer.parseInt(rollNumberString))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine(); // read name part
randomAccessFile.readLine(); // read gender part
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(StudentDTOInterface studentDTOInterface) throws DAOException
{
int rollNumber;
String name;
char gender;
rollNumber=studentDTOInterface.getRollNumber();
name=studentDTOInterface.getName();
gender=studentDTOInterface.getGender();
if(rollNumber<=0)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
if(name==null)
{
throw new DAOException("Name required");
}
name=name.trim();
if(name.length()==0)
{
throw new DAOException("Name required");
}
if(gender!='M' && gender!='F' && gender!='m' && gender!='f')
{
throw new DAOException("Invalid gender : "+gender);
}
if(gender>=97 && gender<=122) gender=(char)(((int)gender)-32);
if(this.exists(rollNumber)==false)
{
throw new DAOException("Roll number does not exist : "+rollNumber+" exists.");
}
try
{
File file=new File(fileName);
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw");
File tmpFile=new File("fhaltu.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile; 
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
int vRollNumber;
String vName;
char vGender; while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);
if(vRollNumber!=rollNumber)
{
tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(rollNumber+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(gender+"\n");
}
}
randomAccessFile.seek(0); // move the internal pointer
tmpRandomAccessFile.seek(0); 
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void remove(int rollNumber) throws DAOException
{
if(rollNumber<=0)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
if(this.exists(rollNumber)==false)
{
throw new DAOException("Roll number does not exist : "+rollNumber+" exists.");
}
try
{
File file=new File(fileName);
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw");
File tmpFile=new File("fhaltu.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile; 
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
int vRollNumber;
String vName;
char vGender; 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);
if(vRollNumber!=rollNumber)
{
tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
}
}
randomAccessFile.seek(0); // move the internal pointer
tmpRandomAccessFile.seek(0); 
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public StudentDTOInterface getByRollNumber(int rollNumber) throws DAOException
{
if(rollNumber<=0)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
try
{
File file;
file=new File(fileName); 
if(file.exists()==false) throw new DAOException("Roll number : "+rollNumber+" does not exist");
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw");
int vRollNumber;
String vName;
char vGender; 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
if(vRollNumber==rollNumber)
{
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);
randomAccessFile.close(); 
return new StudentDTO(vRollNumber,vName,vGender);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.close(); 
throw new DAOException("Invalid roll number : "+rollNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public List<StudentDTOInterface> get() throws DAOException
{
List<StudentDTOInterface> students;
students=new ArrayList<StudentDTOInterface>();
StudentDTO studentDTO;
int rollNumber;
String name;
char gender;
try
{
File file=new File(fileName);
if(file.exists()==false) return students;
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw"); while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumber=Integer.parseInt(randomAccessFile.readLine());
name=randomAccessFile.readLine();
gender=randomAccessFile.readLine().charAt(0); 
studentDTO=new StudentDTO(rollNumber,name,gender);
students.add(studentDTO);
}
randomAccessFile.close();
return students;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}