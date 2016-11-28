import java.awt.*;
import java.awt.event.*;
import  java.io.*;
import java.lang.*;
import java.util.*;

public class deco extends Frame
  {
    String msg="";
    Menu fonts,color,point;
    MenuItem newi,openi,savei,saveasi,closei,exiti,findi,againi,repi;
    MenuItem undoi,cuti,copyi,pasi,clsi,selalli;
    MenuItem compi,interi,decompi,appleti,exploreri;
    MenuItem commandi, abouti;
    Color colo;
    String fontname="Serif";

    int pointsize=14,bol=0,itlic=0;
    Font forfont,fon,itmfont;

    static TextArea ta,tb;
    String copy,temp,clear,sel,cmd,fname,cmdr;
    static String sear,loc,rep,fin;
    int undo=0;
    static int sindex=0,eindex=0,len=0,i=0,sindex1=0,eindex1=0,eindex2=0;
    static int len1=0,op1=0,op2=0,diff=0,diff1,skflag,mflag,cflag;
    int l=0,c=0,si=0;                  
    int si1=0;
    static matchdialog mg;
    static nomatchdialog ng;
    static changedialog cg;
    boolean appletflag=false,interfaceflag=false;

    int iload,dload,fload,iloadp,aload=0,aloadp,floadp,dloadp,strcount=0;
    int excount=0;
    String strname[]=new String[100];
    String abipush[]=new String[100];
    String objpush[]=new String[100];
    String currentobj=new String();
    int astore=0;
    int abipushcount=0;
        String detext=new String();
        String fulltext=new String();
        String insideclass=new String();
        String filename=new String();
        String expfrom[]=new String[20];
        String expto[]=new String[20];
        String exptarget[]=new String[20];
        String exptype[]=new String[20];
        String toapp=new String();
        String methname=new String();
        String classname=new String();

        String towrite=new String();
        String typename=new String();
        StringTokenizer detokens;

        String intpush[]=new String[20];
        String realpush[]=new String[20];
        String doublepush[]=new String[20];

        Hashtable reserve=new Hashtable();
        Hashtable checknext=new Hashtable();
        Hashtable noinclude=new Hashtable();
        Hashtable meth=new Hashtable();
        Hashtable returnt=new Hashtable();
        Hashtable clss=new Hashtable();
        Hashtable exptable=new Hashtable();
        Hashtable extend=new Hashtable();
        Hashtable imp=new Hashtable();
        Hashtable decl=new Hashtable();
        Hashtable imple=new Hashtable();
        Hashtable appmethods=new Hashtable();


      deco(String title)
      {
            super(title);
            MenuBar mb=new MenuBar();

            setMenuBar(mb);

            forfont=new Font("Helvetica",Font.BOLD,15);

            Menu file =new Menu("File");
            file.setFont(forfont);
            
            mb.add(file);
            itmfont=new Font("Helvetica",Font.BOLD+Font.ITALIC,14);

            newi=new MenuItem("New",new MenuShortcut('N'));
            newi.setFont(itmfont);

            openi=new MenuItem("Open",new MenuShortcut('O'));
            openi.setFont(itmfont);

            savei=new MenuItem("Save",new MenuShortcut('S'));
            savei.setFont(itmfont);

            saveasi=new MenuItem("Save File As",new MenuShortcut('F'));
            saveasi.setFont(itmfont);

            closei=new MenuItem("Close",new MenuShortcut('C'));
            closei.setFont(itmfont);

            exiti=new MenuItem("Exit",new MenuShortcut('E'));
            exiti.setFont(itmfont);

            file.add(newi);
            file.add(openi);
            file.add(savei);
            file.add(saveasi);
            file.add(new MenuItem("-"));
            file.add(closei);
            file.add(exiti);

            Menu edit =new Menu("Edit");
            edit.setFont(forfont);

            mb.add(edit);
            undoi=new MenuItem("Undo");
            undoi.setFont(itmfont);

            cuti=new MenuItem("Cut",new MenuShortcut('U'));
            cuti.setFont(itmfont);

            copyi=new MenuItem("Copy",new MenuShortcut('Y'));
            copyi.setFont(itmfont);

            pasi=new MenuItem("Paste",new MenuShortcut('P'));
            pasi.setFont(itmfont);

            clsi=new MenuItem("Clear",new MenuShortcut('Z'));
            clsi.setFont(itmfont);

            selalli=new MenuItem("Select All",new MenuShortcut('A'));
            selalli.setFont(itmfont);

            edit.add(undoi);
            edit.add(new MenuItem("-"));
            edit.add(cuti);
            edit.add(copyi);
            edit.add(pasi);
            edit.add(clsi);
            edit.add(selalli);
                          
            Menu search =new Menu("Search");
            search.setFont(forfont);

            mb.add(search);
            findi=new MenuItem("Find");
            findi.setFont(itmfont);

            againi=new MenuItem("Repeat Last Find",new MenuShortcut('L'));
            againi.setFont(itmfont);

            repi=new MenuItem("Replace");
            repi.setFont(itmfont);

            search.add(findi);
            search.add(againi);
            search.add(repi);
                
            Menu execute=new Menu("Execute");
            execute.setFont(forfont);

            mb.add(execute);
            compi=new MenuItem("Compiler",new MenuShortcut('J'));
            compi.setFont(itmfont);

            interi=new MenuItem("Interpreter",new MenuShortcut('R'));
            interi.setFont(itmfont);

            decompi=new MenuItem("Decompiler",new MenuShortcut('D'));
            decompi.setFont(itmfont);

            appleti=new MenuItem("Applet Viewer",new MenuShortcut('V'));
            appleti.setFont(itmfont);

            exploreri=new MenuItem("Internet Explorer",new MenuShortcut('I'));
            exploreri.setFont(itmfont);

            execute.add(compi);
            execute.add(interi);
            execute.add(decompi);
            execute.add(appleti);
            execute.add(exploreri);

            Menu options=new Menu("Options");
            options.setFont(forfont);

            mb.add(options);
            color=new Menu("Color");
            color.setFont(itmfont);

            options.add(color);
            color.add(new MenuItem("White & Red"));
            color.add(new MenuItem("Blue & White"));
            color.add(new MenuItem("White & Brown"));
            color.add(new MenuItem("Green & Yellow"));
            color.add(new MenuItem("White & Green"));
            color.add(new MenuItem("Gray & Red"));
            color.add(new MenuItem("Black & Gray"));
            color.add(new MenuItem("Default"));

            fonts=new Menu("Fonts");
            fonts.setFont(itmfont);

            options.add(fonts);
            fonts.add(new MenuItem("Serif"));
            fonts.add(new MenuItem("Serif+Bold"));
            fonts.add(new MenuItem("Serif+Italic"));
            fonts.add(new MenuItem("Serif+Bold+Italic"));

            point=new Menu("Point Size");
            point.setFont(itmfont);

            options.add(point);
            point.add(new MenuItem("8"));
            point.add(new MenuItem("10"));
            point.add(new MenuItem("12"));
            point.add(new MenuItem("14"));
            point.add(new MenuItem("16"));
            point.add(new MenuItem("18"));
            point.add(new MenuItem("20"));
            point.add(new MenuItem("22"));
            point.add(new MenuItem("24"));
            point.add(new MenuItem("26"));
            point.add(new MenuItem("28"));
            point.add(new MenuItem("30"));
            point.add(new MenuItem("40"));
            point.add(new MenuItem("50"));
            point.add(new MenuItem("100"));

            Menu help=new Menu("Help");
            help.setFont(forfont);

            mb.add(help);
            commandi=new MenuItem("Commands");
            commandi.setFont(itmfont);

            abouti=new MenuItem("About Us");
            abouti.setFont(itmfont);

            help.add(commandi);
            help.add(abouti);

            ta=new TextArea(40,90);
            add(ta);
            tb = new TextArea();

            colo=new Color(150,150,255);
            ta.setBackground(colo);
            tb.setForeground(colo);

            colo=new Color(20,25,125);
            ta.setForeground(colo);
            tb.setBackground(colo);

      }//constructor
//------------------------------------------------------------

    public boolean handleEvent(Event e)
      {
         if(e.id==Event.WINDOW_DESTROY)
           {
              hide();
              System.exit(0);
              return true;
           }
         return super.handleEvent(e);
      }
//------------------------------------------------------------
    public boolean action(Event e,Object o)
      {
         String dir=new String();
         File f=new File("Untitled");
         FileReader fr;
         BufferedReader br;
         FileWriter fw;
         BufferedWriter bw;
         finddialog fg ;
         repdialog rg;
         matchdialog mg;
         nomatchdialog ng;
         aboutus ab;
         commands com;
         if(e.target instanceof MenuItem)
           {
              if(o.equals("New"))
               {
                 ta.setText("");
                 setTitle("Untitled");
                 tb.setVisible(false);
                 ta.reshape(4,42,640,410);
                 ta.requestFocus();
               }
           //------------------------------------------------------------
               if(o.equals("Open"))
               {
                 tb.setVisible(false);
                 ta.reshape(4,42,640,410);
                 FileDialog fd=new FileDialog(new Frame(),"Open File",FileDialog.LOAD);
                 fd.show();
                 fname=fd.getFile();
                 dir=fd.getDirectory();

                 try{
                      f=new File(dir,fname);
                      setTitle(f+"");
                      ta.setText("");

                      fr=new FileReader(f);
                      br=new BufferedReader(fr);
                      String val=new String();
                      while((val=br.readLine())!=null)
                       {
                         ta.appendText(val+"\n");
                       }
                    }catch(Exception exp){}
                 ta.requestFocus();
                 
               }//if open
           //------------------------------------------------------------
             if(o.equals("Save"))
              {
                String val;
                FileDialog fd=new FileDialog(new Frame(),"Save File",FileDialog.SAVE);
                if(getTitle().equals("Untitled"))
                  {
                     fd.show();
                     fname=fd.getFile();
                     dir=fd.getDirectory();
                     f=new File(dir,fname);
                     setTitle(dir+fname);
                  try{
                      fw=new FileWriter(f);
                      bw=new BufferedWriter(fw);
                      fw.write(ta.getText());
                      fw.close();
                    }catch (Exception exp){}
                 }
                else
                 {
                   try{
                      fw=new FileWriter(getTitle());
                      bw=new BufferedWriter(fw);
                      bw.write(ta.getText());
                      bw.close();
                   }catch (Exception exp){}
                }
                 ta.requestFocus();

              } // if save

           //------------------------------------------------------------
             if(o.equals("Save File As"))
              {
                String val;
                FileDialog fd=new FileDialog(new Frame(),"Save File As",FileDialog.SAVE);
                fd.show();
                fname=fd.getFile();
                dir=fd.getDirectory();
                setTitle(dir+fname);
                f=new File(dir,fname);
                try{
                      fw=new FileWriter(f);
                      bw=new BufferedWriter(fw);
                      fw.write(ta.getText());
                      fw.close();
                   }catch (Exception exp){}
                 ta.requestFocus();

             }//save as
           //------------------------------------------------------------
           if(o.equals("Undo"))
            {
              if(undo==1)
               {
                 ta.insert(copy,si1);
                 undo=0;
               }
              else
              if(undo==2)
               {
                 ta.replaceRange("",si,c);
                 undo=0;
               }
              if(undo==3)
               {
                 ta.setText(clear);
                 undo=0;
               }
              if(undo==4)
               {
                 ta.setText("");
                 ta.setText(sel);
                 undo=0;

               }

            }
          //------------------------------------------------------------
           if (o.equals("Cut"))
              {
                 copy=ta.getSelectedText();
                 temp=ta.getText();
                 si1=temp.indexOf(copy);
                 ta.replaceRange("",this.ta.getSelectionStart(),this.ta.getSelectionEnd());
                 undo=1;
              }
           //------------------------------------------------------------
            if (o.equals("Copy"))
              {
                copy=ta.getSelectedText();
                l=copy.length();
              }
           //------------------------------------------------------------
            if (o.equals("Paste"))
              {
                 ta.insert(copy,this.ta.getCaretPosition());
                 c =ta.getCaretPosition();
                 l = copy.length();
                 si = (c-l);
                  undo=2;
              }
          //------------------------------------------------------------
           if(o.equals("Select All"))
             {
                 sel=ta.getText();
                 this.ta.selectAll();
                 undo=4;
             }
          //------------------------------------------------------------
           if(o.equals("Clear"))
            {
                clear=ta.getText();
                ta.setText(" ");
                undo=3;
            }
          //------------------------------------------------------------
          if(o.equals("Close"))
            {
               ta.setText("");
               tb.setText("");
               setTitle("Untitled");
            }
         //------------------------------------------------------------
          if(o.equals("Find"))
            {
                fg  = new finddialog(this,"Find",true);
                fg.setSize(300,150);
                fg.setLocation(250,200);
                fg.setVisible(true);
            }
             //------------------------------------------------------------
          if(o.equals("Repeat Last Find"))
            {
              if(eindex==0)
                   {
                       fg  = new finddialog(this,"Find",true);
                       fg.setSize(300,150);
                       fg.setLocation(250,200);
                       fg.setVisible(true);
                   }
               else
                   {
                       i = eindex-1;
                        if(i<=loc.length())
                         {
                                 sindex=loc.indexOf(sear,i);
                                 eindex=sindex+len;
                                 if(sindex==-1)
                                     {
                                        mg  = new matchdialog(this,"Edit",true);
                                        mg.setSize(240,100);
                                        mg.setLocation(250,200);
                                        mg.setVisible(true);
                                     }
                                   else
                                     {
                                        ta.select(sindex,eindex);
                                     }
                          }//if
                     }//else
             }//find next
             //------------------------------------------------------------
          if(o.equals("Replace"))
            {
                diff1=0;
                rg  = new repdialog(this,"Replace",true);
                rg.setSize(300,150);
                rg.setLocation(250,200);
                rg.setVisible(true);

            }
            //------------------------------------------------------------
          if(o.equals("Compiler"))
            {
               ta.reshape(4,42,640,250);
               tb.reshape(4,292,640,160);
               add(tb);
               tb.setVisible(true);

               if(fname.endsWith(".java"))
               {
                    tb.setText(" C O M P I L I N G !!");
                    try{
                         fw=new FileWriter(getTitle());
                          bw=new BufferedWriter(fw);
                          bw.write(ta.getText());
                          bw.close();
                       }catch (Exception exp){}
                   try
                    {
                       cmd = "javac"+" "+fname;
                       Process child = Runtime.getRuntime().exec(cmd);
                       InputStream in = child.getErrorStream();
                       DataInputStream dis=new DataInputStream(in);
                       String val;
                       tb.append("\n");
                       while((val=dis.readLine())!=null)
                         tb.append(val+"\n");
                       in.close();
                       try
                        {
                          child.waitFor();
                        }
                       catch(Exception ex){}
                       if(child.exitValue()==0)
                         {
                           tb.setText("Success class file created");
                         }
                     }
                    catch(Exception ep)
                    {
                         System.out.println(e);
                    }
                  }
                 else
                  {
                      notvalid nvj=new notvalid(this,"Error Compiling",false,"Not a valid Java File !");
                      nvj.setSize(210,150);
                      nvj.setLocation(100,100);
                      nvj.setVisible(true);

                  }
            }
             //------------------------------------------------------------
          if(o.equals("Interpreter"))
            {
                String sr;
                int id;
               ta.reshape(4,42,640,250);
               tb.setVisible(true);
               tb.reshape(4,292,640,160);
               add(tb);
               tb.setText("");
               try
                {
                   id = fname.indexOf('.');
                   sr = fname.substring(0,id);
                   cmdr = "java"+" "+sr;
                   Process child = Runtime.getRuntime().exec(cmdr);
                   InputStream in = child.getInputStream();
                   DataInputStream dis=new DataInputStream(in);
                   String val;
                    tb.append("\n");
                    while((val=dis.readLine())!=null)
                            tb.append(val+"\n");
                    in.close();
                   try
                    {
                      child.waitFor();
                    }
                   catch(Exception ex){}
                   if(child.exitValue()!=0)
                     {
                       InputStream in1 = child.getErrorStream();
                       int re1;
                       while((re1=in1.read())!=-1)
                         tb.append(String.valueOf((char)re1));
                       in1.close();
                     }
                 }
                catch(Exception ep)
                 {
                   System.out.println(e);
                 }

            }
             //------------------------------------------------------------
          if(o.equals("Applet Viewer"))
            {
               ta.reshape(4,42,640,250);
               tb.setVisible(true);
               tb.reshape(4,292,640,160);
               add(tb);
               tb.setText("");
                cmd="appletviewer "+fname;
                 try
                 {
                    Process child=Runtime.getRuntime().exec(cmd);  
                    InputStream in=child.getInputStream();
                    DataInputStream dis=new DataInputStream(in);
                    String val;
                    tb.append("\n");
                    while((val=dis.readLine())!=null)
                       tb.append(val+"\n");
                     in.close();
                     try
                     {
                         child.waitFor();
                     }catch(InterruptedException p)
                     {
                        p.printStackTrace();
                        System.out.println("child exited with"+child.exitValue());
                     }
                   if(child.exitValue()!=0)
                     {
                       InputStream in1 = child.getErrorStream();
                       int re1;
                       while((re1=in1.read())!=-1)
                         tb.append(String.valueOf((char)re1));
                       in1.close();
                     }

                }
               catch(IOException z)
               {
                 // tb.appendText(z);
               }           

            }
             //------------------------------------------------------------
          if(o.equals("Internet Explorer"))
            {
               ta.reshape(4,42,640,410);
               tb.setVisible(false);
               tb.setText("");
             Runtime rn=Runtime.getRuntime();
             Process pro=null;
             if((fname.endsWith(".html")) || (fname.endsWith(".htm")))
             {
             try
               {
                 pro=rn.exec("c:\\progra~1\\iexplore.exe"+"  "+getTitle());
               }
               catch(Exception exp){
                 System.out.println(" Error :"+exp);
               }
             }
             else
             {
                      notvalid  nvc=new notvalid(this,"Error Opening Internet Explorer !",false,"Not a Valid HTML File !");
                      nvc.setSize(250,150);
                      nvc.setLocation(100,100);
                      nvc.setVisible(true);
             }
            }
             //------------------------------------------------------------
          if(o.equals("White & Red" ))
            {
             Color coloring = new Color(100,55,120);

             ta.setBackground(Color.white);
             ta.setForeground(coloring);

             tb.setForeground(Color.white);
             tb.setBackground(coloring);
            }
             //------------------------------------------------------------

          if(o.equals("Blue & White" ))
            {
             Color coloring = new Color(20,25,125);
             ta.setBackground(coloring);
             ta.setForeground(Color.white);

             tb.setForeground(coloring);
             tb.setBackground(Color.white);

            }
             //------------------------------------------------------------

          if(o.equals("White & Brown"))
            {
             Color coloring = new Color(150,55,20);
             ta.setBackground(coloring);
             ta.setForeground(Color.white);

             tb.setForeground(coloring);
             tb.setBackground(Color.white);
            }

          if(o.equals("Black & Gray"))
            {
             Color coloring = new Color(150,55,20);
             ta.setBackground(Color.gray);
             ta.setForeground(Color.black);

             tb.setForeground(Color.white);
             tb.setBackground(Color.black);
            }
             //------------------------------------------------------------

          if(o.equals("Green & Yellow" ))
            {
             Color coloring = new Color(55,100,25);
             ta.setBackground(coloring);
             ta.setForeground(Color.yellow);

             tb.setForeground(coloring);
             tb.setBackground(Color.yellow);
            }
             //------------------------------------------------------------

          if(o.equals("White & Green" ))
            {
             ta.setBackground(Color.white);
             Color coloring = new Color(55,100,25);
             ta.setForeground(coloring);

             tb.setForeground(Color.white);
             tb.setBackground(coloring);

            }
             //------------------------------------------------------------

          if(o.equals("Gray & Red" ))
            {
             ta.setBackground(Color.gray);
             ta.setForeground(Color.red);

             tb.setForeground(Color.gray);
             tb.setBackground(Color.red);
             
            }
          if(o.equals("Default"))
            {
            colo=new Color(150,150,255);
            ta.setBackground(colo);
            tb.setForeground(colo);

            colo=new Color(20,25,125);
            ta.setForeground(colo);
            tb.setBackground(colo);
            }

             //------------------------------------------------------------
          if(o.equals("Serif" ))
          {
            fontname="Serif";
            fon=new Font(fontname,Font.PLAIN,pointsize);
            ta.setFont(fon);
          }
             //------------------------------------------------------------
          if(o.equals("Serif+Bold" ))
          {
            fontname="Serif";
            fon=new Font(fontname,Font.BOLD,pointsize);
            ta.setFont(fon);
          }
             //------------------------------------------------------------
          if(o.equals("Serif+Italic" ))
          {                                               
            fontname="Serif";
            itlic=1;
            bol=0;
            fon=new Font(fontname,Font.ITALIC,pointsize);
            ta.setFont(fon);
          }
             //------------------------------------------------------------
          if(o.equals("Serif+Bold+Italic" ))
          {
            fontname="Serif";
            bol=1;
            itlic=1;
            fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
            ta.setFont(fon);
          }
             //------------------------------------------------------------

           if(o.equals("8"))
           {

             pointsize=8;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("10"))
           {
             pointsize=10;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("12"))
           {
             pointsize=12;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("14"))
           {
             pointsize=14;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("16"))
           {
             pointsize=16;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("18"))
           {
             pointsize=18;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("20"))
           {
             pointsize=20;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("22"))
           {
             pointsize=22;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("24"))
           {
             pointsize=24;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("26"))
           {
             pointsize=26;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("28"))
           {
             pointsize=28;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("30"))
           {
             pointsize=30;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("40"))
           {
             pointsize=40;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("50"))
           {
             pointsize=50;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("100"))
           {
             pointsize=100;
             if((bol==1)&&(itlic==1))
                fon=new Font(fontname,Font.BOLD+Font.ITALIC,pointsize);
             else
             if(bol==1)
                fon=new Font(fontname,Font.BOLD,pointsize);
             else
               if(itlic==1)
                fon=new Font(fontname,Font.ITALIC,pointsize);
             else
                fon=new Font(fontname,Font.PLAIN,pointsize);
             ta.setFont(fon);
           }
             //------------------------------------------------------------
           if(o.equals("About Us"))
            {
              ab=new aboutus(this,"ABOUT US",true);
              ab.setSize(400,300);
              ab.setLocation(100,100);
              ab.setVisible(true);

            } 
           if(o.equals("Commands"))
            {
              com=new commands(this,"Commands",true);
              com.setSize(400,380);
              com.setLocation(100,70);
              com.setVisible(true);

            } 
             //------------------------------------------------------------
           if(o.equals("Exit"))
             {
               dispose();
               System.exit(0);
             }
          //------------------------------------------------------------
            if(o.equals("Decompiler"))
                {
                    ta.reshape(4,42,640,250);
                    tb.reshape(4,292,640,160);
                    tb.setVisible(true);
                    add(tb);
                    if(getTitle().endsWith(".class"))

                     {
                         tb.setText(" D E C O M P I L I N G  .....");
                         int id;
                         String sr,cmdr;
                          try
                              {
                                   id = fname.indexOf('.');
                                   sr = fname.substring(0,id);
                                   cmdr = "javap"+" "+sr+" -c";
                                   Process child=Runtime.getRuntime().exec(cmdr);  
                                   InputStream in=child.getInputStream();
                                   DataInputStream dis=new DataInputStream(in);
                                   String val;
                                    tb.append("\n");
                                    while((val=dis.readLine())!=null)
                                            tb.append(val+"\n");
                                    in.close();
                                     try
                                     {
                                         child.waitFor();
                                      }catch(InterruptedException p)
                                       {
                                                p.printStackTrace();
                                                System.out.println("child exited with"+child.exitValue());
                                        }
                                        if(child.exitValue()!=0)
                                             {
                                               InputStream in1 = child.getErrorStream();
                                               int re1;
                                               while((re1=in1.read())!=-1)
                                                         tb.append(String.valueOf((char)re1));
                                               in1.close();
                                              }
                                }//try
                                catch(IOException z)
                               {
                                 // tb.appendText(z);
                               }
                   }//if class
                   else
                   {
                      notvalid  nvc=new notvalid(this,"Error Decompiling !",false,"Not a Valid Class File !");
                      nvc.setSize(210,150);
                      nvc.setLocation(100,100);
                      nvc.setVisible(true);
                  }
                  decomp();
           }//decompiler

           return true;
         }//if event
       return false;
      }//action performed
//.........................................................................
   public void decomp()
     {
         detext=new String();
         fulltext=new String();
         insideclass=new String();
         iload=1;
         dload=1;
         fload=1;
         appletflag=false;
         interfaceflag=false;

         towrite=new String();

            hash();
            tonotinclude();
            formethods();
            returntype();
            extending();
            partition();
            impo();
            implementing();
            appmeth();
            enterexcep();

            check();
     }
//.........................................................................
public void partition()
  {
       String tem=new String();

       detext=tb.getText();
       fulltext=tb.getText();
       int net=fulltext.indexOf("java.net");
       if(net!=-1)
          towrite+="import java.net.*;\n";
       StringTokenizer temp=new StringTokenizer(detext);
       while(temp.hasMoreTokens())
         {
           tem=temp.nextToken();
           if(tem.equals("}"))
           {
             insideclass+=tem;
             break;
           }
           if(tem.endsWith(";")) insideclass+="\n";
           insideclass+=tem+" ";
         }
         if(fulltext.indexOf("Exception")!=-1)
               forexceptions();
  }
//.........................................................................
public void forexceptions()
{
       int in=detext.indexOf("Exception");
       if(in!=-1)
       {
               int fromexp=detext.indexOf("type",in);
               String forexp=detext.substring(fromexp+"type".length());
               ta.appendText("\n Exceptions "+forexp);
               StringTokenizer stexp=new StringTokenizer(forexp);
               String temp;
               while(stexp.hasMoreTokens())
               {                                               
                    temp=stexp.nextToken();
                    expfrom[excount]=temp;
        
                    temp=stexp.nextToken();
                    expto[excount]=temp;
        
                    temp=stexp.nextToken();
                    exptarget[excount]=temp;
        
                    temp=stexp.nextToken();
                    temp=stexp.nextToken();
        
                    exptype[excount]=temp;
                    exptable.put(exptype[i],new String(onlyexp(exptype[i])));
                    excount++;
               }
          }//if
}//for exceptions
//.........................................................................
String onlyexp(String full)
{
   int in=full.indexOf("lang.");
   int to=full.indexOf(">");
   return full.substring(in+"lang.".length(),to);
}
//.........................................................................

public void  check()
    {
       String each=new String();
       detokens=new StringTokenizer(insideclass);
       while(detokens.hasMoreTokens())
         {
            each=detokens.nextToken();

            if(each.endsWith(".java"))
              {
                  int i=each.indexOf(".java");
                  filename=each.substring(0,i);
                  clss.put(filename,new String(filename));

              }
            else
             if(each.equals("interface"))
             {
               interfaceflag=true;
               forinterface(each,detokens);
             }
            else
              if(each.equals("class"))
              {
                 towrite+=each+" ";
                 each=detokens.nextToken();
                 towrite+=each;
                 classname=each;
                 clss.put(each,new String(each));
               }

             else
               if(returnt.containsKey(each))
               {
                   towrite +=each+" ";

                   String tosearch="Method "+each +" ";
                   each=detokens.nextToken();
                   if(each.endsWith(");"))
                   {
                        tosearch+=elicolon(each);
                        classname=" ";
                        forreturn(tosearch,each);
                   }
                   else
                   {
                     towrite+=each+"\n";
  //                   if(typename.equals("String"))
     //                   strname[strcount++]=each;
                   }
               }

              else
                if(clss.containsKey(beforebrac(each))&&(each.startsWith(beforebrac(each)+"(")))
                 {
                   if(appletflag)
                      {
                        int pu=towrite.lastIndexOf("public");
                        towrite=towrite.substring(0,pu);
                      }
                   String tosearch="Method "+elicolon(each);
                   classname=eliminate(each);
                   clss.put(eliminate(each),new String(""));
                   forreturn(tosearch,each);
                  }

              else
                if(reserve.containsKey(each))
                        towrite+=reserve.get(each);
              else
                 if(each.equals("extends"))
                    {
                         each=detokens.nextToken();
                         if(noinclude.containsKey(each))
                                    continue;
                         else
                           if(extend.containsKey(each))
                           {
                             String temp=new String();
                             temp+=imp.get(each);
                             if(each.endsWith("Applet"))
                             {
                                temp+="\n/*<applet code="+filename+".java"+" width=500 height=400> </applet>*/\n";
                                appletflag=true;
                             }
                             towrite=temp+towrite;
                              towrite+=" extends "+extend.get(each);
                            }
                          else
                          {
                            towrite+=" extends "+each;
                            clss.put(eliminate(each),new String(""));
                          }
                   }

                   if(each.equals("implements"))
                   {
                      towrite+=" implements ";
                      each=detokens.nextToken();
                      if(imple.containsKey(eliminate(each)))
                       {
                           String temp=new String();
                           temp+=imp.get(each);
                           towrite=temp+towrite;
                           towrite+=imple.get(eliminate(each));
                        }
                        else
                           towrite+=each;

                         if(each.endsWith(","))
                          {

                            while(each.indexOf("{")==-1)
                            {
                              each=detokens.nextToken();
                              if(each.indexOf("{")!=-1)
                              {
                                 towrite+="\n{\n\t";
                                 break;
                              }
                              towrite+=", ";
                              if(imple.containsKey(eliminate(each)))
                                  towrite+=imple.get(eliminate(each));
                              else
                                 towrite+=each;
                             }// while
                          }// if ,
                   }// if implements
         }//while more tokens
        //   tb.appendText("\n To write :"+towrite);
           writefile(filename);
    }
//.........................................................................
void forinterface(String each,StringTokenizer detokens)
  {
     towrite+="interface ";
     while(detokens.hasMoreTokens())
     {
         each=detokens.nextToken();
         if(exptable.containsKey(eliminate(each)))
            towrite+=exptable.get(eliminate(each))+";\n";
         else
         if(each.equals("abstract"))
           continue;
         else
         if(each.equals("extends"))
         {
                each=detokens.nextToken();
                if(extend.containsKey(each))
                   {
                      String temp=new String();
                      temp+=imp.get(each);
                      towrite=temp+towrite;
                      towrite+=" extends "+extend.get(each);
                     }
                     else
                      {
                        towrite+=" extends "+each;
                         clss.put(eliminate(each),new String(""));
                       }
                 towrite+="\n";
         }//if extends
         else
          if(each.startsWith("/*"))
          {
            while(!each.endsWith("*/"))
                each=detokens.nextToken();
          }
         else
         if(each.startsWith("{"))
           towrite+=each+"\n";
         else
         {
           towrite+=each+" ";
           if(each.endsWith(";"))
             towrite+="\n";
         }
     }//while
  }   
//.........................................................................
void forreturn(String tosearch1, String each)
{
             int in=detext.indexOf(tosearch1);
             if(in==-1)
             {
               ta.appendText(" Not Found ");
               return;
              }
                   int infrom=detext.indexOf("return",in);
                   infrom+="return".length();

                   String subs=detext.substring(in,infrom);

                   detext=fulltext.substring(0,in);
                   detext+=fulltext.substring(infrom);

                   int incon=subs.indexOf("return");
                   char befret=subs.charAt(incon-2);
                  if(befret!='4')
                   {
                        if(each.startsWith("main"))
                                meth.put(eliminate(each),new String("main(String args[])\n\t{\n\t\t"));
                        else
                          if(ifafterbrac(each))
                          {
                                String forvar=new String();
                                if(afterbrac(each).equals("int"))
                                   forvar+="in"+iload;
                                else
                                if(afterbrac(each).equals("double"))
                                   forvar+="do"+dload;
                                else
                                if(afterbrac(each).equals("float"))
                                   forvar+="fl"+fload;
                                else
                                if(afterbrac(each).equals("String"))
                                   forvar+="str"+iload;
                                meth.put(eliminate(each),new String(beforebrac(each)+"("+afterbrac(each)+" "+forvar+")\n{\n\t"));
                          }
                        else
                                meth.put(eliminate(each),new String(eliminate(each)+"()\n\t{\n\t"));
                        methname=elicolon(each);
                       if(appletflag)
                         forapplet(each,subs);
                       else
                        submethod(each,subs);
                   }

               }//for return
//........................................................................
public void forapplet(String each,String subs)
{
   StringTokenizer fora=new StringTokenizer(subs);
   String ech=new String();
   String appstr=new String();

   String currentcolor=new String();
   while(fora.hasMoreTokens())
   {
      ech=fora.nextToken();

      if(clss.containsKey(eliminate(ech)))
        continue;
      else
        if(appmethods.containsKey(ech))
          towrite+=appmethods.get(ech);
      else
      if(ech.equals("<String"))
      {
         ech=eliang(fora.nextToken());
         towrite+="\n"+appstr+" +="+ech;
/*         if(ech.endsWith(">")) continue;
         while(!ech.endsWith(">"))
            {
              ech=fora.nextToken();
              towrite+=eliminate(ech)+" ";

            }*/
          towrite+=";\n";
      }
      else
       if(ech.startsWith("showStatus"))
         towrite+="\nshowStatus("+appstr+")";
      else
        if(ech.equals("bipush"))
        {
           abipush[abipushcount++]=fora.nextToken();
        }
      else
        if(ech.equals("java.lang.String"))
       {
         ech=eliang(fora.nextToken());
         if(!ech.startsWith("valueOf"))
               appstr=ech;
      }

      else
        if(ech.startsWith("drawString"))
          towrite+="\tg.drawString("+appstr+","+abipush[abipushcount-1]+","+abipush[abipushcount-2]+");";
      else
        if(ech.equals("java.awt.Color"))
            currentcolor=eliang(fora.nextToken());
      else
        if(ech.startsWith("setForeground("))
           towrite+="\tsetForeground(Color."+currentcolor+");\n";
      else
        if(ech.startsWith("setBackground("))
           towrite+="\tsetBackground(Color."+currentcolor+");\n";
     else
       if(ech.equals("return"))
       {
          towrite+="\n\t}";
          break;
       }
   }//while
}
//........................................................................
public void submethod(String each,String subs)
  {
     String ech=new String();
     String foreli=new String();
     String prev;
     String toiadd=new String();
     String todadd=new String();
     String tofadd=new String();
     String temp=new String();
     boolean brkflag=false;
     iload=dload=fload=iloadp=aload=aloadp=floadp=dloadp=1;
     strcount=0;

     int bipushcount=1,istore=1,fstore=1,callcount=1,dstore=1,astore=1,clsscount=0;
     int popcount=0,intcount=1,realcount=1,doublecount=1,objcount=0,iaddflag=0,daddflag=0,faddflag=0;

     StringTokenizer st=new StringTokenizer(subs);

     while(st.hasMoreTokens())
     {
          ech=st.nextToken();
          callexcount(ech);
          //......................        Push ...........................
          if(ech.equals("bipush"))
          {
              ech=st.nextToken();
              intpush[intcount]=eliminate(ech);
  //            intpush[intcount]="in"+intcount;
              intcount++;
          }//if bipush

          //......................        Value ...........................
          else
          if(ech.equals("<Real"))
            {
             ech=st.nextToken();
              realpush[realcount]=eliminate(ech);
              realcount++;
            }//if real
          else
          if(ech.equals("<Double"))
            {
              ech=st.nextToken();
              doublepush[doublecount]=eliminate(ech);
              doublecount++;
            }//if d
          //......................        Store ...........................
          else
           if(ech.startsWith("fstore"))
           {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
               int il=ech.length();
               Character chr=new Character(ech.charAt(il-1));
               String sng=chr.toString();
               fstore=Integer.parseInt(sng);
               if(faddflag==1)
                  {
                      temp="fl"+fstore;
                      if(!decl.containsKey(temp))
                      {
                           towrite+="\n\tfloat fl"+fstore+" = "+tofadd+";\n";
                          decl.put(temp,new String(" "));
                       }//if !decl
                      else
                           towrite+="\n\tfl"+fstore+" = "+tofadd+";\n";

                      faddflag=0;
                      tofadd=" ";
                  }//if fadd
                  else 
                 towrite+="\n\tfloat fl"+fstore+" = "+realpush[fstore]+"f;\n";
           }//fstore
          else
           if(ech.startsWith("istore"))
              {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();

                  int il=ech.length();
                  Character chr=new Character(ech.charAt(il-1));
                  String sng=chr.toString();
                  istore=Integer.parseInt(sng);
                  if(iaddflag==1)
                  {
                      temp="in"+istore;
                      if(!decl.containsKey(temp))
                      {
                            towrite+="\n\tint in"+istore+" = "+toiadd+";\n";
                            decl.put(temp,new String(" "));
                        }
                       else
                            towrite+="\n\tin"+istore+" = "+toiadd+";\n";

                      iaddflag=0;
                      toiadd=" ";
                  }
                  else 
                  towrite+="\n\tint in"+istore+" = "+intpush[istore]+";\n";
             }

          else
           if(ech.startsWith("astore"))
              {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
                  int il=ech.length();
                  Character chr=new Character(ech.charAt(il-1));
                  String sng=chr.toString();
                  astore=Integer.parseInt(sng);

                  objpush[astore]=currentobj;
             }

          else
           if(ech.startsWith("dstore"))
              {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
                  int il=ech.length();
                  Character chr=new Character(ech.charAt(il-1));
                  String sng=chr.toString();
                  dstore=Integer.parseInt(sng);
               if(daddflag==1)
                  {
                      temp="do"+dstore;
                      if(!decl.containsKey(temp))
                      {
                                towrite+="\n\tdouble do"+dstore+" = "+todadd+";\n";
                                  decl.put(temp,new String(" "));
                       }
                      else
                                towrite+="\n\tdo"+dstore+" = "+todadd+";\n";

                      daddflag=0;
                      todadd=" ";
                  }
                  else 
                     towrite+="\n\tdouble do"+dstore+" = "+doublepush[dstore]+";\n";

             }
          //......................        Load ...........................

          else
           if(ech.startsWith("aload"))
             {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
               int il=ech.length();
               aloadp=aload;
               Character chr=new Character(ech.charAt(il-1));
               String sng=chr.toString();
               aload=Integer.parseInt(sng);
               currentobj=objpush[aload];
             }
          else
           if(ech.startsWith("iload"))
             {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();

               int il=ech.length();
               iloadp=iload;
               Character chr=new Character(ech.charAt(il-1));
               String sng=chr.toString();
               iload=Integer.parseInt(sng);
             }
          else
           if(ech.startsWith("dload"))
             {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
               int il=ech.length();
               dloadp=dload;
               Character chr=new Character(ech.charAt(il-1));
               String sng=chr.toString();
               dload=Integer.parseInt(sng);
             }
          else
           if(ech.startsWith("fload"))
             {
               int under=ech.indexOf("_");
               if(under==-1) ech=st.nextToken();
               int il=ech.length();
               floadp=fload;
               Character chr=new Character(ech.charAt(il-1));
               String sng=chr.toString();
               fload=Integer.parseInt(sng);
             }
          //......................        ADD ...........................

          else
           if(ech.startsWith("iadd"))
             {
               iaddflag=1;
               toiadd+="in"+iloadp+" + in"+iload;
             }
          else
           if(ech.startsWith("dadd"))
             {
               daddflag=1;
               todadd+="do"+dloadp+" + do"+dload;
             }
          else
           if(ech.startsWith("fadd"))
             {
               faddflag=1;
               tofadd+="fl"+floadp+" + fl"+fload;
             }
          //......................       SUB ...........................

          else
           if(ech.startsWith("isub"))
             {
               iaddflag=1;
               toiadd+="in"+iloadp+" - in"+iload;
             }
          else
           if(ech.startsWith("dsub"))
             {
               daddflag=1;
               todadd+="do"+dloadp+" - do"+dload;
             }
          else
           if(ech.startsWith("fsub"))
             {
               faddflag=1;
               tofadd+="fl"+floadp+" - fl"+fload;
             }

          //......................        MUL ...........................

          else
           if(ech.startsWith("imul"))
             {
               iaddflag=1;
               toiadd+="in"+iloadp+" * in"+iload;
             }
          else
           if(ech.startsWith("dmul"))
             {
               daddflag=1;
               todadd+="do"+dloadp+" * do"+dload;
             }
          else
           if(ech.startsWith("fmul"))
             {
               faddflag=1;
               tofadd+="fl"+floadp+" + fl"+fload;
             }
          //......................    DIV ...........................
          else
           if(ech.startsWith("idiv"))
             {
               iaddflag=1;
               toiadd+="in"+iloadp+" / in"+iload;
             }
          else
           if(ech.startsWith("ddiv"))
             {
               daddflag=1;
               todadd+="do"+dloadp+" / do"+dload;
             }

          else
           if(ech.startsWith("fdiv"))
             {
               faddflag=1;
               tofadd+="fl"+floadp+" / fl"+fload;
             }

         else
            if(ech.equals("pop"))
            {
              towrite+="\n\t}\n\t";
            }
          else
          if(ech.equals("new"))
           {
             while(!ech.endsWith(">"))
             {
                ech=st.nextToken();
                prev=eliminate(ech);
                if(clss.containsKey(prev))
                {
                  objcount++;
                  towrite+=prev+" "+prev+"obj"+objcount+" = new ";
                  currentobj=prev+"obj"+objcount;
                  break;
                }
             }//while
           }//if
         else
         if(clsscount==0)
         {
           if(meth.containsKey(eliminate(each)))
               towrite+=meth.get(eliminate(each));
           clsscount++;
         }
         else
         if(ech.equals("invokespecial"))
         {
         System.out.println(" Entering .. ");
          while(!ech.endsWith(">"))
             ech=st.nextToken();
           if(clss.containsKey(beforebrac(ech))) 
           {
               if(!eliminate(methname).equals(classname))
               {
                   if(ifafterbrac(ech))
                      {
                                 System.out.println(" Further");
                                String forvar=new String();
                                if(afterbrac(ech).equals("int"))
                                   forvar+="in"+iload;
                                else
                                if(afterbrac(ech).equals("double"))
                                   forvar+="do"+dload;
                                else
                                if(afterbrac(ech).equals("float"))
                                   forvar+="fl"+fload;
                                else
                                if(afterbrac(ech).equals("String"))
                                   forvar+="str"+iload;
                                 towrite+=beforebrac(ech)+"("+forvar+");\n\t";
                       }
                   else
                    towrite+=eliang(ech)+";\n\t\t";
                  }
           }// if contains key
       }// if invokespecial

         else
          if(ech.startsWith("<Method"))
                  forappend(ech,st);

         else
         if(ech.equals("invokevirtual"))
         {
           if(forappend(ech,st))
             {
                  while(!ech.endsWith(">"))
                     ech=st.nextToken();
                  towrite+=currentobj+"."+eliang(ech);
             }
         }
         else
         if(!clss.containsKey(eliminate(ech)))
           {
                // ta.appendText(" each "+each);
                if((meth.containsKey(ech))&&(!clss.containsKey(eliminate(each))))
                {
                        towrite+=meth.get(ech);
                }
                else
                  if(ech.startsWith("<"))
                  {
                     for(;;)
                      {
                        callexcount(ech);
                        if(ech.startsWith("<Method"))
                        {
                             forappend(ech,st);
                         }
                         else
                         if(ech.equals("<String"))
                           {
                               while(!ech.endsWith(">"))
                                {
                                   ech=st.nextToken();
                                   towrite+=eliminate(ech)+" ";
                                 }
                               towrite+=");\n";

                           }// if string
                           else
                           if(ech.endsWith(">"))
                              {
                                foreli=eliminate(ech);
                                if(meth.containsKey(foreli))
                                    towrite+=meth.get(foreli);
                                break;
                              }// ends with

                              else
                              if(ech.equals("return"))
                                     break;
                              else
                              if(meth.containsKey(eliminate(ech)))
                              {
                                  foreli=eliminate(ech);
                                    towrite+=meth.get(foreli);
                               }
                               else
                                  if(ech.equals("pop"))
                                    {
                                      towrite+="\n\t}\n\t";
                                    }
                                  //......................        Push ...........................
                                  else
                                  if(ech.equals("bipush"))
                                  {
                                      ech=st.nextToken();
                                      intpush[intcount]=eliminate(ech);
                          //            intpush[intcount]="in"+intcount;
                                      intcount++;
                                  }
                
                                  //......................        Value ...........................
                                  else
                                  if(ech.equals("<Real"))
                                    {
                                     ech=st.nextToken();
                                      realpush[realcount]=eliminate(ech);
                                      realcount++;
                                    }
                                  else
                                  if(ech.equals("<Double"))
                                    {
                                      ech=st.nextToken();
                                      doublepush[doublecount]=eliminate(ech);
                                      doublecount++;
                                    }
                                  //......................        Store ...........................
                                  else
                                   if(ech.startsWith("fstore"))
                                   {
                                       int under=ech.indexOf("_");
                                       if(under==-1) ech=st.nextToken();
                                       int il=ech.length();
                                       Character chr=new Character(ech.charAt(il-1));
                                       String sng=chr.toString();
                                       fstore=Integer.parseInt(sng);
                                       if(faddflag==1)
                                          {
                                              temp="fl"+fstore;
                                              if(!decl.containsKey(temp))
                                              {
                                                   towrite+="\n\tfloat fl"+fstore+" = "+tofadd+";\n";
                                                  decl.put(temp,new String(" "));
                                               }
                                              else
                                                   towrite+="\n\tfl"+fstore+" = "+tofadd+";\n";
                
                                              faddflag=0;
                                              tofadd=" ";
                                          }
                                          else 
                                                 towrite+="\n\tfloat fl"+fstore+" = "+realpush[fstore]+"f;\n";
                                   }
                                  else
                                     if(ech.startsWith("istore"))
                                      {
                                       int under=ech.indexOf("_");
                                       if(under==-1) ech=st.nextToken();

                                          int il=ech.length();
                                          Character chr=new Character(ech.charAt(il-1));
                                          String sng=chr.toString();
                                          istore=Integer.parseInt(sng);
                                          if(iaddflag==1)
                                          {             
                                              temp="in"+istore;
                                              if(!decl.containsKey(temp))
                                              {
                                                    towrite+="\n\tint in"+istore+" = "+toiadd+";\n";
                                                    decl.put(temp,new String(" "));
                                                }
                                               else
                                                    towrite+="\n\tin"+istore+" = "+toiadd+";\n";

                                              iaddflag=0;
                                              toiadd=" ";
                                          }
                                          else 
                                                  towrite+="\n\tint in"+istore+" = "+intpush[istore]+";\n";
                                     }
                                     else
                                       if(ech.startsWith("astore"))
                                      {
                                               int under=ech.indexOf("_");
                                               if(under==-1) ech=st.nextToken();
                                               int il=ech.length();
                                                Character chr=new Character(ech.charAt(il-1));
                                                  String sng=chr.toString();
                                                  astore=Integer.parseInt(sng);
                
                                                  objpush[astore]=currentobj;
                                     }
                                  else
                                   if(ech.startsWith("dstore"))
                                      {
                                               int under=ech.indexOf("_");
                                               if(under==-1) ech=st.nextToken();
                                                  int il=ech.length();
                                                  Character chr=new Character(ech.charAt(il-1));
                                                  String sng=chr.toString();
                                                  dstore=Integer.parseInt(sng);
                                                 if(daddflag==1)
                                                  {
                                                      temp="do"+dstore;
                                                      if(!decl.containsKey(temp))
                                                      {
                                                                towrite+="\n\tdouble do"+dstore+" = "+todadd+";\n";
                                                                  decl.put(temp,new String(" "));
                                                       }
                                                      else
                                                        towrite+="\n\tdo"+dstore+" = "+todadd+";\n";
                
                                                      daddflag=0;
                                                      todadd=" ";
                                  }
                                  else 
                                     towrite+="\n\tdouble do"+dstore+" = "+doublepush[dstore]+";\n";

                             }
                                  //......................        Load ...........................

                                  else
                                       if(ech.startsWith("aload"))
                                     {
                                       int under=ech.indexOf("_");
                                       if(under==-1) ech=st.nextToken();
                                       int il=ech.length();
                                       aloadp=aload;
                                       Character chr=new Character(ech.charAt(il-1));
                                       String sng=chr.toString();
                                       aload=Integer.parseInt(sng);
                                       currentobj=objpush[aload];
                                     }
                                  else
                                           if(ech.startsWith("iload"))
                                     {
                                               int under=ech.indexOf("_");
                                               if(under==-1) ech=st.nextToken();
                
                                               int il=ech.length();
                                               iloadp=iload;
                                               Character chr=new Character(ech.charAt(il-1));
                                               String sng=chr.toString();
                                               iload=Integer.parseInt(sng);
                                     }
                                  else
                                   if(ech.startsWith("dload"))
                                     {
                                               int under=ech.indexOf("_");
                                               if(under==-1) ech=st.nextToken();
                                               int il=ech.length();
                                               dloadp=dload;
                                               Character chr=new Character(ech.charAt(il-1));
                                               String sng=chr.toString();
                                               dload=Integer.parseInt(sng);
                                     }
                                  else
                                   if(ech.startsWith("fload"))
                                     {
                                       int under=ech.indexOf("_");
                                       if(under==-1) ech=st.nextToken();
                                       int il=ech.length();
                                       floadp=fload;
                                       Character chr=new Character(ech.charAt(il-1));
                                       String sng=chr.toString();
                                       fload=Integer.parseInt(sng);
                                     }
                                  //......................        ADD ...........................
                                 else
                                   if(ech.startsWith("iadd"))
                                     {
                                       iaddflag=1;
                                       toiadd+="in"+iloadp+" + in"+iload;
                                     }
                                  else
                                   if(ech.startsWith("dadd"))
                                     {
                                       daddflag=1;
                                       todadd+="do"+dloadp+" + do"+dload;
                                     }
                                  else
                                   if(ech.startsWith("fadd"))
                                     {
                                       faddflag=1;
                                       tofadd+="fl"+floadp+" + fl"+fload;
                                     }          
                                  //......................       SUB ...........................
                                  else
                                   if(ech.startsWith("isub"))
                                     {
                                       iaddflag=1;
                                       toiadd+="in"+iloadp+" - in"+iload;
                                     }
                                  else
                                   if(ech.startsWith("dsub"))
                                     {
                                       daddflag=1;
                                       todadd+="do"+dloadp+" - do"+dload;
                                     }
                                  else
                                   if(ech.startsWith("fsub"))
                                     {
                                       faddflag=1;
                                       tofadd+="fl"+floadp+" - fl"+fload;
                                     }
        
                                  //......................        MUL ...........................
                                  else
                                   if(ech.startsWith("imul"))
                                     {
                                       iaddflag=1;
                                       toiadd+="in"+iloadp+" * in"+iload;
                                     }
                                  else
                                   if(ech.startsWith("dmul"))
                                     {
                                               daddflag=1;
                                               todadd+="do"+dloadp+" * do"+dload;
                                     }
                                  else
                                   if(ech.startsWith("fmul"))
                                     {
                                       faddflag=1;
                                       tofadd+="fl"+floadp+" + fl"+fload;               
                                     }
                                  //......................    DIV ...........................
                                  else
                                   if(ech.startsWith("idiv"))
                                     {
                                       iaddflag=1;
                                       toiadd+="in"+iloadp+" / in"+iload;
                                     }
                                  else
                                   if(ech.startsWith("ddiv"))
                                     {
                                       daddflag=1;
                                       todadd+="do"+dloadp+" / do"+dload;
                                     }

                                  else
                                   if(ech.startsWith("fdiv"))
                                     {
                                       faddflag=1;
                                       tofadd+="fl"+floadp+" / fl"+fload;
                                     }

                                 else
                                    if(ech.equals("pop"))
                                    {
                                      towrite+="\n\t}\n\t";
                                    }
                                  else
                                  if(ech.equals("new"))
                                   {
                                     while(!ech.endsWith(">"))
                                     {
                                        ech=st.nextToken();
                                        prev=eliminate(ech);
                                        if(clss.containsKey(prev))
                                        {
                                          objcount++;
                                          towrite+=prev+" "+prev+"obj"+objcount+" = new ";
                                          currentobj=prev+"obj"+objcount;
                                          break;
                                        }
                                     }//while
                                   }//if
                                 else
                                 if(clsscount==0)
                                 {
                                   if(meth.containsKey(eliminate(each)))
                                       towrite+=meth.get(eliminate(each));
                                   clsscount++;
                                 }
         else
         if(ech.equals("invokespecial"))
         {
               System.out.println(" invlke ");

          while(!ech.endsWith(">"))
             ech=st.nextToken();
           if(clss.containsKey(beforebrac(ech))) 
           {
               if(!eliminate(methname).equals(classname))
               {
                   if(ifafterbrac(ech))
                      {
                                 System.out.println(" Further");
                                String forvar=new String();
                                if(afterbrac(ech).equals("int"))
                                   forvar+="in"+iload;
                                else
                                if(afterbrac(ech).equals("double"))
                                   forvar+="do"+dload;
                                else
                                if(afterbrac(ech).equals("float"))
                                   forvar+="fl"+fload;
                                else
                                if(afterbrac(ech).equals("String"))
                                   forvar+="str"+iload;
                                 towrite+=beforebrac(ech)+"("+forvar+");\n\t";
                       }
                   else
                    towrite+=eliang(ech)+";\n\t\t";
                  }
           }// if contains key

       }// if invokespecial
                                 else
                                   if(ech.equals("invokevirtual"))
                                     {
                                          if(forappend(ech,st))
                                          {
                                          while(!ech.endsWith(">"))
                                             ech=st.nextToken();
                                          if(meth.containsKey(eliminate(ech)))
                                               towrite+=currentobj+"."+eliang(ech)+";\n\t";
                                          }
                                     }

                              else
                              if(ech.endsWith(">"))
                              {
                                 brkflag=true;
                              }
                              else
                              if(brkflag)
                                  break;
                            //  }
                              ech=st.nextToken();
                   }
              }//else if
         }//if !
       }//while
    towrite+="\n\t}\n";
 }
//.........................................................................
void callexcount(String each)
{
  for(int i=0;i<excount;i++)
  {
   if(each.equals(expfrom[i]))
   {
      towrite+="\n\ttry{\n\t\t";
   }

   if(each.equals(expto[i]))
      towrite+="\n\t}\n\t";

   if(each.equals(exptarget[i]))
   {
      towrite+="\n\tcatch(";
      towrite+=exptable.get(exptype[i])+" exception) {\n\t";
   }
  }
}
//.........................................................................

boolean forappend(String ech,StringTokenizer st)
  {
     String afapp=new String();

            while(!ech.endsWith(">"))
               ech=st.nextToken();
            if(ech.startsWith("append("))
              {
                 int ope=ech.indexOf("(");
                 int clo=ech.indexOf(")");
                 afapp=ech.substring(ope+1,clo);
                 if(afapp.equals("int"))
                     toapp="in"+iload;
                 if(afapp.equals("float"))
                     toapp="fl"+fload;
                 if(afapp.equals("double"))
                     toapp="do"+dload;
                 int appp=towrite.lastIndexOf(");");
                 if(appp!=-1)
                 {
                    String temp=towrite.substring(0,appp);
                    temp+=" +"+toapp;
                    towrite=temp+towrite.substring(appp);
                  }
                  return true;
              }
              else
               return false;
  }  
//.........................................................................
String afterbrac(String tos)
 {
    String temp=new String();
    int len=tos.length();
    int be=tos.indexOf('(');
    int afe=tos.indexOf(')');
    temp+=tos.substring(be+1,afe);
      return temp;
 }
//.........................................................................
boolean ifafterbrac(String tos)
 {
    String temp=new String();
    int len=tos.length();
    int be=tos.indexOf('(');
    int afe=tos.indexOf(')');
    temp+=tos.substring(be+1,afe);
    if(returnt.containsKey(temp))
      return true;

    else
      return false;
 }
//.........................................................................
String beforebrac(String tos)
 {
    String temp=new String();
    int len=tos.length();
    for(int i=0;i<len;i++)
     {
       if(tos.charAt(i)=='(')
          break;
        temp+=tos.charAt(i);
     }
    return temp;
 }
//.........................................................................
String betbrac(String tos)
 {
    String temp=new String();
    int len=tos.indexOf("(");
    int len2=tos.indexOf(")");
    temp=tos.substring(len,len2);
    return temp;
 }
//.........................................................................
String eliang(String tos)
 {
    String temp=new String();
    int len=tos.length();
    for(int i=0;i<len;i++)
     {
       if((tos.charAt(i)!='>')&&(tos.charAt(i)!='<'))
         temp+=tos.charAt(i);
     }
    return temp;
 }
//.........................................................................
String elicolon(String tos)
 {
   String temp=new String();
                  int len=tos.length();
                  for(int i=0;i<len;i++)
                  {
                    if(tos.charAt(i)!=';')
                            temp+=tos.charAt(i);
                   }
      return temp;
 }
//.........................................................................

String eliminate(String tos)
 {
   String temp=new String();
                  int len=tos.length();
                  for(int i=0;i<len;i++)
                  {
                    if((tos.charAt(i)!=';')&&(tos.charAt(i)!='(')&&(tos.charAt(i)!=')')&&(tos.charAt(i)!='<')&&(tos.charAt(i)!='>')&&(tos.charAt(i)!=','))
                            temp+=tos.charAt(i);
                   }
      return temp;
 }
//.........................................................................
void appmeth()
  {
      appmethods.put("init()",new String("init()\n\t{\n"));
      appmethods.put("destroy()",new String("destroy()\n\t{\n"));
      appmethods.put("stop()",new String("stop()\n\t{\n"));
      appmethods.put("start()",new String("start()\n\t{\n"));
      appmethods.put("paint(java.awt.Graphics)",new String("paint(Graphics g)\n\t{\n"));

      appmethods.put("KeyPressed(java.awt.event.KeyEvent)",new String("KeyPressed(KeyEvent ke)\n\t{\n"));
      appmethods.put("KeyReleased(java.awt.event.KeyEvent)",new String("KeyReleased(KeyEvent ke)\n\t{\n"));
      appmethods.put("KeyTyped(java.awt.event.KeyEvent)",new String("KeyTyped(KeyEvent ke)\n\t{\n"));

      appmethods.put("mouseClicked(java.awt.event.MouseEvent)",new String("mouseCicked(MouseEvent me)\n\t{\n"));
      appmethods.put("mouseEntered(java.awt.event.MouseEvent)",new String("mouseEntered(MouseEvent me)\n\t{\n"));
      appmethods.put("mouseExited(java.awt.event.MouseEvent)",new String("mouseExited(MouseEvent me)\n\t{\n"));
      appmethods.put("mousePressed(java.awt.event.MouseEvent)",new String("mousePressed(MouseEvent me)\n\t{\n"));
      appmethods.put("mouseReleased(java.awt.event.MouseEvent)",new String("mouseReleased(MouseEvent me)\n\t{\n"));
      appmethods.put("mouseDragged(java.awt.event.MouseEvent)",new String("mouseDeaggedMouseEvent me)\n\t{\n"));
      appmethods.put("mouseMoved(java.awt.event.MouseEvent)",new String("mouseMoved(MouseEvent me)\n\t{\n"));
  }
//.........................................................................
 void hash()
   {

      reserve.put("{",new String("\n{\n\t"));
      reserve.put("public",new String("\npublic "));
      reserve.put("static",new String("static "));
   }
//.........................................................................
 void formethods()
   {
      meth.put("java.io.PrintStream",new String("\n\tSystem."));
      meth.put("out",new String("out.println("));
      meth.put("new",new String("new"));

 }
//.............................................................................
void returntype()
   {
      returnt.put("void",new String());
      returnt.put("int",new String("int"));
      returnt.put("String",new String());
      returnt.put("double",new String("double"));
      returnt.put("java.lang.String",new String("String"));
   }
//.............................................................................
void impo()
   {
      imp.put("java.awt.Frame",new String("\nimport java.awt.*;\n"));
      imp.put("java.applet.Applet",new String("\nimport java.awt.*;\nimport java.applet.*;\n"));
      imp.put("java.rmi.Remote",new String("\nimport java.rmi.*;\n"));
   }
//.............................................................................
 void tonotinclude()
   {
      noinclude.put("java.lang.Object",new String(""));
   }
//.............................................................................
void enterexcep()
 {
   exptable.put("java.rmi.RemoteException",new String("RemoteException"));
 }

//.............................................................................
 void implementing()
   {
      imple.put("java.awt.event.KeyListener",new String("KeyListener"));
      imple.put("java.awt.event.MouseListener",new String("MouseListener"));
      imple.put("java.awt.event.MouseMotionListener",new String("MouseMotionListener"));
    }
//.............................................................................
 void extending()
   {
      extend.put("java.awt.Frame",new String("Frame"));
      extend.put("java.lang.String",new String("String"));
      extend.put("java.rmi.Remote",new String("Remote"));
      extend.put("java.applet.Applet",new String("Applet"));
   }
//.............................................................................
public void writefile(String filename)
{
       if(!interfaceflag)
               towrite+="\n}";
       String temp=new String();
       try
       {
               File fil=new File(filename+".java");
               if(fil.exists())
               {
                 FileReader fr=new FileReader(fil);
                 BufferedReader br=new BufferedReader(fr);
                 String val=new String();
                 while((val=br.readLine())!=null)
                   {
                       temp+=val+"\n";
                   }
                }
               towrite=temp+towrite;
               FileWriter fw=new FileWriter(fil);
               fw.write(towrite);
               fw.close();
       }
       catch(Exception exp)
       {}
}//write file


class wind extends WindowAdapter
 {
   public void windowClosing(WindowEvent e)
    {
     Dialog f1=(Dialog)e.getSource();
     f1.setVisible(false);
     f1.dispose();
     }
  }
//.........................................................................
class commands extends Dialog
  {
    Button toclose;
    TextArea helpta;
    Frame to;
    commands(Frame f,String title,boolean b)
    {
      super(f,title,true);
      setLayout(null);
      this.to=f;
      addWindowListener(new wind());
      Font forfont=new Font("TimesNewRoman",Font.PLAIN,12);
      setFont(forfont);  
      setBackground(Color.white);
      helpta=new TextArea();
      add(helpta);
      helpta.setEditable(false);
      helpta.reshape(4,24,396,300);
      toclose=new Button("OK");
      toclose.reshape(170,335,30,30);
      add(toclose);
      helpta.appendText(" Shortcut For MenuItems \n");
      helpta.appendText(" ---------------------------------- \n");
      helpta.appendText("    New   \t\t --> Ctrl+N    \t  -   To Create a New File \n");
      helpta.appendText("    Open  \t\t --> Ctrl+O    \t  -   To Open a File \n"); 
      helpta.appendText("    Save  \t\t --> Ctrl+S    \t  -   To Save a File \n"); 
      helpta.appendText("    Save File As\t --> Ctrl+F\t\t  -   To Save a file in a New name \n"); 
      helpta.appendText("    Close \t\t --> Ctrl+C    \t  -   To Close a File \n");
      helpta.appendText("    Exit  \t\t --> Ctrl+E    \t  -   To Exit File \n");
      helpta.appendText("    Cut   \t\t --> Ctrl+U    \t  -   To Cut a Text \n"); 
      helpta.appendText("    Copy  \t\t --> Ctrl+Y  \t  -   To Copy a Text \n");
      helpta.appendText("    Paste \t\t --> Ctrl+P  \t  -   To Paste a Text(s)\n" );
      helpta.appendText("    Clear \t\t --> Ctrl+Z  \t  -   To Clear a TextFile \n");
      helpta.appendText("    Select All\t --> Ctrl+A\t\t  -   To Select All Text \n"); 
      helpta.appendText("    Find Next \t --> Ctrl+L\t\t  -   To Find successive Texts \n"); 
      helpta.appendText("    Compiler \t\t --> Ctrl+J  \t  -   To Compile Java File \n"); 
      helpta.appendText("    Interpreter  \t\t --> Ctrl+R  \t  -   To Run Java File \n");
      helpta.appendText("    Decompiler  \t\t --> Ctrl+D  \t  -   To Build a Java File from a class File \n");
      helpta.appendText("    Appletviewer\t --> Ctrl+W\t  -   To Show the applet \n");
      helpta.appendText("    Internet Explorer\t --> Ctrl+I\t\t  -   To Execute html files \n");

   }
   public boolean action(Event ev,Object o)
    {
      if(ev.target instanceof Button)
       {
        if(o.equals("OK"))
          {
            dispose();
          }
         return true;
        }
      return false;
   }
} 
//.........................................................................
class aboutus extends Dialog
  {
    Label lus1,lus2,lide,lver,ldes;
    Button toclose;
    Frame to;
    aboutus(Frame f,String title,boolean b)
    {
      super(f,title,true);
      setLayout(null);
      this.to=f;
      addWindowListener(new wind());
      Font forfont=new Font("Serif",Font.BOLD+Font.ITALIC,20);
      setFont(forfont);
      setBackground(Color.white);
      lide=new Label("Java Decompiler");
      lide.reshape(35,25,400,30);
      add(lide);

      lver=new Label("SHARATH     SRINIDHI URS");
      lver.reshape(20,140,350,30);
      add(lver);

      ldes=new Label("Designed By");
      ldes.reshape(115,110,200,30);
      add(ldes);

      lus1=new Label("");
      lus1.reshape(20,140,350,30);
      lus2= new Label ("");
      lus2.reshape(20,145,350,30);
      add(lus1);add(lus2);

      toclose=new Button("Close");
      toclose.reshape(160,210,60,30);
      add(toclose);
   }
   public boolean action(Event ev,Object o)
    {
      if(ev.target instanceof Button)
       {
        if(o.equals("Close"))
          {
            dispose();
          }
         return true;
        }
      return false;
   }
}
//.........................................................................
class matchdialog extends Dialog
  {
       Label ml;
    Button mb1;
    matchdialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        addWindowListener(new wind());
        ml = new Label("No more matches found.");
        ml.reshape(50,30,200,30);
        mb1 = new Button("OK");
        mb1.reshape(100,70,50,20);
        ml.setBackground(Color.lightGray);
        add(ml);add(mb1);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("OK"))
                 {
                   dispose();
                 }
             return true;
          }
          return false;
      }
  }// match
