import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carId;
    private String carModel;
    private String carBrand;
    private double carRentPrice;
    private boolean isavailable ;


    public Car(String carId, String carBrand, String carModel, double carRentPrice){
        this.carId=carId;
        this.carBrand=carBrand;
        this.carModel=carModel;
        this.carRentPrice=carRentPrice;
         this.isavailable=true;
    }

    public boolean isIsavailable() {
        return isavailable;
    }


    public String getCarId() {
        return carId;
    }

    public double getCarRentPrice(int rentalDays) {
        return carRentPrice*rentalDays;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void rent(){
        isavailable=false;
    }

    public void returnCar(){
        isavailable=true;
    }


}
class Customer{
    private String customerId;
    private String customerName;

    public Customer(String customerId,String customerName){
        this.customerId=customerId;
        this.customerName=customerName;
    }
    public String getCustomerId(){
        return  customerId;
    }
    public String getCustomerName(){
        return customerName;
    }
}
class Rental{
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car,Customer customer,int days){
        this.car=car;
        this.customer=customer;
        this.days=days;
    }

    public Car getCar() {
        return car;
    }
    public Customer getCustomer(){
        return customer;
    }

    public int getDays(){
        return days;
    }
}
class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars= new ArrayList<>();
        customers= new ArrayList<>();
        rentals= new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

   public void addCustomer(Customer customer){
        customers.add(customer);
   }

   public void rentCar(Car car,Customer customer,int days){
        if(car.isIsavailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }else{
            System.out.println("Car is not available for rent.");
        }
   }

   public void returnedCar(Car car){
       car.returnCar();
      Rental rentalToRemove=null;
      for(Rental rental:rentals){
          if(rental.getCar()==car){
              rentalToRemove=rental;
              break;
          }
      }
      if(rentalToRemove!=null){
          rentals.remove(rentalToRemove);
          System.out.println("car returned successfully.");
      }else{
          System.out.println("car was not rented.");
      }
   }

     public void menu(){

        while(true) {
            System.out.println("================= CAR RENTAL SYSTEM =================");
            System.out.println();
            System.out.println("1. RENT A CAR");
            System.out.println("2. RETURN A CAR");
            System.out.println("3. EXIT");
            System.out.println();
            System.out.println("Enter your choice:");

            Scanner scn = new Scanner(System.in);
            int choice = scn.nextInt();
            scn.nextLine();

            if(choice==1){
                System.out.println("========= RENT A CAR =========");

                System.out.println("Enter your name: ");
                String customerName=scn.nextLine();

                System.out.println("Available Cars:");
                for(Car car:cars){
                    if(car.isIsavailable()){
                        System.out.println(car.getCarId()+" "+car.getCarModel()+" "+car.getCarBrand());
                    }
                }

                System.out.println("Enter the car id that you want to rent:");
                String carId=scn.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays=scn.nextInt();
                scn.nextLine();

                Customer newCustomer= new Customer("PCR"+(customers.size()+1),customerName);
                 addCustomer(newCustomer);

                Car selectedCar=null;
                 for (Car car: cars){
                     if(car.getCarId().equals(carId) && car.isIsavailable()){
                         selectedCar=car;
                     }
                 }

                 if(selectedCar!=null){
                     double totalPrice=selectedCar.getCarRentPrice(rentalDays);
                     System.out.println("========= Rental information =========");
                     System.out.println("Customer Id: "+newCustomer.getCustomerId());
                     System.out.println("Customer Name: "+customerName);
                     System.out.println("Car:"+selectedCar.getCarBrand()+" "+selectedCar.getCarModel());
                     System.out.println("Rental Days: "+rentalDays);
                     System.out.println("Total Price: "+totalPrice);

                     System.out.println("Confirm Rental (Y/N):");
                     String confirm = scn.nextLine();
                     if(confirm  .equalsIgnoreCase("y")){
                     rentCar(selectedCar,newCustomer,rentalDays);
                         System.out.println("Car rented successfully");
                     }else{
                         System.out.println("Rental canceled.");
                     }

                 }else{
                     System.out.println("Car is not available for rent or Invailed car selection.");
                 }


            }else if(choice==2){
                System.out.println("========= Return a Car =========");
                System.out.println("Enter a car id that you want to return.");
                String returndCarID=scn.nextLine();

                Car carToReturned=null;
                for(Car car:cars){
                    if(car.getCarId().equals(returndCarID)){
                        carToReturned=car;
                    }
                }

                if(carToReturned!=null){
                    Customer customer=null;
                    for(Rental rental:rentals){
                        if(rental.getCar()==carToReturned){
                            customer=rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null){
                        returnedCar(carToReturned);
                        System.out.println("car successfully return by "+customer.getCustomerName());
                    }else{
                        System.out.println("car was not rented or rental information is missing.");
                    }
                }else{
                    System.out.println("Invalid car ID or Car is not rented.");
                }

            }else if(choice==3){
                break;
            }else{
                System.out.println("Invalid choice or please enter a valid number.  ");
            }
        }

         System.out.println("Thank you for using  tha car rental system.");

     }




}
public class Main {
    public static void main(String[] args) {

        CarRentalSystem carRentalSystem= new CarRentalSystem();
        Car car = new Car("PCR1","Toyota","Camry",600);
        Car car1 = new Car("PCR2","mahindra","thar",900);
        Car car2 = new Car("PCR2","Tata","nexon",400);
        carRentalSystem.addCar(car);
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.menu();



    }
}