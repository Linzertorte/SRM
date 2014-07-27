public class AutoLoan
{
    private double getBalance(double annualRate, double price, double monthlyPayment, int loanTerm){
        double monthlyRate = annualRate/12;
        double balance = price;
        for(int i=0;i<loanTerm;i++){
            balance = balance*(1+monthlyRate/100) - monthlyPayment;
        }
        return balance;
        
    }
    public double interestRate(double price, double monthlyPayment, int loanTerm)
    {
        double l = 0, r = 100;
        while(Math.abs(l-r)>1e-10){
            double mid = (l+r)/2;
            if(getBalance(mid,price,monthlyPayment,loanTerm)>0) r=mid;
            else l=mid;
        }
        return l;
    }
}