//.........................................................................
class nomatchdialog extends Dialog
  {
       Label ml;
    Button mb1;
    nomatchdialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        addWindowListener(new wind());
        ml = new Label("Edit was unable to find a match");
        ml.reshape(40,30,200,30);
        mb1 = new Button("OK");
        mb1.reshape(100,70,50,20);
        ml.setBackground(Color.lightGray);
        add(ml);add(mb1);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("OK"))
                 {
                   dispose();
                 }
             return true;
          }
          return false;
      }
  }// nomatch
//.........................................................................
class finddialog extends Dialog
  {
    Label fl;
    TextField ft1;
    Button fb1,fb2;
    matchdialog mg;
    Frame f3;
    finddialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        this.f3=f;
        addWindowListener(new wind());
        fl = new Label("Find What");
        fl.setBackground(Color.lightGray);
        ft1 = new TextField(25);
        fb1 = new Button("OK");
        fb2 = new Button("Cancel");
        add(fl);add(ft1);add(fb1);add(fb2);
        fl.reshape(30,50,70,25);
        ft1.reshape(110,50,150,25);
        fb1.reshape(80,100,55,20);
        fb2.reshape(150,100,60,20);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("OK"))
                 {
                    fin=ft1.getText();
                    deco.calc(fin);
                    if(mflag==1){
                      ng  = new nomatchdialog(f3,"Edit",true);
                      ng.setSize(240,100);
                      ng.setLocation(280,230);
                      ng.setVisible(true);
                      mflag=0;}
                    else
                      this.setVisible(false);
                 }
                if(o.equals("Cancel"))
                 {
                   dispose();
                 }
             return true;
          }
          return false;
      }
  }// finddialog
