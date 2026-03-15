import java.awt.*;
import java.awt.event.*;

class editClose extends WindowAdapter
{
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
}


class edit extends Frame implements ActionListener
{

private Label l1,l2,l3;
private TextField tRoll,tName;
private Button b1,b2,b3;
private Checkbox male, female;
private CheckboxGroup genderGroup;
private TextArea ta1;

edit()
{
super("Edit Module");

l1 = new Label("Enter Roll Number :");
tRoll = new TextField(25);
l2 = new Label("Enter New Name :");
tName = new TextField(45);
l3 = new Label("Edit Gender :");
genderGroup = new CheckboxGroup();
male = new Checkbox("Male",genderGroup,true);
female = new Checkbox("Female",genderGroup,false);
b1 = new Button("Edit");
b2 = new Button("CANCEL");
ta1 = new TextArea();
b3 = new Button("search");

FlowLayout f = new FlowLayout();
setLayout(f);
add(l1);
add(tRoll);
add(b3);
add(ta1);
add(l2);
add(tName);
add(l3);
add(male);
add(female);
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

editClose c = new editClose();
addWindowListener(c);

setVisible(true);
}		//contructor ends here

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

}catch(NumberFormatException nfe)
{
System.out.println("Roll number must be a number");
}catch(DAOException de)
{
System.out.println(de.getMessage());
}

}

if(ev.getSource() == b1)
{

int rollNumber = Integer.parseInt(tRoll.getText().trim());
String name = tName.getText().trim();
Checkbox selected = genderGroup.getSelectedCheckbox();
char gender;
if (selected.getLabel().equalsIgnoreCase("Male"))
{
gender = 'M';
}
else
{
gender = 'F';
}

StudentManager studentManager = new StudentManager();
studentManager.editStudent(rollNumber, name, gender);


}

if(ev.getSource() == b2)
{
this.dispose();
}

}		// action performed function ends here

}	//edit class ends here

class EditGUI
{
public static void main(String aa[])
{
edit e;
e = new edit();
}
}