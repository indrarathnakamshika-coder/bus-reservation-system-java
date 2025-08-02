import java.util.Scanner;

class BusReservationSystem
{
    CustomerNode chead, ctail ; //Customer list
    BusNode      bhead, btail ; //Bus list
    ReserveNode  rhead, rtail ; //Reservation list
    QueueNode    qhead, qtail ; // waiting Customer list

    public void init()
    {
        chead = null;
        ctail = null;
        bhead = null;
        btail = null;
        rhead = null;
        rtail = null;
    }

    public void registerCustomer()  
    {
        Scanner obj = new Scanner (System.in);
        System.out.println("REGISTER CUSTOMER");
        System.out.print  ("Customer ID    :");
        int id = obj.nextInt();
        System.out.print  ("Name           :");
        String  name =  obj.next();
        System.out.print  ("Contact No     :");
        String  telno =  obj.next();
        System.out.print  ("Email ID       :");
        String  email =  obj.next();
        System.out.print  ("City           :");
        String  city =  obj.next();
        System.out.print  ("Age            :");
        int  age =  obj.nextInt();
        CustomerNode c = new CustomerNode();
        c.cusid = id ;
        c.name = name ;
        c.telno = telno ;
        c.email = email ;
        c.city = city ;
        c.age = age ;
        if ( chead == null ) 
        {
            chead = c;
            ctail = c;
            ctail.next = null;
        }
        else
        {
            ctail.next = c;
            ctail = c;
            ctail.next = null;
        }
        System.out.println("Customer Registered Successfully");  
    }

    public void displayCustomers()
    {
        System.out.println("LIST OF CUSTOMERS");
        CustomerNode c = chead;
        while (c != null) 
        {
            System.out.println(c.cusid+"   "+c.name+"   "+c.telno+"   "+c.email+"   "+c.city+"   "+c.age);
            c = c.next;
        }  
    }

    public void registerbus()
    {
        Scanner obj = new Scanner (System.in);
        System.out.println("REGISTER BUS");
        System.out.print  ("Bus number     :");
        int busno = obj.nextInt();
        System.out.print  ("Total Seats    :");
        int  tot =  obj.nextInt();
        System.out.print  ("Staring Point  :");
        String  start =  obj.next();
        System.out.print  ("End Point      :");
        String  end =  obj.next();
        System.out.print  ("Start Time     :");
        String  time =  obj.next();
        System.out.print  ("Travel Fare       :");
        double  fa =  obj.nextDouble();
        BusNode b = new BusNode();
        b.busno = busno ;
        b.total_seats = tot ;
        b.start = start ;
        b.end = end ;
        b.StartTime = time ;
        b.fare = fa ;
        if ( bhead == null )
        {
            bhead = b;
            btail = b;
            btail.next = null;
        }
        else
        {
            btail.next = b;
            btail = b;
            btail.next = null;
        }    
    }

    public void searchBuses()
    {
      Scanner obj = new Scanner (System.in);
      System.out.print("Enter Bus Number as Search Key  |");
      int  busno =  obj.nextInt();
      BusNode  b  =  bhead;
      while  (b!= null) 
      {
        if ( busno == b.busno)
        {
            System.out.println("Total Seats "+b.total_seats);
            System.out.println("Starting point  "+b.start);
            System.out.println("End Point "+b.end);
            System.out.println("Start Time "+b.StartTime);
            System.out.println("Travel Fare "+b.fare);
            return ;
        }
        else
        {
            b = b.next;
        }
      }
      System.out.println("No Bus found with the given Number");
    }

    public void reserveSeat()
    {
        Scanner obj = new Scanner (System.in);
        System.out.print("Enter Customer ID  |");
        int  cusid =  obj.nextInt();
        CustomerNode  c  =  chead;
        boolean found = false;
        while  (c!= null)
        {
            if (cusid == c.cusid)
            {
                System.out.println("Name  "+c.name);
                System.out.println("Contact No    "+c.telno);
                System.out.println("Email ID    "+c.email);
                System.out.println("City    "+c.city);
                System.out.println("Age    "+c.age);
                found = true;
                break ; 
            }
            else
            {
                c = c.next;
            }
        }
        if (found == false)
        {
            System.out.println("Oops!! Customer details not found in our system ");
            return;
        }
        System.out.print("Enter Bus Number as Search Key  |");
        int busno = obj.nextInt();
        BusNode b = bhead;
        found = false;
        while (b!= null)
        {
            if (busno == b.busno)
            {
                System.out.println("Bus Number  "+b.busno);
                System.out.println("Total seats  "+b.total_seats);
                System.out.println("Starting point  "+b.start);
                System.out.println("End Point "+b.end);
                System.out.println("Start Time "+b.StartTime);
                System.out.println("Travel Fare "+b.fare);
                found = true ;
                break;
            }
            else
            {
                b = b.next;
            }
        }
        if (found == false)
        {
            System.out.println("No matching bus found!!");
            return;
        }

        if (b.total_seats == 0)
        {
            System.out.println("Sorry, all seats are currently booked. Please try again soon");
        }
        else
        {
           ReserveNode  r = new ReserveNode();
           r.cusid =  c.cusid ;
           r.name = c.name;
           r.busno =  b.busno;
           r.seatno = b.total_seats;
           System.out.println ("------ CONFIRMATION MESSAGE ------");  
           System.out.println ("Confirmed Booking For: "+c.name);
           System.out.println ("Contact No   "+c.telno);
           System.out.println ("Bus Number    "+b.busno);
           System.out.println ("Seat  No "+b.total_seats);
           System.out.println ("----------------------------");

           b.total_seats = b.total_seats - 1 ; 

           if ( rhead == null)
           {
            rhead = r;
            rtail = r;
            rtail.next = null;
           }
           else
           {
            rtail.next = r;
            rtail = r;
            rtail.next = null;
           }
 
        }
    }

