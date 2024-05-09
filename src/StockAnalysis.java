import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StockAnalysis {
    
    public static void main(String[] args) {
        startTheProgram();
    }
    
    public static void startTheProgram() {
        Scanner input = new Scanner(System.in);
        Integer choice;
        
        printManual("start");
        
        while (true) {
            try {
                System.out.print("Your numeric choice =>: ");
                String choiceString = input.nextLine().trim();
                
                if (choiceString.length() > 1 || choiceString.isEmpty()) {
                    System.out.println("Too many characters. Please enter an integer: 0, 1, 2, 3, or 4.");
                    continue;
                }
                
                choice = Integer.parseInt(choiceString);
                
                if (choice == 0) {
                    printManual("quit");
                    break;
                } else if (choice >= 1 && choice <= 4) {
                    switch (choice) {
                        case 1:
                            calculateAveragePrice(input);
                            break;
                        case 2:
                            findMaximumPrice(input);
                            break;
                        case 3:
                            countOccurrences(input);
                            break;
                        case 4:
                            computeCumulativeSum(input);
                            break;
                    }
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer: 0, 1, 2, 3, or 4.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer: 0, 1, 2, 3, or 4.");
            }
        }
        
        input.close();
    }
    
    
    public static void printManual(String startOrQuit) {
        if (startOrQuit.equals("start")) {
            System.out.println("=========================================");
            System.out.println("Enter a numeric value corresponding to the");
            System.out.println("operation that you want to carry out");
            System.out.println("=========================================");
        } else if (startOrQuit.equals("quit")) {
            System.out.println("Goodbye! Program ended.");
        }
        System.out.println("Choose an option:");
        System.out.println("1. Calculate average price");
        System.out.println("2. Find maximum price");
        System.out.println("3. Count occurrences");
        System.out.println("4. Compute cumulative sum");
        System.out.println("0. Quit");
    }
    
    
	public static void calculateAveragePrice(Scanner input) {
        try {
            System.out.println("Enter the stock prices separated by spaces:");
            String inputLine = input.nextLine();
            String[] tokens = inputLine.split("\\s*,\\s*");

            if (tokens.length == 0) {
                throw new IllegalArgumentException("Empty input. Please enter at least one stock price.");
            }

            float sum = 0;
            for (String token : tokens) {
                float price = Float.parseFloat(token);
                sum += price;
            }

            float average = sum / tokens.length;
            BigDecimal averageBigDecimal = new BigDecimal(Float.toString(average));
            averageBigDecimal = averageBigDecimal.setScale(2, RoundingMode.HALF_UP);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedDouble = decimalFormat.format(averageBigDecimal);

            System.out.println("**********************************");
            System.out.println("The Average Price of the entered value(s) to 2 decimal places is == " + formattedDouble);
            System.out.println("**********************************");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format. Please enter numeric values separated by commas.");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Input mismatch. Please enter valid numeric values separated by commas.");
        }
        System.out.println("\n");
        
        
    }
    
    public static void findMaximumPrice(Scanner input) {
    	 try {
             System.out.println("Enter the stock prices separated by commas:");
             String inputLine = input.nextLine();
             String[] tokens = inputLine.split("\\s*,\\s*");

             if (tokens.length == 0) {
                 throw new IllegalArgumentException("Empty input. Please enter at least one stock price.");
             }

             float maxPrice = Float.parseFloat(tokens[0]);
             for (String token : tokens) {
                 float price = Float.parseFloat(token);
                 if (price > maxPrice) {
                     maxPrice = price;
                 }
             }

             System.out.println("**********************************");
             System.out.println("The Maximum Price among the entered values is == " + maxPrice);
             System.out.println("**********************************");
         } catch (NumberFormatException e) {
             System.out.println("Error: Invalid input format. Please enter numeric values separated by commas.");
         } catch (IllegalArgumentException e) {
             System.out.println("Error: " + e.getMessage());
         } catch (InputMismatchException e) {
             System.out.println("Error: Input mismatch. Please enter valid numeric values separated by commas.");
         }
    	 System.out.println("\n");
         startTheProgram();
     }
    
    
    public static void countOccurrences(Scanner input) {
        while (true) {
            try {
                System.out.println("Enter the stock prices separated by commas:");
                String inputLine = input.nextLine();
                String[] tokens = inputLine.split("\\s*,\\s*");

                if (tokens.length == 0) {
                    throw new IllegalArgumentException("Empty input. Please enter at least one stock price.");
                }

                System.out.println("Enter the target price:");
                float targetPrice = input.nextFloat();

                int count = 0;
                for (String token : tokens) {
                    float price = Float.parseFloat(token);
                    if (price == targetPrice) {
                        count++;
                    }
                }

                System.out.println("**********************************");
                System.out.println("The target price occurred " + count + " times.");
                System.out.println("**********************************");
                break; // Break out of the loop if input and processing are successful
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input format. Please enter numeric values separated by commas.");
                input.nextLine();
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); 
                continue;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input mismatch. Please enter valid numeric values separated by commas.");
                input.nextLine(); 
                continue;
            }
        }
        System.out.println("\n");
        startTheProgram();
    }


    
    public static void computeCumulativeSum(Scanner input) {
    	try {
            System.out.println("Enter the stock prices separated by commas:");
            String inputLine = input.nextLine();
            String[] tokens = inputLine.split("\\s*,\\s*");

            if (tokens.length == 0) {
                throw new IllegalArgumentException("Empty input. Please enter at least one stock price.");
            }

            ArrayList<Float> cumulativeSum = new ArrayList<>();
            float sum = 0;
            for (String token : tokens) {
                float price = Float.parseFloat(token);
                sum += price;
                cumulativeSum.add(sum);
            }

            System.out.println("**********************************");
            System.out.println("Below is the cumulative sum of the stock prices:");
            for (Float sumValue : cumulativeSum) {
                System.out.println(String.format("%.2f", sumValue)); 
            }
            System.out.println("**********************************");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format. Please enter numeric values separated by commas.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Input mismatch. Please enter valid numeric values separated by commas.");
        }
        System.out.println("\n");
        startTheProgram();
    }
    
}
