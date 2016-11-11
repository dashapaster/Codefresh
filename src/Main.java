public class Main {
    public static void main(String[] args){
        FirstApp firstApp = new FirstApp();

        if (args.length > 0) {
            firstApp.runProgram(args[0]);
        } else {
            firstApp.runProgram();
        }
    }
}
