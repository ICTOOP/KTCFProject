import java.awt.*; 						
import javax.swing.*;					
import java.awt.event.*; 			
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ProductSale extends JFrame  {
  JPanel panel;
  JLabel idlbl, namelbl, pricelbl, qlbl, anslbl;
  JLabel datelbl,piclbl,cnamelbl,cadslbl,blanklbl;
  JTextField idtxt, nametxt, pricetxt, qtxt;
  JPasswordField pwtxt;  Icon ani;
  JButton rptbtn, savebtn, resetbtn;
  Font fn = new Font("Estrangelo Edessa",Font.PLAIN,18);
  Font tfn = new Font("TH Sarabun New",Font.BOLD,24);
  AddPanel y;
  product prod = new product();
  product pr[] = new product[50];
  int count=0;
  String pid, pname;
  float pprice, pqty;
  public ProductSale(String title) {
    setTitle(title);
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    buildPanel();
    add(panel);
    setVisible(true);
  }
  private void buildPanel() {
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    
    ani = new ImageIcon("SHIPPING.gif");
    piclbl = new JLabel(ani,SwingConstants.LEFT);      
    cnamelbl = new JLabel("University of Phayao");
    cadslbl = new JLabel("Object-Oriented Concepts and Programming");       
    Date DateName = new Date();
    SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
    String dd = df.format(DateName.getTime());
    datelbl = new JLabel("Date : " + dd + "   ");             
    
    idlbl = new JLabel("Product ID");
    namelbl = new JLabel("Product Name");
    pricelbl = new JLabel("Price/Unit");
    qlbl = new JLabel("Unit");
    idtxt = new JTextField(15);
    nametxt = new JTextField(15);
    nametxt.setEnabled(false);
    pricetxt = new JTextField(15);
    pricetxt.setEnabled(false);
    qtxt = new JTextField(15);
    
    piclbl.setFont(fn); 
    cnamelbl.setFont(fn);
    cadslbl.setFont(fn);
    datelbl.setFont(fn);    
    idlbl.setFont(fn);
    namelbl.setFont(fn);
    pricelbl.setFont(fn);
    qlbl.setFont(fn);
    idtxt.setFont(fn);
    nametxt.setFont(fn);
    pricetxt.setFont(fn);
    qtxt.setFont(fn);    
    ani = new ImageIcon("report.gif");
    rptbtn = new JButton("Report",ani);
    ani = new ImageIcon("save.gif");
    savebtn = new JButton("Save",ani);
    ani = new ImageIcon("reset.gif");
    resetbtn = new JButton("Reset",ani);  
    anslbl = new JLabel("",SwingConstants.CENTER);
    anslbl.setOpaque(true);
    anslbl.setBackground(Color.blue);
    anslbl.setForeground(Color.white);
    anslbl.setPreferredSize(new Dimension(350,30));
    rptbtn.setFont(fn);
    savebtn.setFont(fn);
    resetbtn.setFont(fn);
    anslbl.setFont(tfn);
    idtxt.addActionListener(new TextListener());
    qtxt.addActionListener(new TextListener());
    rptbtn.addActionListener(new ButtonListener());
    savebtn.addActionListener(new ButtonListener());
    resetbtn.addActionListener(new ButtonListener());

    y = new AddPanel();    
    y.addItem(panel,piclbl,0,0,1,3,GridBagConstraints.WEST);
    y.addItem(panel,cnamelbl,1,0,2,1,GridBagConstraints.WEST);
    y.addItem(panel,cadslbl,1,1,2,1,GridBagConstraints.WEST);    
    
    y.addItem(panel,datelbl,  1,2,3,1,GridBagConstraints.EAST);  
    
    y.addItem(panel,idlbl,0,3,1,1,GridBagConstraints.WEST);
    y.addItem(panel,idtxt,1,3,2,1,GridBagConstraints.EAST);
    
    y.addItem(panel,namelbl,0,4,1,1,GridBagConstraints.WEST);
    y.addItem(panel,nametxt,1,4,2,1,GridBagConstraints.EAST);
    
    y.addItem(panel,pricelbl,0,5,1,1,GridBagConstraints.WEST);
    y.addItem(panel,pricetxt,1,5,2,1,GridBagConstraints.EAST);
    
    y.addItem(panel,qlbl,0,6,1,1,GridBagConstraints.WEST);
    y.addItem(panel,qtxt,1,6,2,1,GridBagConstraints.EAST);
    
    y.addItem(panel,rptbtn,0,7,1,1,GridBagConstraints.WEST);
    y.addItem(panel,savebtn,1,7,1,1,GridBagConstraints.WEST);
    y.addItem(panel,resetbtn,2,7,1,1,GridBagConstraints.WEST);
    
    y.addItem(panel,anslbl,0,8,4,1,GridBagConstraints.CENTER);
  }
  private class TextListener implements ActionListener {
    @Override  
    public void actionPerformed(ActionEvent e) {
      String id;
      if(e.getSource()==idtxt)  {
        id = idtxt.getText();
        count = 0;
        if (!checkID(id)) {
	  JOptionPane.showMessageDialog(null,
                  "ไม่พบข้อมูลของรหัสสินค้า " + id +" !!!");
          idtxt.setText("");
          nametxt.setText("");
          pricetxt.setText("");
          qtxt.setText("");
          anslbl.setText("");
          idtxt.requestFocus();
        }
      }
      if(e.getSource()==qtxt)  {
	pqty = new Float(qtxt.getText());
	float total = pqty*pprice;
	DecimalFormat fm = new DecimalFormat("#,##0.00");
	String ans = "รวมเป็นเงิน " + fm.format(total) + " บาท ";
	anslbl.setText(ans);
        savebtn.requestFocus();
      }
    }
  }
  private class ButtonListener implements ActionListener  {
    @Override  
    public void actionPerformed(ActionEvent e)    {
      String i,n; float p;
      if(e.getSource()==rptbtn)  {
        pr = prod.readdata("sale.txt");
        System.out.printf("===============================\n");
        System.out.printf("        Product Sale List           \n");
        System.out.printf("id    product       price");
        System.out.printf("\n===============================\n");
        for (int j=0; j<prod.rec; j++)
          System.out.printf("%-6s%-10s%,10.2f\n",pr[j].id,pr[j].name,pr[j].price);
        System.out.printf("===============================\n");
      }

      if(e.getSource()==savebtn) {        
        i = idtxt.getText();
        n = nametxt.getText();
        p = pqty*pprice;
        prod.addData("sale.txt",i,n,p);
        idtxt.setText("");
        nametxt.setText("");
        pricetxt.setText("");
        qtxt.setText("");
        anslbl.setText("");
        idtxt.requestFocus();
        
     }
     if(e.getSource()==resetbtn)  {
       idtxt.setText("");
       nametxt.setText("");
       pricetxt.setText("");
       qtxt.setText("");
       anslbl.setText("");
       idtxt.requestFocus();
     }
   }
  }
  private boolean checkID(String id) {
    boolean found = false;
    pr = prod.readdata("product.txt");
    for (int j=0; j < prod.rec; j++) {
      if (pr[j].id.equals(id)) {
        pid = pr[j].id;
        pname = pr[j].name;
        pprice = pr[j].price;
   	DecimalFormat fm = new DecimalFormat("#,##0.00");	      
	nametxt.setText(pname);
        pricetxt.setText(fm.format(pprice));
        qtxt.requestFocus();
        found = true;
      }
    }               
    return found;
  }  
}