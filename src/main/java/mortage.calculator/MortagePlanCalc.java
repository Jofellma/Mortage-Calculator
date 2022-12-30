package mortage.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Currency;
public class MortagePlanCalc {

    public ArrayList<String> readFile (String path){
        ArrayList<String> rows = new ArrayList<String>();

        try {
            File inputFile = new File(path);
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            String row;

            while ((row = br.readLine()) != null) {
                rows.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows;
    }

    public ArrayList<Mortage> createMortageObjects(ArrayList<String> file){

        ArrayList<Mortage> mortages = new ArrayList<Mortage>();

        String[] headers = file.remove(0).split(",");

        for(String row : file){
            String[] entity = row.split("(?!\".*)(,)(?!.*\")");

            if (entity.length < 4){
                continue;
            }
            String customer = fixCustomerName(entity[0]);
            float totLoan = Float.parseFloat(entity[1]);
            float interest = Float.parseFloat(entity[2]);
            int years = Integer.parseInt(entity[3]);

            Mortage mortage = new Mortage(customer, totLoan, interest, years);
            mortages.add(mortage);
        }
        return mortages;
    }

    public String fixCustomerName(String name) {
        name = name.replace(",", " ");
        name = name.replaceAll("\"","");
        //name = name.replaceAll("[^a-öA-Ö\\s]", "");
        return name;
    }

    public void printOutMortage(Mortage mortage, int i) {
        System.out.println("Prospect " + i + ": " +
                mortage.getCustomer() + " wants to borrow " +
                mortage.getTotLoan() + " € for a period of " +
                mortage.getYears() + " years and pay " +
                String.format("%.2f", mortage.getMonthlyPay()) + "€ each month");
    }

    public MortagePlanCalc(String path) {
        ArrayList<String> file = readFile(path);
        ArrayList<Mortage> mortages = createMortageObjects(file);
        int i = 1;
        Currency currency = Currency.getInstance("EUR");
        //System.out.println("****************************************************************************************************");
        for(Mortage mortage : mortages) {
            mortage.calcMonthlyPay();
            System.out.println("****************************************************************************************************");
            System.out.println("Prospect " + i + ": "+ mortage.getCustomer() + " wants to borrow " +
                    mortage.getTotLoan() + " € for a period of " +
                    mortage.getYears() + " years and pay " +
                    mortage.getMonthlyPay() + " € each month");
            i += 1;
            System.out.println("****************************************************************************************************");
        }
        //System.out.println("****************************************************************************************************");
    }
}