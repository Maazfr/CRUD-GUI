import java.awt.*;
import java.awt.event.*;

class appClose extends WindowAdapter
{
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
}

class SchoolApp extends Frame implements ActionListener
{

private Label l1,l2,l3,l4,l5,l6,l7;
private TextField t1,t2;
private Button b1,b2,b3,b4,b5,b6;
private Checkbox male, female;
private CheckboxGroup genderGroup;
private Choice ch;

SchoolApp()
{
super("School-Application");

l1 = new Label("Add Student :");
b1 = new Button("Click me");
l2 = new Label("Edit Student :");
b2 = new Button("Click Me");
l3 = new Label("Delete Student :");
b3 = new Button("Click Me");
l4 = new Label("Search Student :");
b4 = new Button("Click Me");
l5 = new Label("Display List Of Student ;");
b5 = new Button("Click Me");
l6 = new Label("~~EXIT~~");
b6 = new Button("Click Me");
l7 = new Label("Student-Application");
genderGroup = new CheckboxGroup();
male = new Checkbox("Male", genderGroup,true);
female = new Checkbox("Female", genderGroup,false);

setLayout(new GridLayout(6,1));

add(l1); add(b1);
add(l2); add(b2);
add(l3); add(b3);
add(l4); add(b4);
add(l5); add(b5);
add(l6); add(b6);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);


Toolkit toolkit = Toolkit.getDefaultToolkit();
Dimension dimension = toolkit.getScreenSize();

int w = dimension.width - 100;
int h = dimension.height - 100;


setSize(w,h);
int x = dimension.width/2  - w/2;
int y = dimension.height/2 - h/2;
setLocation(x,y);

appClose a = new appClose();
addWindowListener(a);

setVisible(true);
}		//constructor ends here

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource() == b1)
{
new AddGUI();
}

if(ev.getSource() == b2)
{
new edit();
}

if(ev.getSource() == b3)
{
new delete();
}

if(ev.getSource() == b4)
{
new search();
}

if(ev.getSource() == b5)
{
}

if(ev.getSource() == b6)
{
System.exit(0);
}


}		//action performed class ends here

}		//school app class ends here

class ApplicationGUI
{
public static void main(String aa[])
{
SchoolApp schoolApp;
schoolApp = new SchoolApp();
}
}