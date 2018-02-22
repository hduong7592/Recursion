import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Project 4 - Recursion...Modify code
 *
 * @author Cody Narber & (Hieu Duong)
 */
public class Recursion {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("%nEnter a binary number: ");
        String input = scn.nextLine();
        System.out.printf("%n%-10s - using checkString results in: %6d%n",input,checkString(input));
        System.out.printf("%-10s - using checkString2 results in: %5d%n",input,checkString2(input));

        System.out.printf("%n%-10s - using checkStringNR results in: %4d%n",input,checkStringNR(input));
        System.out.printf("%-10s - using checkStringNR2 results in: %3d%n",input,checkStringNR2(input));
        System.out.println("\n--------------------------------");

        int num1 = promptForPosInteger(scn);
        int num2 = promptForPosInteger(scn);
        System.out.printf("%nThe GCD between %d and %d is %8d%n", num1, num2, GCD(num1,num2));
        System.out.printf("The GCD(NR) between %d and %d is %4d%n%n", num1, num2, GCD_NR(num1,num2));
    }

    /**
     * My method for getting an integer, enforcing the fact it is a positve integer.
     */
    public static int promptForPosInteger(Scanner s){
        System.out.print("Enter a positive integer: " );
        String entered = s.nextLine();
        while(!entered.matches("\\d+")){
            System.out.print("That is not a positve integer, try again: " );
            entered = s.nextLine();
        }
        return Integer.parseInt(entered);
    }



    /**********************************************************************
     * A recursive method that does something... TODO: fill in details
     * Preconditions: is a string and contain 1 and 0 only. It does not break the code if the string does not only contain 0 and 1
     * Postconditions: string size reduced to 0, sum of 1 count and 0 count are calculated
     * Base Case: N==0
     * Smaller Case: It is progress toward N==0
     * General Case: checkString(s.substring(1))
     */
    public static int checkString(String s){
        int v = 0;
        if(s.length()==0) return 0;
        if(s.charAt(0)=='1'){
            s+="00";
            v+=checkString(s.substring(1));
        }else {
            v++;
            v+=checkString(s.substring(1));
        }
        return v;
    }

    /**
     * TODO: Write non-recursive code that returns the same result as above
     */
    public static int checkStringNR(String s){
        //TODO: modify method body so you get the same result as above recursive method
        //Basically this method will replace 1 with 00
        //If char is 0 then keep it
        //Finally count all 0
        //In this case, the formula is: sum of 0 + 2(sum of 1)

        String newString = "";
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            //System.out.println(s.charAt(i));
            char input = s.charAt(i);
            if(input == '1') {
                newString+="00";
                //2 00 then add 2 to count
                count +=2;
            }
            else {
                newString += "0";
                //1 0 then add 1 to count
                count+=1;
            }
        }

        //System.out.println("New string"+newString);
        return count;
    }



    /**********************************************************************
     * A recursive method that does something... TODO: fill in details
     * Preconditions: is a string and contain 1 and 0 only
     * Postconditions: string size reduced to 0
     * Base Case: N==0
     * Smaller Case: it is progress toward N==0
     * General Case: checkString2(s.substring(1));
     */
    public static int checkString2(String s){
        int v=0;
        if(s.length()==0) return 0;
        if(s.charAt(0)=='1'){
            v+=checkString2(s.substring(1))+1;
        } else {
            v+=1;
        }
        v+=checkString2(s.substring(1));
        return v;
    }

    /**
     * TODO: Write non-recursive code that returns the same result as above
     */
    public static int checkStringNR2(String s){
        //TODO: modify method body so you get the same result as above recursive method
        //In this case, we have to count separate 1 and 0
        //For 1: the formula is (2^n)-1
        //We can see the pattern: count1(1) = 1; count1(2) = 3; count1(3) = 7; count1(4)=15, count1(5)=31 ...and so on
        //For 0: the formula is a bit tricky
        //For every 0 in the string, it will be 2 to the power of number of 1 count
        //For example: 01: there is no 1 in front of 0, so the count is 2^0, total is 1 count
        //10: there is a 1 in front of 0, so the count of 0 is 2^1, total is 2 count
        //1010: for the first 0, there is 1 in front of it, so the count is 2^1, is 2 count
        //for the second 0, there is 2 number 1 in front of it, so the count is 2^2, is 4 count
        //Total 0 count is 2 + 4 = 6; total count of 1 in this case is (2 to the power of 2)-1 = 3; total count is 6 + 3 = 9

        int count1 = 0;
        int count0 = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='1'){
                //Count number of 1 in the string
                count1++;
            }
            else{
                //Count number of 2 by sum of 2 power to the number of 1 in front of it
                count0+= Math.pow(2,count1);
            }
        }
        //Get total count of 1
        int totalCount1 = (int)Math.pow(2, count1) - 1;

        //Total is sum of count of 1 and count of 0
        int total = count0+totalCount1;

        return total;
    }



    /**********************************************************************
     * A recursive method that computes the greatest common divisor
     */
    public static int GCD(int a, int b){
        //TODO: modify method body so you compute the GCD using euclid's algorithm

        //If a is 0 then the greatest common divisor is b
        if (a==0){
            //System.out.println("A: "+a+",B: "+b);
            return b;
        }
        //If b is 0 then the greatest common divisor is a
        else if(b==0){
            return a;
        }
        //If not 2 cases above, compute GCD
        else{
            //If a larger or equal b, compute GCD
            if(a>=b){
                //System.out.println("a, b, A: "+a+",B: "+b);
                return GCD(b,a%b);
            }
            //Else, reverse the other and compute GCD
            else{
                //System.out.println("b, a, A: "+a+",B: "+b);
                return GCD(b, a);
            }
        }
    }

    /**
     * TODO: Write non-recursive code that returns the same result as above
     */
    public static int GCD_NR(int a, int b){
        //TODO: modify method body so you get the same result as above recursive method
        int commonDivisor;
        if(a==0){
            return b;
        }
        else if(b==0){
            return a;
        }
        else{
            //Create an array list of integer to hold the list of divisor from integer a
            ArrayList<Integer> divisorA = new ArrayList<>();
            if(a>0){
                for(int i=1; i<=a; i++){
                    if(a%i==0){
                        //System.out.println("a Divisor: "+i);
                        divisorA.add(i);
                    }
                }
            }

            //Create an array list of integer to hold the list of divisor from integer b
            ArrayList<Integer> divisorB = new ArrayList<>();
            System.out.println();
            if(b>0){
                for(int j=1; j<=b; j++){
                    if(b%j==0){
                        //System.out.println("b Divisor: "+j);
                        divisorB.add(j);
                    }
                }
            }

            //Sort divisorA list largest to smallest
            Collections.reverse(divisorA);
            //Sort divisorB list smallest to largest
            Collections.reverse(divisorB);

            //Create a new list to hold common divisor from both a and b
            ArrayList<Integer> commonList = new ArrayList<>();

            for(int i=0; i<divisorA.size(); i++){
                for(int j=0; j<divisorB.size(); j++){
                    //If they are matching from both list, add to the commonList
                    if(divisorA.get(i)==divisorB.get(j)){
                        commonList.add(divisorA.get(i));
                    }
                }
            }

            //System.out.println(commonList);
            //The list already sorted largest to smallest so we don't need to sort it again
            //If the list is not null, then get the number at index 0, this should be the largest in the list
            //Also is the greatest common divisor
            if(commonList.size()>0){
                //System.out.println("Common divisor: "+commonList.get(0));
                commonDivisor = commonList.get(0);
            }else{
                commonDivisor = 0;
            }
            //System.out.println();

        }
        return commonDivisor;
    }
}