import java.awt.*;
import java.awt.event.*;

class addClose extends WindowAdapter
{
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
}


public class AddGUI extends Frame implements ActionListener
{
private Label l1,l2,l3;
private TextField tName,tRoll;
private Checkbox male, female;
private CheckboxGroup genderGroup;
private Button b1,b2;

AddGUI()
{
super("Add Module ");

l1 = new Label("Name :");
tName = new TextField(45);
l2 = new Label("Roll Number :");
tRoll = new TextField(20);
l3 = new Label("Gender :");
genderGroup = new CheckboxGroup();
male = new Checkbox("Male",genderGroup,true);
female = new Checkbox("Female",genderGroup,false);
b1 = new Button("ADD");
b2 = new Button("CANCEL");

FlowLayout f = new FlowLayout();
setLayout(f);
add(l1);
add(tName);
add(l2);
add(tRoll);
add(l3);
add(male);
add(female);
add(b1);
add(b2);

b1.addActionListener(this);
b2.addActionListener(this);


Toolkit toolkit = Toolkit.getDefaultToolkit();
Dimension dimension = toolkit.getScreenSize();

int w = dimension.width - 1100;
int h = dimension.height - 500;

setSize(w,h);
int x = dimension.width/2  - w/2;
int y = dimension.height/2 - h/2;
setLocation(x,y);
setVisible(true);

addClose ac = new addClose();
addWindowListener(ac);

setVisible(true);
}		//constructor ends here

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource() == b1)
{

try
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
studentManager.addStudent(rollNumber, name, gender);

}catch (NumberFormatException nfe) 
{
System.out.println("Roll number must be a number");
}

}

if(ev.getSource() == b2)
{
this.dispose();
}

}		// action performed function ends here



public static void main(String aa[])
{
new AddGUI();
}

}		// AddGUI class ends here