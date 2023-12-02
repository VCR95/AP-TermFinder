import java.util.Scanner;

public class AP {

    private static int recursionCount = 0;

    private static int calcFactorial(int n){
        int fact = 1;
        for (int i=2; i<=n; i++){
            fact = fact * i;
        }
        return fact;
    }

    static int calcAP(int[] arrSeq, int nthTerm){
        int arrLen = arrSeq.length;
        int newArr[] = new int[arrLen - 1];
        int cd = 0; // common difference
        int termAux = nthTerm;
        int numerator = 1, addend = 0;

        for(int i = 0; i <= arrLen-2; i++){
            newArr[i] = arrSeq[i+1] - arrSeq[i];
            if (i == 1 && (newArr[0] == newArr[1] || newArr[1] == 0))  {
                    cd = newArr[0];
                    break; //1st order AP found
                }
        }

        if (recursionCount > 0) {
            for (int k=0; k < recursionCount; k++){
                termAux--;
                numerator = numerator * termAux;
            }
            numerator = numerator * arrSeq[0];

        } else if (recursionCount == 0 && cd != 0){
            //1st order AP, no need for further calc
            addend = arrSeq[0] + cd * (nthTerm-1);
            return addend;

        } else {
            numerator = arrSeq[0];
        }

        addend = numerator / calcFactorial(recursionCount);

        recursionCount++;
        if (newArr[0] == 0){
            return addend;
        }

        return calcAP(newArr, nthTerm) + addend;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms in the sequence: ");
        int n = sc.nextInt(); //number of terms

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            System.out.print("Enter term #"+(i+1)+": ");
            arr[i]=sc.nextInt();
        }

        System.out.print("Enter the term to be found: ");
        int an = sc.nextInt();

        System.out.println("An = "+calcAP(arr, an));

    }
}
