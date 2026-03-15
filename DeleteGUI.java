import java.awt.*;
import java.awt.event.*;

class deleteClose extends WindowAdapter
{
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
}


class delete extends Frame implements ActionListener
{
private Label l1,l2,l3;
private TextField tRoll;
private Checkbox male, female;
private CheckboxGroup genderGroup;
private Button b1,b2,b3;
private TextArea ta1;


delete()
{
super("Delete Module");

l1 = new Label("Enter Roll number to delete :");
tRoll = new TextField(25);
b3 = new Button("Search");
b1 = new Button("DELETE");
b2 = new Button("Cancel");
ta1 = new TextArea();

FlowLayout f = new FlowLayout();
setLayout(f);
add(l1);
add(tRoll);
add(b3);
add(ta1);
add(b1);
add(b2);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);

Toolkit toolkit = Toolkit.getDefaultToolkit();
Dimension dimension = toolkit.getScreenSize();

int w = dimension.width - 1100;
int h = dimension.height - 500;

setSize(w,h);
int x = dimension.width/2  - w/2;
int y = dimension.height/2 - h/2;
setLocation(x,y);
setVisible(true);

deleteClose dc = new deleteClose();
addWindowListener(dc);

setVisible(true);
}	//contructor ends here

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource() == b3)
{

try
{
int rollNumber = Integer.parseInt(tRoll.getText().trim());

StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO(); 
studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);

ta1.setText("Roll Number : "+ rollNumber+"\nName : "+studentDTOInterface.getName()+"\nGender : "+studentDTOInterface.getGender());

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}

if(ev.getSource() == b1)
{

int rollNumber = Integer.parseInt(tRoll.getText().trim());
StudentManager studentManager = new StudentManager();
studentManager.deleteStudent(rollNumber);

}

if(ev.getSource() == b2)
{
this.dispose();
}

}		// action performed function ends here

}	//delete class ends here

class DeleteGUI
{
public static void main(String aa[])
{
delete d;
d = new delete();
}
}