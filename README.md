<img width="597" height="809" alt="University-Logo-Vertical-small-2" src="https://github.com/user-attachments/assets/748276b5-9921-4d2a-bacf-1832c0db62f7" />

Strathmore University 

Bachelor of Informatics and Computer Science 

Student 1: 160761 – Muriuki Nelly Nkatha 

Student 2: 168669 – Riang’a Ravine Kerubo 

Computer Simulations and Modelling 

ICS 4C 

Project: Bank Queue Simulation using Java 

Repository: https://github.com/nkathahh/160761_168669_CSM 

## **Table of Contents** 

|1.|Introduction ........................................................................................................................ 3|
|---|---|
|2.|Objectives .......................................................................................................................... 3|
|3.|Simulation Model............................................................................................................... 3|
|4.|Simulation Algorithm......................................................................................................... 4|
|5.|Mathematical Formulas ..................................................................................................... 4|
|6.|Java Implementation .......................................................................................................... 5|
|7.|Sample Simulation Output ................................................................................................. 6|
|8.|Queue Statistics .................................................................................................................. 8|
|9.|Discussion .......................................................................................................................... 8|
|10.|Conclusion ..................................................................................................................... 9|
|11.|Appendix ........................................................................................................................ 9|



## **1. Introduction** 

Queueing systems model situations where customers wait for service before being attended to. Banks are a common real-world example: customers arrive at unpredictable times, and a teller can only serve one customer at a time, which means waiting is sometimes unavoidable. 

This project simulates a single-server bank queue using Java. Customer inter-arrival times are generated from a Uniform(0.5, 3.0) minute distribution, and service times from a Uniform(1.0, 4.0) minute distribution. The simulation processes 100 customers under a FirstCome, First-Served (FCFS) discipline and computes a set of queue performance statistics from the results 

## **2. Objectives** 

The objectives of this simulation are: 

- To simulate random customer arrivals at a bank using a uniform distribution. 

- To simulate random customer service times using a uniform distribution. 

- To compute each customer's waiting time and total time spent in the system. 

- To calculate overall queue performance statistics after serving 100 customers. 

- To present the simulation through a simple Java Swing interface. 

## **3. Simulation Model** 

The model assumes a single queue served by one teller, with customers processed strictly in the order they arrive. 

Because service times are on average larger than inter-arrival times, the system is expected to show meaningful queue buildup rather than idle gaps. This is examined further in the Discussion section. 

|**Parameter**|**Distribution**|
|---|---|
|Inter-arrival Time|Uniform(0.5, 3.0) minutes|
|Service Time|Uniform(1.0, 4.0) minutes|
|Number of Customers|100|



|Queue discipline|First-Come, First-Served (FCFS)|
|---|---|
|Number of Servers|1|



## **4. Simulation Algorithm** 

For each of the 100 customers, the program performs the following steps: 

1. Generate a random inter-arrival time using the uniform distribution. 

2. Add it to the previous customer's arrival time to get this customer's arrival time. 

3. Generate a random service time using the uniform distribution. 

4. Determine the service start time - the later of the customer's arrival time or the previous customer's service end time. 

5. Compute the waiting time as the difference between service start and arrival. 

6. Compute the service end time by adding the service time to the service start time. 

7. Compute the total time the customer spends in the system. 

8. Accumulate running totals for averages. 

9. Repeat for all 100 customers, then compute summary statistics. 

## **5. Mathematical Formulas** 

## Arrival Time 

Arrival Time(i) = Arrival Time(i−1) + Inter-arrival Time(i) 

## Service Start Time 

Service Start = max(Arrival Time, Previous Service End Time) 

## Waiting Time 

Waiting Time = Service Start − Arrival Time 

## Service End Time 

Service End = Service Start + Service Time 

## Time in System 

Time in System = Waiting Time + Service Time 

## Server Utilization 

Server Utilization = (Total Service Time / Total Simulation Time) × 100% 

## **6. Java Implementation** 

The program is implemented as a single Java class using Swing for the interface. Key components: 

   - **JFrame** - the main application window. 

   - **CardLayout** - switches between an introductory explanation screen and the simulation screen, so the user understands what the program does before running it. 

   - **JButton** - one button to move from the intro screen into the simulation, and a second to run the simulation itself. 

   - **JTextArea** inside a JScrollPane - displays the full 100-row customer table and the summary statistics as scrollable monospaced text. 

   - **Random** - generates the uniformly distributed inter-arrival and service times. 

- **DecimalFormat** - formats all numerical output to three decimal places for readability. 

- The uniform(min, max) helper method implements the standard uniform distribution transformation: 

## value = min + (max − min) × random.nextDouble() 

This is reused for both inter-arrival and service time generation, keeping the random number generation logic in one place rather than duplicated. 

The simulation loop (simulate()) runs once per button click, recalculating all 100 customers from scratch with newly generated random numbers each time, so re-running the simulation produces different (but statistically similar) results. 

## **7. Sample Simulation Output** 

<img width="940" height="497" alt="image" src="https://github.com/user-attachments/assets/1632154a-9791-4ae6-9562-e15eabce95b2" />


A snippet of the customer table produced by the program (column headers: customer number, inter-arrival time, arrival time, service time, service start, waiting time, service end, time in system): 
<img width="940" height="528" alt="image" src="https://github.com/user-attachments/assets/d0a1e347-3257-458c-b161-322153206d02" />

<img width="940" height="498" alt="image" src="https://github.com/user-attachments/assets/d504df97-a3c4-4441-a296-f3a1a5e4aa27" />

<img width="940" height="440" alt="image" src="https://github.com/user-attachments/assets/5d97a077-ed52-4f2b-bd98-cab665e588d6" />

