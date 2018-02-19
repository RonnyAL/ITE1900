package modul_2;

public class Test {
	  public static void main(String[] args) {
	    try {
	      System.out.println("Line 1");
	      int i = 0;
	      double y = 2.0 / i;
	      System.out.println("Line 2");
	    }
	    finally {
	      System.out.println("Finally");
	    }
	  }
	}