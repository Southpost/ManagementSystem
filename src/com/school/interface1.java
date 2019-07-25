package com.school;

import java.io.*;
import java.util.*;


//注意与C++ 的区别
//泛型数组列表的使用（放在函数的外面）


class interface1 {
    private ArrayList<person> pe=new ArrayList<>();   //更改了ArrayList-还要导入用：Alt+Enter 快捷键

    public interface1() throws IOException {
        ReadFile();
    }

    public void Display() {
        System.out.println();
        System.out.println("*************0.退出************");
        System.out.println("***********1.添加信息**********");
        System.out.println("***********2.查询信息**********");
        System.out.println("***********3.删除信息**********");
        System.out.println("***********4.显示信息**********");
    }

    public void Run() throws IOException {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            Display();
            System.out.println("请您输入选项：");
            choice = in.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    Insert();
                    break;
                case 2:
                    Search();
                    break;
                case 3:
                    Delete();
                    break;
                case 4:
                    Output();
                    break;
            }
        } while (choice!=0);     //区别
        Save();
    }
    public void Search(){     //还未完成-数据集的转化
        Scanner in=new Scanner(System.in);
        String number;
        System.out.println("请输入要查询的编号：");
        number=in.nextLine();
        for(person s:pe){
            if(s.getNumber().equals(number)){      //TODO 字符串相等时不能用（==），应该要用a.equals(b)   !!!
                PrintOne(s);
                return;
            }
        }
        System.out.print("对不起了您，没有此人信息!!!");


    }
    public void Insert() throws IOException {
        Scanner in=new Scanner(System.in);
        String name;
        String number;
        char sex;
        int year;
        int momth;
        int day;
        String speciality="";
        String researchTopic="";
        String academicTitle="";
        int type;
        char ch='n';
        do {
            System.out.println("你输入的人员类型（1-学生  2-研究生  3-在职研究生  4-教师）：");
            type = in.nextInt();
            while (type != 1 && type != 2 && type != 3 && type != 4) {
                System.out.println("输入错误，请重新输入：");
                type = in.nextInt();
            }
            in.nextLine();       //吃掉一个换行，以免下面被吃掉 todo
            System.out.println("姓名：");
            name = in.nextLine();
            System.out.println("编号：");
            number = in.nextLine();
            System.out.println("性别（m/f）：");
            sex = (char)System.in.read();
            System.out.println("出生日期（年份 月份 日期）：");
            year = in.nextInt();
            momth = in.nextInt();
            day = in.nextInt();
            //TODO  每次使用in.next**()时会吃掉换行，所以在顶部加上下面这句话！！！
            in.nextLine();
            if (type == 1) {
                System.out.println("专业：");
                speciality = in.nextLine();
                academicTitle = "";
            } else if (type == 2) {
                System.out.println("专业：");
                speciality = in.nextLine();
                System.out.println("课题：");
                researchTopic = in.nextLine();
                academicTitle = "";
            } else if (type == 3) {
                System.out.println("专业：");
                speciality = in.nextLine();
                System.out.println("课题：");
                researchTopic = in.nextLine();
                System.out.println("职称：");
                academicTitle = "";
            } else {
                System.out.println("职称：");
                academicTitle = in.nextLine();
                speciality = "";
                researchTopic = "";
            }
            person ob = new person(name, number, sex, year,momth,day, speciality, researchTopic, academicTitle, type);
            //todo 要在访问对象前创建一个它的实例(跳到定义它的地方+new ArrayList<>())-
            // 错误提示--Exception in thread "main" java.lang.NullPointerException
            pe.add(ob);      //导入规范,记得new Set（y,m,d）;
            System.out.print("继续输入？（y/n）");
            ch = (char) System.in.read();   //记住单个字符
        }while (ch=='y');
    }
    //问题一：PrintOne的实现（OK）
    public void PrintOne(person p){
        System.out.println("姓名     编号     性别     出生年月     专业     课题    职称    人员类型");
        System.out.print(p.getName());
        System.out.print('\t');
        System.out.print("   ");
        System.out.print(p.getNumber());
        System.out.print("   ");
        System.out.print('\t');
        System.out.print("  ");
        if(p.getSex()=='m')
            System.out.print("男  ");
        else
            System.out.print("女  ");
        //Date bi=new Date();                 //todo
        System.out.print(p.birthday.getYear());
        System.out.print("-");
        System.out.print(p.getBirth().getMonth());
        System.out.print("-");
        System.out.print(p.birthday.getDay());
        System.out.print('\t');
        System.out.print("   ");
        String sp=p.getSpeciality();
        if("".equals(sp)) {
            System.out.print("\t");
        }
        else {
            System.out.print(sp);
            System.out.print(" ");
        }
        String re=p.getResearchTopic();
        if("".equals(re))
            System.out.print("\t");
        else
            System.out.printf(re,"\t");
        String ac=p.getAcademicTopic();
        if("".equals(ac))
            System.out.print("\t");   //println--默认加换行
        else
            System.out.printf(ac,"\t");
        int type=p.getType();
        if(type==1)
        System.out.print("学生");
        else if(type==2)
            System.out.print("研究生");
        else if(type==3)
            System.out.print("在职研究生");
        else if(type==4)
            System.out.print("教师");
    }





    //问题二：Output的实现（OK）
    public void Output(){
           for(person s:pe){
               PrintOne(s);
           }
    }
    //问题三：Delete的核心部分（OK）
    public void Delete(){
        Scanner in=new Scanner(System.in);
        String number;
        System.out.println("请输入要删除的编号：");
        number=in.nextLine();
        //这里要注意两点：1.p.get(i) ;2.不能在for each遍历的时候删除集合，只能用倒叙删除的方法
        for(int i=pe.size()-1;i>=0;i--)
        {
            if(pe.get(i).getNumber().equals(number)){
                pe.remove(pe.get(i));
                System.out.println("删除成功！");
            }
        }
        return;
    }
     //问题四：ReadFile（OK）  Todo   java的序列化
