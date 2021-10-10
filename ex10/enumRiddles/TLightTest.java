package enumRiddles;

enum TLight {
	// Each instance provides its implementation to abstract method
	RED(30),
	AMBER(10),
	GREEN(30);


	private final int seconds;     // Private variable

	TLight(int seconds) {          // Constructor
		this.seconds = seconds;
	}

	int getSeconds() {             // Getter
		return seconds;
	}

	TLight next() {
		switch (this) {
			case RED:	return GREEN;
			case AMBER:	return RED;
			case GREEN:	return AMBER;
		}
		return null;
	}
}

public class TLightTest {
	public static void main(String[] args) {
		for (TLight light : TLight.values()) {
			System.out.printf("%s: %d seconds, next is %s\n", light,
					light.getSeconds(), light.next());
		}
	}
}