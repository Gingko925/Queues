import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
		int k = Integer.parseInt(args[0]);
		int n = 0;
		while (!StdIn.isEmpty()) {

			String string = StdIn.readString();
			n++;
			
			if (n > k) {
				int rdm = StdRandom.uniform(n);
				if (rdm < k) {
					randomizedQueue.dequeue();
					randomizedQueue.enqueue(string);
				}
			}
			else {
				randomizedQueue.enqueue(string);
			}
			
		}
		
		for (int i = 0; i < k; i++) {
			StdOut.println(randomizedQueue.dequeue());
		}
	}
}
