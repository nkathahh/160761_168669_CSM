import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

public class BankQueueSimulation extends JFrame {

    JTextArea outputArea;
    JButton simulateButton;

    Random random = new Random();
    DecimalFormat df = new DecimalFormat("0.000");

    public BankQueueSimulation() {

        setTitle("Bank Queue Simulation");
        setSize(1100,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        simulateButton = new JButton("Run Simulation");

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(simulateButton, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        simulateButton.addActionListener(e -> simulate());

        setVisible(true);
    }

    // Uniform random number
    private double uniform(double min, double max){
        return min + (max-min) * random.nextDouble();
    }

    private void simulate(){

        outputArea.setText("");

        outputArea.append(String.format("%-5s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                "No","InterArr","Arrival","Service","Start","Wait","End","System"));

        double arrivalTime = 0;
        double previousEnd = 0;

        double totalWait = 0;
        double totalService = 0;
        double totalArrival = 0;
        double totalSystem = 0;
        double maxWait = 0;

        for(int i=1;i<=100;i++){

            double interArrival = uniform(0.5,3.0);

            if(i==1)
                arrivalTime = interArrival;
            else
                arrivalTime += interArrival;

            double serviceTime = uniform(1.0,4.0);

            double startService = Math.max(arrivalTime, previousEnd);

            double waitingTime = startService - arrivalTime;

            double endService = startService + serviceTime;

            double systemTime = waitingTime + serviceTime;

            previousEnd = endService;

            totalWait += waitingTime;
            totalService += serviceTime;
            totalArrival += interArrival;
            totalSystem += systemTime;

            if(waitingTime > maxWait)
                maxWait = waitingTime;

            outputArea.append(String.format(
                    "%-5d %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                    i,
                    df.format(interArrival),
                    df.format(arrivalTime),
                    df.format(serviceTime),
                    df.format(startService),
                    df.format(waitingTime),
                    df.format(endService),
                    df.format(systemTime)
            ));
        }

        double avgWait = totalWait/100;
        double avgService = totalService/100;
        double avgArrival = totalArrival/100;
        double avgSystem = totalSystem/100;

        double simulationTime = previousEnd;

        double utilization = (totalService/simulationTime)*100;

        outputArea.append("\nQueue Statistics\n");

        outputArea.append("Customers Simulated : 100\n");
        outputArea.append("Average Inter-arrival Time : " + df.format(avgArrival)+"\n");
        outputArea.append("Average Service Time : " + df.format(avgService)+"\n");
        outputArea.append("Average Waiting Time : " + df.format(avgWait)+"\n");
        outputArea.append("Average Time in System : " + df.format(avgSystem)+"\n");
        outputArea.append("Maximum Waiting Time : " + df.format(maxWait)+"\n");
        outputArea.append("Server Utilization : " + df.format(utilization)+"%\n");
        outputArea.append("Total Simulation Time : " + df.format(simulationTime)+"\n");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankQueueSimulation::new);
    }
}