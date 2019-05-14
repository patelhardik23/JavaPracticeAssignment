import java.util.Scanner;

public class Assignment1
{
//main method to run program
    public static void main(String[] args)
    {

        //declare variable that are going to use in program
        double minMAP = Double.MAX_VALUE;
        double maxMAP = Double.MIN_VALUE;
        double avg;
        int count = 0;
        double sbpVAL;
        double dbpVAL;
        double mapVAL;
        double totalMAP = 0;
        int numbOfIndividual;
        int identifier;
        
        Scanner in = new Scanner(System.in); // get input from user
        Calculator calculate = new Calculator(); //create object of calculator class

        do
        {
            System.out.print(
                    "Please Enter Nuber of individual for clinical trial : ");
            numbOfIndividual = in.nextInt();
            if (numbOfIndividual < 5 || numbOfIndividual > 10)
            {
                System.out.println("Please Enter value between 5 to 10");
            }
            else
            {
                System.out.println();
                break;
            }
        }
        while (numbOfIndividual < 5 || numbOfIndividual > 10); // as given total number of test patient should be between 5 to 10

        while (count < numbOfIndividual)
        {
            System.out.print("Enter the Identifier of Individual : ");
            identifier = in.nextInt(); //get patient identifier for test
            
            //validate value identifier
            if (identifier < 1 || identifier > 100)
            {
                System.out.println(
                        "Please enter valid identifier between 1 to 100");
                continue;
            }

            System.out.print("Enter the SBP of person " + identifier + ":");
            sbpVAL = in.nextDouble(); //get SBP of patient

            System.out.print("Enter the DBP of person " + identifier + ":");
            dbpVAL = in.nextDouble();  //get DBP of patient

            //SBP must be higher than DBP
            if (sbpVAL > dbpVAL)
            {
                mapVAL = calculate.value(sbpVAL, dbpVAL); //calculate value of map

                System.out.println(
                        "MAP value for person " + identifier + " is : " + String.format("%.1f", mapVAL));
                
                System.out.println("MAP value is "+calculate.category(mapVAL));

                totalMAP += mapVAL;
                
                //find min and max value
                if (mapVAL > maxMAP)
                {
                    maxMAP = mapVAL;
                }
                else if (mapVAL < minMAP)
                {
                    minMAP = mapVAL;
                }
                count = count + 1;

            }
            else
            {
                System.out
                        .println("Record for individual " + identifier
                                 + " has been discarded as SBP and DBP values are not correct. SBP must be greater than DBP. ");
                continue;
            }

        }
        avg = totalMAP / count; //calculate average

        //generate formatedreport.
        System.out.println("----------REPORT-----------");
        System.out.println("Highest value of MAP is :" + String.format("%.1f", maxMAP));
        System.out.println("Lowest value of MAP is  :" + String.format("%.1f", minMAP));
        System.out.println("Average value of MAP is :" + String.format("%.1f", avg));

    }

}
