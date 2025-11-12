
package team3.utils;


public class ReceiptPrinter {    

    public static String centerString(String text, int width) {
        if (text == null || width <= 0) return "";
        int padding = width - text.length();
        if (padding <= 0) return text; // or truncate the text
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return String.format("%" + leftPadding + "s%s%" + rightPadding + "s", "", text, "");
    }

    // Print asterisks decorator
    static String rcptDecorator(){
      String dec = "";
      for (int i=0; i<40; i++){
        dec += "*";
      }
      return dec;
    }

    public static void main(String[] args) {
        // Header
        System.out.println(rcptDecorator());        
        String title = "Receipt Title";
        System.out.println(centerString(title, 40));        
        System.out.println(rcptDecorator());
        
        // TIN 
        // Date
        

        // Customer
        String CustName = "Bryce Alcantara";
        
        // List Orders
        String item = "Wash, Dry & Fold";
        double price = 70.00;
        
        String formattedLine = String.format("%-30s%10.2f", item, price);
        System.out.println(formattedLine);
        // Totals
        // VAT, Deduction
        
        
        // Footer
        String strFooter = "Thank you for your business.";
        
        
        
//    Output:
//    Wash, Dry & Fold                    70.00
//    %-30s: Left-justifies the string in a 30-character width.
//    %10.2f: Right-justifies the floating-point number (2 decimal places) in a 10-character width. 
//    2. Auto Center - You can create a helper method to center text by calculating the required leading and trailing spaces: 



    }
    
}
