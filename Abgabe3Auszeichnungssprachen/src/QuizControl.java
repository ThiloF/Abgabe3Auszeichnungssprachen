import java.util.ArrayList;

public class QuizControl {

	public static void main(String[] args) {

		ReadQuestions rq = new ReadQuestions();

		rq.readQst();
		ArrayList<Questions> quiz = rq.getQuestions();

		for (Questions q : quiz) {
			System.out.println("Themengebiet: " + q.getTopic() );
			System.out.println("Frage: " + q.getQuestion());
			System.out.println("Antworten:");
			 for(String s: q.getOptions()){
				 System.out.println("Antwort: " + s);
			 }
			 System.out.println("Korrekt: " + q.getAnswer());
			 System.out.println("_____________________________________");
		}
		
		

	}

}
