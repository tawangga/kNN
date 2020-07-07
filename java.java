
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tawangga
 */
public class java {
    static List<String[]> trainingData = new ArrayList<>();
    static List<String[]> testingData = new ArrayList<>();
    static int nilaiKNN;

    public static void main(String[] args) {
        readData();
 
        System.out.println("Training Data : ");
        printArray(trainingData);
        
        System.out.println("\nTesting Data : ");
        printArray(testingData);
        
        System.out.print("Pilih data yang akan di tes : ");
        Scanner scan = new Scanner(System.in);
        int pilih = scan.nextInt() - 1;
        System.out.print("\nmasukkan nilai k-NN : ");
        nilaiKNN = scan.nextInt();

        if (testingData.size() >= pilih) {
            String[] testing = testingData.get(pilih);
            System.out.println("\nData Testing :");
            System.out.println(Arrays.toString(testing).replace(", ", "\t").replace("[", "").replace("]", "")+"\n");
            bacaEuclidean(testing);  
        }else{
            System.err.println("Error input data");
        }
    }
    
    private static void readData() {
        try {
            File file = new File("data.txt"); 
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String str = "";

            int count  = 1;
            while (str != null) {
                str = br.readLine(); 
                if (str != null){
                    if(count <= 38 || (count > 50 && count <=88) || (count> 100 && count <=138)){
                        trainingData.add(str.split(" "));
                    }else{
                         testingData.add(str.split(" "));
                    } 
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File NotFound");
        } catch (IOException e) { 
            System.err.println("Error Reaing File");
        }
    }

    private static String jarakEuclidean(String[] training, String[] testing){
        double d = 0.0;
        for(int i = 0 ; i < training.length -1; i++){
            d += Math.pow(Double.valueOf(training[i]) - Double.valueOf(testing[i]), 2);
        }
        return String.format("%.3f", Math.sqrt(d));

    }

    private static void bacaEuclidean(String[] testing) {
        ArrayList<String[]> jarak = new ArrayList<>();
        trainingData.stream().map((training) -> {
            String[] s = new String[6];
            s[0] = training[0];
            s[1] = training[1];
            s[2] = training[2];
            s[3] = training[3];
            s[4] = training[4];
            s[5] = jarakEuclidean(training, testing);
            return s;
        }).forEachOrdered((s) -> {
            jarak.add(s);
        });
        
        jarak.sort((String[] o1, String[] o2) -> {
            if (Double.valueOf(o1[5]) > Double.valueOf(o2[5]))
                return 1;
            else
                return -1;
        });
        
        System.out.println("\nJarak Euclidean :");
        printArray(jarak);
        
        
        List<String[]> hasil = new ArrayList<>();
        hasil.addAll(jarak.subList(0, nilaiKNN));
        System.out.println("\nTop 3 :");
        printArray(hasil);
        cariHasil(hasil);
    }

    private static void printArray(List<String[]> datas) {
        int no = 1;
        for(String[] data : datas){
             System.out.println(no+"\t"+Arrays.toString(data).replace(", ", "\t").replace("[", "").replace("]", ""));
             no++;
        }

    }

    private static void cariHasil(List<String[]> hasil) {
        List<String[]> temp = new ArrayList<>();
        
        
    }
}
