
import java.util.Scanner;

public class DS6_NoStarvePriorityQueue_Main
{
    public static void main(String[]args)
    {

        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS6_PriorityQueueInterface<DS6_PriorityNode<String>> nspq= new DS6_NoStarvePriorityQueue<>();

        do
        {
            System.out.print("\n-Menu-\n1. Print\n2. Size\n3. Empty?\n4. Add\n5. Remove\n6. Clear\n0. Exit\nEnter selection: ");
            choice= keyboard.nextInt();

            if(choice==1)
            {
                System.out.println("\nThe data in the priority queue is: " + nspq);
            }
            if(choice==2)
            {
                System.out.println("\nThe size of the priority queue is " + nspq.size());
            }
            if(choice==3)
            {
                if(nspq.isEmpty()==true)
                    System.out.println("\nThe priority queue is empty.");
                else
                    System.out.println("\nThe priority queue has data.");
            }
            if(choice==4)
            {
                System.out.print("\nEnter text to be added: ");
                String text = keyboard.next();
                System.out.print("Enter priority of this text: ");
                Integer priority = keyboard.nextInt();
                DS6_PriorityNode<String> item = new DS6_PriorityNode<>(text,priority);
                nspq.offer(item);
                System.out.println(item+" was added to the priority queue.");
            }
            if(choice==5)
            {
                System.out.println("\n"+nspq.poll()+" was removed from the priority queue.");
            }
            if(choice==6)
            {
                nspq.clear();
                System.out.println("\nThe priority queue has been cleared.");
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");
    }
}