    public void requestSeat()
    {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter Customer ID to Request New Seat | ");
        int cusid = obj.nextInt();

        CustomerNode c = chead;
        boolean found = false;
       
        while (c != null)
        {
            if (c.cusid == cusid)
            {
                System.out.println("Customer Name: " + c.name + ", Contact No: " + c.telno);
                found = true;
                break;
            }
            c = c.next;   
        }
        if (!found)
        {
            System.out.println("No customer record found");
            return;
        }

        ReserveNode r = rhead;
        found = false;
        while (r != null)
        {
            if (r.cusid == cusid)
            {
                found = true;
                break;
            }
            r = r.next;
        }
        if (!found)
        {
            System.out.println("No active reservation found for this customer");
            return;
        }

        ReserveNode a = rhead, b = null;
        while (a != null)
        {
            if (a.cusid == cusid)
            {
                if (b == null)
                rhead = a.next;
                else
                b.next = a.next;
                break;
            }
            else
            {
                b = a;
                a = b.next;
            }
        }

        QueueNode q = new QueueNode();
        q.cusid = cusid;
        q.name = c.name;
        q.telno = c.telno;
        q.busno = a.busno;

        if (qhead == null)
        {
            qhead =q;
            qtail =q;
            qtail.next = null;
        }
        else
        {
            qtail.next = q;
            qtail = q;
            qtail.next = null;
        }
        System.out.println("Request successfully placed. You are now in the waiting queue");
    }

    public void cancelReservation()
    {
        ReserveNode r, s ;
        s = null;
        r = rhead;
        Scanner obj = new Scanner (System.in);
        System.out.print("Enter Customer ID as Search Key  |");
        int  cusid =  obj.nextInt();    
        while  (r!=null)
        {
            if (cusid == r.cusid)
            {
                BusNode b = bhead;
                while (b != null)
                {
                    if (b.busno == r.busno)
                    {
                        b.total_seats++;
                        break;
                    }
                    b = b.next;
                }

                if (s == null)
                {
                    rhead = r.next;
                }
                else
                {
                    s.next = r.next;
                }

                if (r == rtail)
                {
                    rtail = s;
                }
                System.out.println("Booking has been cancelled for: " + r.cusid);

                if (qhead != null)
                {
                    QueueNode temp = qhead;
                    qhead = qhead.next;
                    if (qhead == null) qtail = null;
                    ReserveNode rn = new ReserveNode();
                    rn.cusid = temp.cusid;
                    rn.name = temp.name;
                    rn.busno = temp.busno;
                    rn.seatno = b.total_seats;
                    b.total_seats--;
                
                    if (rhead == null)
                    {
                        rhead = rn;
                        rtail = rn;
                        
                    }
                    else
                    {
                        rtail.next = rn;
                        rtail = rn;
                        
                    }
                    rn.next = null;
                    
                    System.out.println("Info: Cancelled seat now booked for customer ID: " + temp.cusid);
                   
                }
                return;
            }
            else
            {
                s = r ;
                r = r.next;
            }
        }
        System.out.println("Customer ID does not exist in our system");
    }

    public void displayReservations()
    {
        System.out.println("LIST OF RESERVATIONS");
        ReserveNode c = rhead;
        while  ( c  !=  null)
        {
           System.out.println(c.cusid+"   "+c.name+"    "+c.busno+"   "+c.seatno);
           c = c.next; 
        }
    }

    public static void main(String arg[])
    {
        BusReservationSystem r1 = new BusReservationSystem();

        while (true)
        {
            System.out.println("BUS RESERVATION SYSTEM");
            System.out.println("1 - Initialize");
            System.out.println("2 - Register Customer");
            System.out.println("3 - Display Registered Customers");
            System.out.println("4 - Register Bus");
            System.out.println("5 - Search Buses");
            System.out.println("6 - Make Reservation");
            System.out.println("7 - Request New Seat");
            System.out.println("8 - Cancel Reservation");
            System.out.println("9 - Display All Reservations");
            System.out.println("10 - Exit");
            System.out.print("Enter Choice [1|2|3|4|5|6|7|8|9|10]");
            Scanner  obj =  new Scanner(System.in);
            int  choice = obj.nextInt();
            if (choice <1 || choice >9)
            System.out.println("Invalid Choice");
            if (choice ==1)
            r1.init();
            if (choice ==2)
            r1.registerCustomer();
            if (choice ==3)
            r1.displayCustomers();
            if (choice ==4)
            r1.registerbus();
            if (choice ==5)
            r1.searchBuses();
            if (choice ==6)
            r1.reserveSeat();
            if (choice ==7)
            r1.requestSeat();
            if (choice ==8)
            r1.cancelReservation(); 
            if (choice ==9)
            r1.displayReservations();
            if (choice ==10)
            break;
            
        }
        System.out.println("----Exiting System. Thank You!! ----");
    }
    
}