<img width="940" height="171" alt="image" src="https://github.com/user-attachments/assets/ec292821-655e-47f9-9f29-f77ccf8af115" />




## The output table displays: 

- Customer Number 

- 

   - Inter-arrival Time 

- Arrival Time 

- Service Time 

- Service Start Time 

- Waiting Time 

- Service End Time 

- Time in System 

## **8. Queue Statistics** 

After simulating 100 customers, the following statistics are displayed: 

|Statistic|Example Value|
|---|---|
|Customers Simulated|100|
|Average Inter-arrival Time|1.75 minutes|
|Average Service Time|2.48 minutes|
|Average Waiting Time|18.94 minutes|
|Average Time in System|21.42 minutes|
|Maximum Waiting Time|37.81 minutes|
|Server Utilization|98.74%|
|Total Simulation Time|251.83 minutes|



Actual figures vary slightly on each run because of the randomness inherent in the simulation, but the overall pattern remains consistent across runs. 

## **9. Discussion** 

The average service time (~2.48 minutes) exceeds the average inter-arrival time (~1.75 minutes), meaning customers arrive faster, on average, than the single teller can serve them. This imbalance is the primary driver of the simulation's results: waiting times climb steadily as the simulation progresses, and the teller is busy almost the entire time the bank is "open" (server utilization close to 99%). 

This matches queueing theory expectations. When the arrival rate exceeds the service rate, a single-server FCFS queue cannot keep pace, and the system trends toward instability; visible here in the long maximum waiting time (over 37 minutes for at least one customer) relative to the relatively short average service time. In a real bank, this would be evidence that a second teller is needed to bring the system back into balance. 

Running the simulation multiple times changes the specific numbers but not this underlying pattern, since the distributions themselves are unchanged; only the random samples drawn from them differ. 

## **10. Conclusion** 

This Java program successfully simulates a single-server bank queue for 100 customers using uniformly distributed inter-arrival and service times. It computes each customer's arrival time, waiting time, service time, and time in the system, and summarizes the results into key queue performance statistics. The simulation illustrates a core principle of queueing theory: when service capacity does not keep pace with arrival rate, waiting times and server utilization both rise sharply, even in a comparatively simple single-server system. 

## **11. Appendix** 

## **Appendix 1: Full Java Source Code** 

```java
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

public class BankQueueSimulation extends JFrame {

    JTextArea outputArea;
    JButton simulateButton;
    JButton startButton;

    CardLayout cardLayout;
    JPanel mainPanel;

    Random random = new Random();
    DecimalFormat df = new DecimalFormat("0.000");

    public BankQueueSimulation() {

        setTitle("Bank Queue Simulation");
        setSize(1100,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(buildIntroPanel(), "intro");
        mainPanel.add(buildSimulationPanel(), "simulation");

        add(mainPanel);

        cardLayout.show(mainPanel, "intro");

        setVisible(true);
    }

    private JPanel buildIntroPanel(){

        JPanel intro = new JPanel(new BorderLayout());
        intro.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        JLabel heading = new JLabel("Single-Server Bank Queue Simulation", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));

        String text =
                "<html><div style='text-align:left; font-size:13px; line-height:1.5;'>" +
                        "This program simulates a single-server bank queue using random number generation.<br><br>" +
                        "<b>Model assumptions:</b><br>" +
                        "&nbsp;&nbsp;- Inter-arrival times follow a Uniform(0.5, 3.0) minute distribution.<br>" +
                        "&nbsp;&nbsp;- Service times follow a Uniform(1.0, 4.0) minute distribution.<br>" +
                        "&nbsp;&nbsp;- One teller (single server) handles customers on a First-Come, First-Served basis.<br>" +
                        "&nbsp;&nbsp;- The simulation runs for 100 customers.<br><br>" +
                        "<b>What the program calculates for each customer:</b><br>" +
                        "&nbsp;&nbsp;- Arrival time and inter-arrival time<br>" +
                        "&nbsp;&nbsp;- Service start time and waiting time<br>" +
                        "&nbsp;&nbsp;- Service end time and total time in system<br><br>" +
                        "<b>Overall queue statistics produced:</b><br>" +
                        "&nbsp;&nbsp;- Average waiting time and average time in system<br>" +
                        "&nbsp;&nbsp;- Maximum waiting time<br>" +
                        "&nbsp;&nbsp;- Server utilization and total simulation time<br><br>" +
                        "Click <b>Start Simulation</b> below to generate the results." +
                        "</div></html>";

        JLabel description = new JLabel(text);

        startButton = new JButton("Start Simulation");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "simulation"));

        JPanel buttonWrap = new JPanel();
        buttonWrap.add(startButton);

        intro.add(heading, BorderLayout.NORTH);
        intro.add(description, BorderLayout.CENTER);
        intro.add(buttonWrap, BorderLayout.SOUTH);

        return intro;
    }

    private JPanel buildSimulationPanel(){

        JPanel sim = new JPanel(new BorderLayout());

        simulateButton = new JButton("Run Simulation");

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        sim.add(simulateButton, BorderLayout.NORTH);
        sim.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        simulateButton.addActionListener(e -> simulate());

        return sim;
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
```

## **Appendix 2: Screenshot of the running application.** 
<img width="940" height="513" alt="image" src="https://github.com/user-attachments/assets/32d5f714-7253-42cb-b729-13d146c52a18" />


## **Appendix 3: Screenshot of the queue statistics** 
<img width="940" height="171" alt="image" src="https://github.com/user-attachments/assets/a5a47677-68ee-4f50-a6ff-c15983b16489" />

