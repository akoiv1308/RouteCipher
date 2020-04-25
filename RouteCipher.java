public class RouteCipher {

  //Fields
  private static String[][] letterBlock;
  private static int numRows;
  private static int numCols;

  //Constructor
  public RouteCipher(int numRows, int numCols) {
    //initializing variables
    this.numRows = numRows;
    this.numCols = numCols;
    letterBlock = new String[numRows][numCols];
  }

  //Access Methods 
  public static void fillBlock(String str) {
    int count = 0; //used to count indexes/positions
    for(int r = 0; r < letterBlock.length; r++) { //going through an array
      for(int c = 0; c < letterBlock[0].length; c++) {
        if(count < str.length()) { //as long as the position of the elements is less than the length of a string, add it to an array and increase the count by one
          letterBlock[r][c] = str.substring(count, count + 1);
          count++;
        }
        else { //else, replace the remaining elements with A
          letterBlock[r][c] = "A";
        }
      }
    }
    //print
    print();
  }

  public static String encryptBlock() { 
    String message = "";
    for(int c = 0; c < letterBlock[0].length; c++) { //column-major order 
      for(int r = 0; r < letterBlock.length; r++) {
        message += letterBlock[r][c]; //creating encrypted order by adding letters from each column first, and then row
      }
    }
    return message;
  }

  public static void print() { //used to print an array for displaying purposes
    for(int i = 0; i < letterBlock.length; i++) {
      for(int j = 0; j < letterBlock[0].length; j++) {
        System.out.print(letterBlock[i][j]);
      }
      System.out.println();
    }
  }

  public String encryptMessage(String message) {
    String encryption = ""; //new string to store encrypted message
    int sizeOfmessage = numRows * numCols; //identifying the size of an array
    int times = (message.length()/sizeOfmessage);
    if(message.length() % sizeOfmessage != 0) { //checking how many times the process should repeat 
      times++;
    }
    for(int i = 0; i < times; i++) { 
      if (sizeOfmessage > message.length()) { //assigning the new length every time the for loop executes
        sizeOfmessage = message.length();
      }
      fillBlock(message); //filling letters into array
      encryption += encryptBlock(); //encrypting message
      message = message.substring(sizeOfmessage); //changing the length by cutting off the only part we haven't went through
    }
    return encryption;
  }

}