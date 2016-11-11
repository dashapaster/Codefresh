/**
 * Created by dasha on 11/11/16.
 */
import java.io.*;
import java.lang.management.ManagementFactory;

public class FirstApp {
    private String id;
    private HttpConn httpConn = new HttpConn();
    Process childProcess;

    public void runProgram() {
        resetStdout();
        if(id==null){
            step2();
        }else {
            step3(id);
        }
    }
    public void runProgram(String id) {
        resetStdout();
        step3(id);
    }


    private void resetStdout() {
        try {
            String runtimeName = getProcessName();
            System.setOut(new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(runtimeName+"_logs.txt")), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getProcessName() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }


    public void step2(){
        id = httpConn.getIdentifier();
        step3(id);
    }

    public void step3(String id) {
        String response = httpConn.useIdentifier(id);
        switch (response) {
            case "0":
                step2();
                break;
            case "1":
                fork(id);
                break;
            case "2":
                System.exit(0);
            default:
                printlog(response);
                step3(id);
                break;
        }
    }


    public void printlog(String log){
        System.out.println(log);
    }

    public void fork(String id){
           ProcessBuilder pb = new ProcessBuilder("java", "-jar", "Codefresh.jar", id);
            pb.redirectErrorStream(true);
        try {
            childProcess = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



