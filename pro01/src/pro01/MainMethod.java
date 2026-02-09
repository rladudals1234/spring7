package pro01;

public class MainMethod {

	public static void main(String[] args) {
		System.out.println("메인프로그램");
		//클래스 다른 클래스 사용하려면 객체선언해야 사용가능
		//Tv tv = new Tv();
		Product tv = new Tv3();		//스프링은 new X
		tv.name="삼성TV";
		System.out.println(tv.name);

		Audio audio = new Audio();
		audio.name = "하만오디오";
		System.out.println(audio.name);
	}

}