//.........................................................................
public static void calc(String fin1)
 {
    sear = fin1;
    len=sear.length();
    loc = ta.getText();
    sindex=loc.indexOf(sear);
    eindex=sindex+len;
    eindex1=eindex;
    if(sindex==-1)
       mflag=1;
     else
       ta.select(sindex,eindex);
 }
//.........................................................................
class changedialog extends Dialog
  {
    Label ml;
    Button mb1;
    changedialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        addWindowListener(new wind());
        ml = new Label("Change complete.");
        ml.reshape(70,30,200,30);
        mb1 = new Button("OK");
        mb1.reshape(100,70,50,20);
        ml.setBackground(Color.lightGray);
        add(ml);add(mb1);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("OK"))
                 {
                   dispose();
                 }
             return true;
          }
          return false;
      }
  }// match
//.........................................................................
class repdialog extends Dialog
  {
    Label rl1,rl2;
    TextField rt1,rt2;
    Button rb1,rb2;
    rep1dialog rg1;
    String fin;
    Frame to;
    repdialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        this.to = f;
        addWindowListener(new wind());
        rl1 = new Label("Find What");
        rl1.reshape(30,25,70,25);
        rl1.setBackground(Color.lightGray);
        rt1 = new TextField(25);
        rt1.reshape(110,25,150,25);
        rl2 = new Label("Replace with");
        rl2.reshape(30,65,70,25);
        rl2.setBackground(Color.lightGray);
        rt2 = new TextField(25);
        rt2.reshape(110,65,150,25);
        rb1 = new Button("Replace");
        rb1.reshape(90,115,55,20);
        rb2 = new Button("Cancel");
        rb2.reshape(160,115,60,20);
        add(rl1);add(rt1);add(rl2);add(rt2);add(rb1);add(rb2);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("Replace"))
                 {
                    fin=rt1.getText();
                    rep=rt2.getText();
                    len1=rep.length();
                    deco.calc(fin);
                    if(mflag==1){
                      ng  = new nomatchdialog(to,"Edit",true);
                      ng.setSize(240,100);
                      ng.setLocation(280,230);
                      ng.setVisible(true);
                      mflag=0;}
                    else{
                        this.setVisible(false);
                        rg1 = new rep1dialog(to,"Replace",true);
                        rg1.setSize(300,150);
                        rg1.setLocation(250,200);
                        rg1.setVisible(true);}
                       }
                if(o.equals("Cancel"))
                 {
                   dispose();
                 }
             return true;
          }
          return false;
      }
}//rep
//.........................................................................
class rep1dialog extends Dialog
  {
    Label r1l1;
    Button r1b1,r1b2,r1b3;
    repdialog rd;
    Frame f2;
    matchdialog mg;
    rep1dialog(Frame f,String title,boolean b)
     {
        super(f,title,true);
        setLayout(null);
        this.f2=f;
        addWindowListener(new wind());
        r1l1 = new Label("Replace this occurance?");
        r1l1.reshape(70,40,150,20);
        r1b1 = new Button("Replace");
        r1b1.reshape(40,100,70,20);
        r1b2 = new Button("Skip");
        r1b2.reshape(120,100,40,20);
        r1b3 = new Button("Cancel");
        r1b3.reshape(170,100,60,20);
        r1l1.setBackground(Color.lightGray);
        add(r1l1);add(r1b1); add(r1b2);add(r1b3);
     }
    public boolean action(Event e,Object o)
      {
         if(e.target instanceof Button)
           {
                if(o.equals("Replace"))
                 {
                   deco.differ(len,len1);
                   if(cflag==1){
                      this.setVisible(false);
                      cg  = new changedialog(f2,"Edit",true);
                      cg.setSize(240,100);
//                      mg.setSize(300,150);
                      cg.setLocation(250,200);
                      cg.setVisible(true);
                      cflag=0;}
                 }//replace
                if(o.equals("Skip"))
                 {
                   deco.differ1(len,len1);
                   if(cflag==1){
                      this.setVisible(false);
                      cg  = new changedialog(f2,"Edit",true);
                      cg.setSize(240,100);
//                      mg.setSize(300,150);
                      cg.setLocation(250,200);
                      cg.setVisible(true);
                      cflag=0;}
                 }
                if(o.equals("Cancel"))
                 {
                   dispose();
                 }
             return true;
           }
         return false;
       }
}//rep1
//.........................................................................
public static void differ(int leng,int leng1)
 {
    op1 = leng;
    op2 = leng1;
         if(op1==op2)
           {
                   skflag=1;
                   ta.replaceRange("",sindex,eindex);
                   ta.insert(rep,sindex);
                   i = eindex-1;
                   if(i<=loc.length())
                      {
                          sindex=loc.indexOf(sear,i);
                          eindex=sindex+len;
                         // System.out.println("esindex="+sindex);
                          //System.out.println("eeindex="+eindex);
                          if(sindex==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                        }
             }
         else if(op1>op2)
              {
                   skflag=1;
                   diff = op1-op2;
                   ta.replaceRange("",sindex,eindex);
                   ta.insert(rep,sindex);
                   i = eindex1-1;
                  // System.out.println("i="+i);     
                   if(i<=loc.length())
                      {
                          diff1=diff1+diff;
                          sindex=loc.indexOf(sear,i);
                         // System.out.println("sindex="+sindex);
                          sindex1=sindex;
                          eindex1=sindex+len;
                          sindex=sindex-diff1;
                          eindex=sindex+len;
                         // System.out.println("lsindex="+sindex);
                          //System.out.println("leindex="+eindex);
                          if(sindex1==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                      }
              }
            else if(op2>op1)
              {
                   skflag=1;
                   diff = op2-op1;
                   ta.replaceRange("",sindex,eindex);
                   ta.insert(rep,sindex);
                   i = eindex1-1;
                   if(i<=loc.length())
                      {
                          diff1=diff1+diff;
                          sindex=loc.indexOf(sear,i);
                          sindex1=sindex;
                          eindex1=sindex+len;
                          sindex=sindex+diff1;
                          eindex=sindex+len;
                         // System.out.println("g1sindex="+sindex1);
                         // System.out.println("gsindex="+sindex);
                          //System.out.println("geindex="+eindex);
                           if(sindex1==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                      }
              }//elseif
 }//differ
//.........................................................................
public static void differ1(int leng,int leng1)
 {
    op1 = leng;
    op2 = leng1;
         if(op1==op2)
           {
                   i = eindex-1;
                   if(i<=loc.length())
                      {
                          sindex=loc.indexOf(sear,i);
                          eindex=sindex+len;
                         // System.out.println("esindex="+sindex);
                         // System.out.println("eeindex="+eindex);
                           if(sindex==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                        }
             }
         else if(op1>op2)
              {
                  // System.out.println("flag="+skflag);
                   diff = op1-op2;
                   i = eindex1-1;
                   if(i<=loc.length())
                      {
                         sindex=loc.indexOf(sear,i);
                         sindex1=sindex;
                         eindex1=sindex+len;
                         // System.out.println("lsindex="+sindex);
                         // System.out.println("leindex="+eindex);
                         // System.out.println("diff1="+diff1);
                              if(skflag==1){
                              sindex=sindex-diff1;
                              eindex=sindex+len;}
                           if(sindex1==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                      }
              }
            else if(op2>op1)
              {
                   diff = op2-op1;
                   i = eindex-1;
                   if(i<=loc.length())
                      {
                          sindex=loc.indexOf(sear,i);
                          sindex1=sindex;
                          eindex1=sindex+len;
                         // System.out.println("gsindex="+sindex);
                         // System.out.println("geindex="+eindex);
                              if(skflag==1){
                              sindex=sindex+diff1;
                              eindex=sindex+len;}
                           if(sindex1==-1)
                            {
                             cflag=1;
                            }
                          else
                           {
                              ta.select(sindex,eindex);
                           }
                      }
              }//elseif
 }//differ1
 
//.................................................................................
class notvalid extends Dialog
  {
    Label lus1,lus2,lide,lver,ldes;
    Button toclose;
    Frame to;
    notvalid(Frame f,String title,boolean b,String msg)
    {
      super(f,title,false);
      setLayout(null);
      this.to=f;
      Font forfont=new Font("Serif",Font.BOLD,20);
      setFont(forfont);
      setBackground(Color.white);
      lide=new Label(msg);
      lide.reshape(10,25,200,30);
      add(lide);

      toclose=new Button("Close");
      toclose.reshape(60,70,60,30);
      add(toclose);
   }

   public boolean action(Event ev,Object o)
    {
      if(ev.target instanceof Button)
       {
        if(o.equals("Close"))
            dispose();
         return true;
        }
      return false;
   }//action
 }//not valid class
//.........................................................................
public static void main(String args[])
    {
               deco e=new deco("Untitled");
             e.resize(640,450);
           e.setVisible(true);


    }
  }

 