//    public void ReadFile() throws IOException {
//        Scanner in = new Scanner(Paths.get("D;\\record.txt"), "UTF-8");
//        try {
//            new Scanner(Paths.get("D;\\record.txt"), "UTF-8");
//        } catch (Exception e) {
//            System.out.println("读取错误！！！");
//            System.exit(0);
//        }
//        String name = "";
//        String number = "";
//        char sex = '0';
//        int y = 0;
//        int m = 0;
//        int d = 0;
//        String speciality =" ";
//        String researchTopic="";
//        String academicTitle="";
//        int type = 0;
//        for(person s:pe) {
//            System.out.println(s);
//        }
//        //TODO  Java的文件的序列化
//        FileInputStream fis = new FileInputStream("D:\\record.txt");
//        while((fis.read()) != -1) {
//            if (speciality.equals("-"))
//                speciality = "";
//            if (researchTopic.equals(""))
//                researchTopic = "";
//            if (academicTitle.equals("-"))
//                academicTitle = "";
//            person ob = new person(name, number, sex,y,m,d, speciality, researchTopic, academicTitle, type);
//            pe.add(ob);
//            for(person s:pe) {
//                System.out.println(s);
//            }
//        }
//        fis.close();
//         return;
//    }
    public void ReadFile() throws IOException {
        try
        {
            FileInputStream fileIn = new FileInputStream("D:\\record.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Person class not found");
            c.printStackTrace();
            return;
        }
        String name = "";
        String number = "";
        char sex = '0';
        int y = 0;
        int m = 0;
        int d = 0;
        String speciality = " ";
        String researchTopic = "";
        String academicTitle = "";
        int type = 0;
        for (person s : pe) {
            System.out.println(s);
        }
        FileInputStream fis = new FileInputStream("D:\\record.txt");
        while ((fis.read()) != -1) {
            if (speciality.equals("-"))
                speciality = "";
            if (researchTopic.equals(""))
                researchTopic = "";
            if (academicTitle.equals("-"))
                academicTitle = "";
            person ob = new person(name, number, sex, y, m, d, speciality, researchTopic, academicTitle, type);
            pe.add(ob);
            for (person s : pe) {
                System.out.println(s);
            }
        }
        fis.close();
        return;
    }


    //问题五：Save（OK）
    public void Save() throws FileNotFoundException, UnsupportedEncodingException {
         PrintWriter out=new PrintWriter("D;\\record.txt","UTF-8");
        try {
            new PrintWriter("D;\\record.txt","UTF-8");
        } catch (Exception e) {
            System.out.println("打开文件错误！！！");
           return;
        }
        for(person s:pe) {
            System.out.printf(s.getName(), ' ', s.getNumber(), ' ', s.getSex(), ' ');
            Date bi = new Date();  //todo
            System.out.println(bi.getYear());
            System.out.println(' ');
            System.out.println(bi.getMonth());
            System.out.println(' ');
            System.out.println(bi.getDay());
            System.out.println(' ');
            String sp = s.getSpeciality();
            if (sp.equals("")) {
                System.out.print("-");
                System.out.print(" ");
            } else {
                System.out.print(sp);
                System.out.print(" ");
            }
            String re = s.getResearchTopic();
            if (re.equals("")) {
                System.out.print("-");
                System.out.print(" ");
            }
            else{
                System.out.print(re);
                System.out.print(" ");
            }
            String ac=s.getAcademicTopic();
            if(ac.equals("")) {
                System.out.print("-");
                System.out.print(" ");
            }
            else {
                System.out.print(ac);
                System.out.print(" ");
            }
            System.out.println(s.getType());
        }
        out.close();
        return;
    }
}
