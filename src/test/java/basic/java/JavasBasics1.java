package basic.java;

public class JavasBasics1 {
   // Class you can have Fields, parameter, variables, constructions, //methods
   static int i=15;
   public JavasBasics1()
   {

   }

   public static void main (String[] args)
   {
      MySubClass mySubClass = new MySubClass();
      String returnedValue= mySubClass.concatinateStrings("KHEMLALL","MANGAL");
      System.out.println(returnedValue);


   }
}
