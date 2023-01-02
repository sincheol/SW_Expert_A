import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int testCase = 1; testCase <= 10; testCase++) {
            int tc = 0;
            int cnt = 0;
            String Input;
            String find;
            String[] nfind;

            tc = Integer.parseInt(br.readLine()); // read testcase num
            find = br.readLine(); // read substring
            Input = br.readLine(); // read string

            Input = "1"+Input+"1";

            nfind = Input.split(find);
            for(int i = 0;i<nfind.length;i++){
                if(nfind[i]=="1"){
                    cnt++;
                }
            }

            cnt = nfind.length-1;

            System.out.println("#" + tc + " " + cnt);
        }

    }
}
