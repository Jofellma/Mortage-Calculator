package mortage.calculator;

public class Mortage {
    //Setting up variables
    private String customer;
    private float totLoan;
    private float interest;
    private int years;
    private double monthlyPay;

    //constructor
    public Mortage(String customer, float totLoan, float interest, int years) {

        this.customer = customer;
        this.totLoan = totLoan;
        this.interest = interest;
        this.years = years;

    }
    //power function
    public double toPower(double base, int power){

        double number = 1;
        for(int i = 0; i < power; i++){
            number = number*base;
        }
        return number;
    }

    //round to two decimals
    public double round2Dec(double sum){
        sum *= 100.0;
        sum += 0.5;
        sum = (int)sum;
        sum /= 100.0;

        return sum;
    }
    //get the mothly pay for a prospect
    public void calcMonthlyPay(){
        //Total loan
        double U = totLoan;
        //get number of payments
        int p = years*12;
        //get interest rate
        double b = (interest/100)/12;
        //calculate the monthly payment
        double E = U * ((b*toPower(1+b,p)) / (toPower(1+b,p)-1));

        this.monthlyPay = round2Dec(E);
    }

    //Setters and getters
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String cust){
        this.customer = cust;
    }

    public float getTotLoan() {
        return totLoan;
    }

    public void setTotLoan(float tot){
        this.totLoan = tot;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int y){
        this.years = y;
    }

    public double getMonthlyPay() {
        return monthlyPay;
    }
}