import java.awt.*;
import java.awt.event.*;

class search extends Frame implements ActionListener
{

private Label l1;
private TextField tRoll;
private TextArea ta1;
private Button b1,b2;

search()
{
super("Search Module");

l1 = new Label("Enter Roll-Number Of Student :");
tRoll = new TextField(20);
ta1 = new TextArea();
b1 = new Button("Search");
b2 = new Button("Cancel");

FlowLayout f = new FlowLayout();
setLayout(f);

add(l1);
add(tRoll);
add(b1);
add(b2);
add(ta1);

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

setVisible(true);
}	//contructor ends here

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource() == b1)
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

if(ev.getSource() == b2)
{
this.dispose();
}

}		//action performed function ends here

}	//search class ends here

class SearchGUI
{
public static void main(String aa[])
{
search s;
s = new search();
}
}